package games.card;

import games.analytics.GameCounter;
import games.analytics.GameResult;
import games.blackjack.*;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Sandbox {
    private final static Logger LOGGER = LoggerFactory.getLogger(Sandbox.class);
    @Test
    public void Sandbox() {
        System.out.println(findMedianSortedArrays(new int[]{1,1,1,1,1,1,1,1,1,1,4,4}, new int[]{1,3,4,4,4,4,4,4,4,4,4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<Integer>();
        int i=0, j=0;
        while(true) {
            System.out.println("i = " + i + ", j = " + j);
            if (i == nums1.length && j == nums2.length) {
                break;
            } else if (i == nums1.length) {
                list.add(nums2[j++]);
            } else if (j == nums2.length) {
                list.add(nums1[i++]);
            } else {
                if (nums1[i] < nums2[j]) {
                    list.add(nums1[i]);
                    i++;
                } else if (nums1[i] == nums2[j]) {
                    list.add(nums1[i]);
                    list.add(nums1[i]);
                    i++;
                    j++;
                } else {
                    list.add(nums2[j]);
                    j++;
                }
            }
        }
        System.out.println(list);
        int size = list.size();
        if (size%2 == 0) {
            return (list.get(size/2-1) + list.get(size/2))/2.0;
        } else {
            return list.get(size/2);
        }
    }


    /*  lose  1 2 3 4  5  6  7   8   9
     *      1 2 4 8 16 32 64 128 256 512
     */
    @Test
    public void playBlackJackTestGroup() {
        int highestBet = 0;
        int numberOfRuns = 10;
        int numberOfAvgRuns = 10;

        for (int j=0; j<numberOfRuns; j++) {
            double avgBalance = 0.0;
            double avgLow = 0.0;
            double avgHigh = 0.0;
            long avgLosingStrike = 0;
            long avgWinningStrike = 0;
            long avgDrawStrike = 0;
            long highestLosingStrike = 0;
            long highestWinningStrike = 0;
            long highestDrawStrike = 0;
            for (int i = 0; i < numberOfAvgRuns; i++) {
                GameCounter gc = playBlackJackTest(highestBet);
                avgBalance = avgBalance + gc.finalBalance;
                avgLow = avgLow + gc.lowestBalance;
                avgHigh = avgHigh + gc.highestBalance;
                avgLosingStrike = avgLosingStrike + gc.losingStreak;
                avgWinningStrike = avgWinningStrike + gc.winningStreak;
                avgDrawStrike = avgDrawStrike + gc.drawStreak;

                if (highestLosingStrike < gc.losingStreak) {
                    highestLosingStrike = gc.losingStreak;
                }

                if (highestWinningStrike < gc.winningStreak) {
                    highestWinningStrike = gc.winningStreak;
                }

                if (highestDrawStrike < gc.drawStreak) {
                    highestDrawStrike = gc.drawStreak;
                }
            }
            System.out.printf("Highest Bet: %d, Avg: %.2f, High: %.2f, Low: %.2f\n", highestBet, avgBalance/numberOfAvgRuns, avgHigh/numberOfAvgRuns, avgLow/numberOfAvgRuns);
            System.out.printf("Avg losingStrike: %d, winningStrike: %d, drawStrike: %d\n", avgLosingStrike/numberOfAvgRuns, avgWinningStrike/numberOfAvgRuns, avgDrawStrike/numberOfAvgRuns);
            System.out.printf("Highest losingStrike: %d, winningStrike: %d, drawStrike: %d\n\n", highestLosingStrike, highestWinningStrike, highestDrawStrike);
            //highestBet = highestBet * 2;

            if (numberOfRuns >= 5) {
                BlackJackStrategy.clear();
                BlackJackStrategy.loadTmp();
            }
        }
    }

    @Test
    public void playBlackJackSeveralTimes() {
        LogManager.getRootLogger().setLevel(Level.DEBUG);
        BlackJack blackJack = new BlackJack(6, 10000000);
        for (int i=0; i<10; i++) {
            blackJack.play(1);
            LOGGER.debug("bet amount {}, player balance: {}\n\n", blackJack.getPlayer().getBetAmount(), blackJack.getPlayer().getBalance());
        }
    }

    @Test
    public void playBlackJackTestOneTime() {
        LogManager.getRootLogger().setLevel(Level.DEBUG);
        playBlackJackTest(128);
    }

    @Test
    public void twoCardtrainOnce() {
        trainTwoCard("10,3", "7");
        trainTwoCard("9,4",  "7");
        trainTwoCard("8,5",  "7");
        trainTwoCard("7,6",  "7");
    }


    @Test
    public void trainOnce() {
        //LogManager.getRootLogger().setLevel(Level.TRACE);
        //trainTwoCard("8,8", "2");
        BlackJackStrategy.loadStrategy();
        trainTwoCard("15", "7");
    }

    @Test
    public void playOnce() {
        LogManager.getRootLogger().setLevel(Level.TRACE);
        BlackJackStrategy.loadStrategy();
        BlackJack blackJack = new BlackJack(6, 10000000);
        BlackJackStrategy.overrideDefaultAction("15", "7", BlackJackAction.STAND);
        for(int i=0; i<10; i++) {
            blackJack.play(1, "15", "7");
            System.out.println();
        }
    }

    @Test
    public void train() {
        //LogManager.getRootLogger().setLevel(Level.TRACE);

        // train 16, 2
        String playersHand = "12";
        String dealersFaceUpCard = "2";

        BlackJackStrategy.loadStrategy();

        for(int i=19; i>=5; i--) {
            for(int j=2; j<=11; j++) {
                trainTwoCard(String.valueOf(i), j==11 ? "A" : String.valueOf(j));
            }
        }

        // have ACE
        for(int i=9; i>=2; i--) {
            for(int j=2; j<=11; j++) {
                trainTwoCard("A,"+String.valueOf(i), j==11 ? "A" : String.valueOf(j));
            }
        }

        // pairs
        for(int i=11; i>=2; i--) {
            String code = i==11 ? "A" : String.valueOf(i);
            for(int j=2; j<=11; j++) {
                trainTwoCard(code+","+code, j==11 ? "A" : String.valueOf(j));
            }
        }

        // two cards
        BlackJackStrategy.load2Cards();
        for(int i=9; i>=2; i--) {
            for (int j = 10; j >= i + 1; j--) {
                for(int k=2; k<=11; k++) {
                    trainTwoCard(i+","+j, k==11 ? "A" : String.valueOf(k));
                }
            }
        }
        BlackJackStrategy.printStrategy();
    }

    private void trainTwoCard(String playersHand, String dealersFaceUpCard) {
        List<String> list = new ArrayList<String>(Arrays.asList("H", "S", "Dh", "Ds"));
        if (playersHand.contains(",")) {
            String[] array = playersHand.split(",");
            if (array[0].equals(array[1])) {
                list.add("SP");
            }
        }

        Map<String, Double> balanceMap = new HashMap<String, Double>();

        BlackJackAction action = null;
        int strikeCounter = 0;
        int maxLoopSize = 1000;
        while (true) {
            for (String actionCode : list) {
                BlackJackStrategy.overrideDefaultAction(playersHand, dealersFaceUpCard, BlackJackAction.fromCode(actionCode));

                Double balance = balanceMap.get(actionCode);
                if (balance == null) {
                    balanceMap.put(actionCode, 10000000d);
                }

                BlackJack blackJack = new BlackJack(6, balanceMap.get(actionCode));
                for (int i = 0; i < maxLoopSize; i++) {
                    blackJack.play(1, playersHand, dealersFaceUpCard);
                    LOGGER.debug("bet amount {}, player balance: {}\n\n", blackJack.getPlayer().getBetAmount(), blackJack.getPlayer().getBalance());
                }
                balanceMap.put(actionCode, blackJack.getPlayer().getBalance());
                LOGGER.info("{} {}: code: {}, player balance: {}", playersHand, dealersFaceUpCard, actionCode, blackJack.getPlayer().getBalance());
            }

            // sort balance according to value
            BlackJackAction newAction = BlackJackAction.fromCode(String.valueOf(sortByValue(balanceMap).keySet().toArray()[balanceMap.size()-1]));

            if (newAction.equals(action)) {
                strikeCounter++;
            } else {
                strikeCounter = 0;
                action = newAction;
                maxLoopSize = maxLoopSize + 1000;
            }

            LOGGER.info("{} {}: action: {}, strikeCounter: {}, maxLoopSize: {}\n", playersHand, dealersFaceUpCard, action, strikeCounter, maxLoopSize);

            if (strikeCounter == 9) {
                break;
            }
        }

        BlackJackStrategy.overrideDefaultAction(playersHand, dealersFaceUpCard, action);
        LOGGER.info("{} {}: should *** {} ***\n\n", playersHand, dealersFaceUpCard, action);
    }

    private GameCounter playBlackJackTest(int highestBet) {
        double originalBetAmount = 1d;
        double betAmount = originalBetAmount;

        GameCounter gameCounter = new GameCounter();

        double initBalance = 1000000;

        gameCounter.highestBalance = 0d;
        gameCounter.lowestBalance = initBalance;
        double previousBalance = initBalance;
        GameResult previousResult = GameResult.DRAW;
        BlackJack blackJack = new BlackJack(6, initBalance);

        long winningStreakCounter = 1;
        long losingStreakCounter = 1;
        long drawStreakCounter = 1;

        for(int i=0; i<1000000; i++) {
            blackJack.play(betAmount);
            double currentBalance = blackJack.getPlayer().getBalance();
            LOGGER.debug("bet amount {}, player balance: {}", blackJack.getPlayer().getBetAmount(), currentBalance);

            GameResult currentResult;
            if (previousBalance == currentBalance) {
                gameCounter.drawCounter++;
                currentResult = GameResult.DRAW;
            } else if (previousBalance > currentBalance) {
                gameCounter.loseCounter++;
                currentResult = GameResult.LOSE;
            } else {
                gameCounter.winCounter++;
                currentResult = GameResult.WIN;
            }

            if (previousResult == currentResult) {
                if (currentResult == GameResult.WIN) {
                    if (++winningStreakCounter > gameCounter.winningStreak){
                        gameCounter.winningStreak = winningStreakCounter;
                    }
                } else  if (currentResult == GameResult.LOSE) {
                    if (++losingStreakCounter > gameCounter.losingStreak){
                        gameCounter.losingStreak = losingStreakCounter;
                    }
                } else {
                    if (++drawStreakCounter > gameCounter.drawStreak){
                        gameCounter.drawStreak = drawStreakCounter;
                    }
                }
            } else {
                winningStreakCounter = 1;
                losingStreakCounter = 1;
                drawStreakCounter = 1;
            }

            if (highestBet != 0) {
                if (currentResult == GameResult.LOSE && betAmount <= highestBet/2d) {
                    betAmount = betAmount*2;
                } else if (currentResult == GameResult.WIN) {
                    betAmount = originalBetAmount;
                }
//                else {
//                    betAmount = originalBetAmount;
//                }
            }

            previousBalance = currentBalance;
            previousResult = currentResult;

            if (gameCounter.highestBalance < currentBalance) {
                gameCounter.highestBalance = currentBalance;
            }

            if (gameCounter.lowestBalance > currentBalance) {
                gameCounter.lowestBalance = currentBalance;
            }
            LOGGER.debug("losingStreakCounter: {}\n\n", losingStreakCounter);
        }
        gameCounter.finalBalance = previousBalance;

        LOGGER.debug("game counter: {}", gameCounter);

        return gameCounter;
    }

    @Test
    public void SearchTest() {
        DeckOfCards deck = new DeckOfCards();
        System.out.println("deck size: " + deck.size());

        BlackJackPlayer player = new BlackJackPlayer(10000);
        player.hit(deck.deal(Ranks.EIGHT));
        player.hit(deck.deal(Ranks.SIX));

        BlackJackDealer dealer = new BlackJackDealer("dealer");
        Card dealerFirstCard = deck.deal(Ranks.SIX);


        int winCounter = 0;
        int loseCounter = 0;
        int drawCounter = 0;
        while(true) {
            dealer.hit(dealerFirstCard);
            while(dealer.getRanking() <= 16) {
                dealer.hit(deck.deal());
            }
            int result = player.settle(dealer);
            if (result > 0) {
                winCounter ++;
            } else if (result == 0) {
                drawCounter ++;
            } else {
                loseCounter ++;
            }

            dealer.clear();

            System.out.printf("win: %d, lose: %d, draw: %d\n", winCounter, loseCounter, drawCounter);
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;

    }
}

