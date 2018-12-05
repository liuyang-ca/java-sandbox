package games.card;

import games.blackjack.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class StrategyTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(StrategyTest.class);

    @Test
    public void printStrategyMap() {
        Map<String, Map<String, BlackJackAction>> totals = new LinkedHashMap<String, Map<String, BlackJackAction>>();

        // add hard totals
        for (int i=20; i>=5; i--) {
            Map<String, BlackJackAction> row = new LinkedHashMap<String, BlackJackAction>();
            for (int j=2; j<=11; j++) {
                String key = j==11 ? "A" : String.valueOf(j);

                if (i >= 17) {
                    row.put(key, BlackJackAction.STAND);
                } else if (i >= 12 && i <=16) {
                    if (j <= 6) {
                        if (i == 12 && j <=3 ) {
                            row.put(key, BlackJackAction.HIT);
                        } else {
                            row.put(key, BlackJackAction.STAND);
                        }
                    } else {
                        row.put(key, BlackJackAction.HIT);
                    }
                } else if (i == 11 || i == 10) {
                    if (i == 10 && j >= 10) {
                        row.put(key, BlackJackAction.HIT);
                    } else {
                        row.put(key, BlackJackAction.DOUBLE_DOWN_HIT);
                    }
                } else {
                    if (i == 9 && (j >=3 && j<=6)) {
                        row.put(key, BlackJackAction.DOUBLE_DOWN_HIT);
                    }
                    row.put(key, BlackJackAction.HIT);
                }
            }
            totals.put(String.valueOf(i), row);
        }

        // add soft totals
        for (int i=9; i>=2; i--) {
            Map<String, BlackJackAction> row = new LinkedHashMap<String, BlackJackAction>();
            for (int j=2; j<=11; j++) {
                String key = j==11 ? "A" : String.valueOf(j);
                if (i >= 8) {
                    if (i == 8 && j == 6) {
                        row.put(key, BlackJackAction.DOUBLE_DOWN_STAND);
                    } else {
                        row.put(key, BlackJackAction.STAND);
                    }
                } else {
                    if (j <= 6) {
                        row.put(key, BlackJackAction.DOUBLE_DOWN_HIT);
                    } else {
                        row.put(key, BlackJackAction.HIT);
                    }
                }
            }
            totals.put("A,"+i, row);
        }


        // add pairs
        for (int i=11; i>=2; i--) {
            Map<String, BlackJackAction> row = new LinkedHashMap<String, BlackJackAction>();
            for (int j=2; j<=11; j++) {
                String key = j==11 ? "A" : String.valueOf(j);
                if (i == 10) {
                    row.put(key, BlackJackAction.STAND);
                } else if (i >= 8) {
                    row.put(key, BlackJackAction.SPLIT);
                } else {
                    if (j <=6 ) {
                        row.put(key, BlackJackAction.SPLIT);
                    } else {
                        row.put(key, BlackJackAction.HIT);
                    }
                }
            }
            String key = i==11 ? "A" : String.valueOf(i);
            totals.put(key+","+key, row);
        }

        // print header
        for(String s : Arrays.asList(" ", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A")) {
            System.out.print(String.format("%-7s", s));
        }
        System.out.println();

        // print
        for (String playerHand : totals.keySet()) {
            System.out.print(String.format("%-7s", playerHand));
            Map<String, BlackJackAction> row = totals.get(playerHand);
            for (String j : row.keySet()) {
                System.out.print(String.format("%-7s", row.get(j).getCode()));
            }
            System.out.println();
        }
    }

    @Test
    public void printStrategy() {
        BlackJackStrategy.overrideDefaultAction("10", "9", BlackJackAction.STAND);
        BlackJackStrategy.overrideDefaultAction("7,3", "9", BlackJackAction.STAND);
        BlackJackStrategy.printStrategy();
    }

    @Test
    public void overrideDefaultActionTest() {
        BlackJackAction action = BlackJackStrategy.getAction("10", "9");
        System.out.println("Default Action: " + action);
        BlackJackStrategy.overrideDefaultAction("10", "9", BlackJackAction.STAND);

        action = BlackJackStrategy.getAction("2,3", "3");
        System.out.println("Override Action: " + action);
    }

    @Test
    public void getActionTest() {
        BlackJackAction action = BlackJackStrategy.getAction("10", "9");
        System.out.println("Get Action: " + action);

        BlackJackStrategy.overrideDefaultAction("6,4", "9", BlackJackAction.HIT);

        action = BlackJackStrategy.getAction("6,4", "9");
        System.out.println("Get Action: " + action);

        action = BlackJackStrategy.getAction("4,6", "9");
        System.out.println("Get Action: " + action);

        action = BlackJackStrategy.getAction("2,3", "3");
        System.out.println("Get Action: " + action);
    }

    @Test
    public void printPlayersHand() {
        for(int i=9; i>=2; i--) {
            for(int j=10; j>=i+1; j--) {
                String playersHand = i+","+j;
                for (String dealersFaceUpCard: Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "A")) {
                    System.out.println("playersHand: " + playersHand + ", dealersFaceUpCard:" + dealersFaceUpCard);
                    BlackJackAction action = BlackJackStrategy.getAction(playersHand, dealersFaceUpCard);
                    BlackJackStrategy.overrideDefaultAction(playersHand, dealersFaceUpCard, action);
                }
            }
        }

        BlackJackStrategy.printStrategy();
    }
}
