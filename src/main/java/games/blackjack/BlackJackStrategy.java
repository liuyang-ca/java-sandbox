package games.blackjack;

import games.card.Ranks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackStrategy {

    private final static Logger LOGGER = LoggerFactory.getLogger(BlackJackPlayer.class);
    private static Map<String, Map<String, BlackJackAction>> strategyMap;

    private static Map<String, Map<String, BlackJackAction>> getStrategyMap() {
        if (strategyMap == null) {
            loadAll();
        }
        return strategyMap;
    }

    private static String sortPlayersHand(String playersHand) {
        playersHand = playersHand.trim();

        // order player's hand from small to large
        String[] playersHandArray = null;
        if (playersHand.contains(",")) {
            playersHandArray = playersHand.split(",");
            if (playersHandArray.length != 2) {
                throw new RuntimeException("playersHand: " + playersHand + " not valid, only support max 2 split hand");
            }
            Ranks rank1 = Ranks.fromCode(playersHandArray[0]);
            Ranks rank2 = Ranks.fromCode(playersHandArray[1]);

            if (rank1.getValue() > rank2.getValue()) {
                return rank2.getCode() + "," + rank1.getCode();
            }
        }

        return playersHand;
    }

    private static BlackJackAction getActionUsingSum(String[] playersHandArray, String playersHand, String dealersFaceUpCard) {
        String playersHandSum = String.valueOf(Integer.valueOf(playersHandArray[0]) + Integer.valueOf(playersHandArray[1]));
        LOGGER.debug("not found action for playersHand {}, using sum {}", playersHand, playersHandSum);
        return getAction(playersHandSum, dealersFaceUpCard);
    }

    public static BlackJackAction getAction(String playersHand, String dealersFaceUpCard) {
        playersHand = playersHand.trim();
        dealersFaceUpCard = dealersFaceUpCard.trim();

        // order player's hand from small to large
        String[] playersHandArray = null;

        if (playersHand.contains(",")) {
            playersHandArray = playersHand.split(",");
            if (playersHandArray.length != 2) {
                throw new RuntimeException("playersHand: " + playersHand + " not valid, only support max 2 split hand");
            }
            Ranks rank1 = Ranks.fromCode(playersHandArray[0]);
            Ranks rank2 = Ranks.fromCode(playersHandArray[1]);

            if (rank1.getValue() > rank2.getValue()) {
                playersHand = rank2.getCode() + "," + rank1.getCode();
            }
        }


        Map<String, BlackJackAction> row = getStrategyMap().get(playersHand);
        if (row == null) {
            if (playersHandArray != null) {
                return getActionUsingSum(playersHandArray, playersHand, dealersFaceUpCard);
            }
            throw new RuntimeException("not able to find action, playersHand: " + playersHand + ", dealersFaceUpCard: " + dealersFaceUpCard);
        }

        BlackJackAction action = row.get(dealersFaceUpCard);
        if (action == null) {
            if (playersHandArray != null) {
                return getActionUsingSum(playersHandArray, playersHand, dealersFaceUpCard);
            }
            throw new RuntimeException("not able to find action, playersHand: " + playersHand + ", dealersFaceUpCard: " + dealersFaceUpCard);
        }
        return action;
    }

    public static void overrideDefaultAction(String playersHand, String dealersFaceUpCard, BlackJackAction action) {
        if (action == null) {
            throw new RuntimeException("invalid BlackJackAction: " + action);
        }

        playersHand = sortPlayersHand(playersHand);

        // validate input is valid
        getAction(playersHand, dealersFaceUpCard);

        Map<String, BlackJackAction> row = getStrategyMap().get(playersHand);
        if (row == null) {
            row = new LinkedHashMap<String, BlackJackAction>();
            getStrategyMap().put(playersHand, row);
        }

        row.put(dealersFaceUpCard, action);
    }

    public static void printStrategy() {
        // print header
        for(String s : Arrays.asList(" ", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A")) {
            System.out.print(String.format("%-7s", s));
        }
        System.out.println();

        // print
        for (String playerHand : getStrategyMap().keySet()) {
            System.out.print(String.format("%-7s", playerHand));
            Map<String, BlackJackAction> row = strategyMap.get(playerHand);
            for (String i : Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "A")) {
                BlackJackAction action = row.get(i);
                System.out.print(String.format("%-7s", action == null? "":action.getCode()));
            }
            System.out.println();
        }
    }

    public static void loadStrategy() {
        loadStrategyFile("strategy.txt");
    }

    public static void load2Cards() {
        loadStrategyFile("2cards.txt");
    }

    public static void loadTmp() {
        loadStrategyFile("tmp.txt");
    }

    public static void clear() {
        strategyMap.clear();
    }

    private static void loadAll() {
        loadStrategy();
        load2Cards();
    }

    private static void loadStrategyFile(String fileName) {
        LOGGER.info("loading strategy file: {}", fileName);
        if (strategyMap == null) {
            strategyMap = new LinkedHashMap<String, Map<String, BlackJackAction>>();
        }
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(streamReader);

        boolean isFirstLine = true;
        String[] header = null;

        try {
            for (String line; (line = reader.readLine()) != null; ) {
                if (isFirstLine) {
                    isFirstLine = false;
                    header = line.split("\\s+");
                } else {
                    Map<String, BlackJackAction> row = new LinkedHashMap<String, BlackJackAction>();
                    String[] array = line.split("\\s+");
                    for (int i = 1; i < array.length; i++) {
                        row.put(header[i], BlackJackAction.fromCode(array[i]));
                    }
                    strategyMap.put(array[0], row);
                }
            }
        } catch (IOException e) {
            LOGGER.error("error when load file", e);
            throw new RuntimeException(e);
        }
    }
}
