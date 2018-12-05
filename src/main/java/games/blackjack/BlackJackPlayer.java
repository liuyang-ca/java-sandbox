package games.blackjack;

import games.card.Card;
import games.card.DeckOfCards;
import games.card.Ranks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BlackJackPlayer extends BlackJackDealer {
    private final static Logger LOGGER = LoggerFactory.getLogger(BlackJackPlayer.class);

    private Balance balance;
    private double betAmount;
    private boolean isSettle;

    private List<BlackJackPlayer> children;

    public BlackJackPlayer(Balance balance) {
        this("player", balance);
    }

    public BlackJackPlayer(double balance) {
        this(new Balance(balance));
    }

    public boolean isSettle() {
        return isSettle;
    }

    public BlackJackPlayer(String playerName, Balance balance) {
        super(playerName);
        this.balance = balance;

        // player will auto stand on 21
        super.autoStandRanking = 21;

        children = new ArrayList<BlackJackPlayer>();
    }

    public List<BlackJackPlayer> getChildren() {
        return children;
    }

    public double getBalance() {
        return balance.v;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void bet(double amount) {
        this.betAmount = amount;
        updateBalance(-amount);
    }

    public void clear() {
        super.clear();
        children.clear();
        isSettle = false;
    }

    private void updateBalance(double amount) {
        balance.v = balance.v + amount;
        LOGGER.trace("[{}] updateBalance amount: {}, current balance: {}", name, amount, balance.v);
        if (balance.v < 0) {
            throw new RuntimeException("You don't have enough balance!");
        }
    }

    private void checkBet() {
        if (betAmount == 0) {
            throw new RuntimeException("You have to make a bet first");
        }
    }

    public void hit(Card card) {
        checkBet();
        super.hit(card);
    }

    public void stand() {
        checkBet();
        super.stand();
    }

    private boolean isDoubleDownAllowed() {
        return cards.size() == 2;
    }

    public void doubleDown(Card card) {
        checkBet();
        if (!isDoubleDownAllowed()) {
            throw new RuntimeException("DOUBLE-DOWN only allowed when having 2 cards");
        }
        updateBalance(-betAmount);
        LOGGER.trace("[{}] DOUBLE-DOWN! at {}", name, currentRanking);
        isDoubleDown = true;
        betAmount = betAmount * 2;
        super.hit(card);
        super.stand();
    }

    public BlackJackAction findStrategy(Card dealersFaceUpCard) {
        String playersHand = null;

        if (hasAce && rankings.get(rankings.size()-1) < 21) { // handle soft hand
            // calculate sum of cards without ACE
            int sum = 0;
            boolean isFirstAce = true;
            for(Card card : cards) {
                if (card.getRank() == Ranks.ACE && isFirstAce) {
                    isFirstAce = false;
                } else {
                    sum = sum + card.getRank().getValue();
                }
            }
            playersHand = "A,"+sum;
        } else if (isSplitAllowed() || (cards.size() == 2 && !isPair())) { // handle paris, two cards specific case
            playersHand = cards.get(0).getRank().getCode() + "," + cards.get(1).getRank().getCode();
        } else {
            // handle more than 2 cards or cannot split anymore
            playersHand = String.valueOf(currentRanking);
        }


        BlackJackAction action = BlackJackStrategy.getAction(playersHand, dealersFaceUpCard.getRank().getCode());
        LOGGER.trace("find strategy, players hand: {}, dealers hand: {}, result: {}", playersHand, dealersFaceUpCard.getRank().getCode(), action);
        return action;
    }

    public BlackJackAction findStrategy2(Card dealersFaceUpCard) {
        if (isSplitAllowed() && currentRanking < 18) {
            return BlackJackAction.SPLIT;
        }

        if (isDoubleDownAllowed() && (currentRanking == 10 || currentRanking == 11)) {
            return BlackJackAction.DOUBLE_DOWN_HIT;
        }

        if (currentRanking <= 12) {
            return BlackJackAction.HIT;
        }
        return BlackJackAction.STAND;
    }

    public void play(DeckOfCards deck, BlackJackDealer dealer) {
        LOGGER.trace("------------------ {} start play ------------------", name);

        // play until player stand
        while (!isStand()) {
            switch (findStrategy(dealer.cards.get(1))) {
                case HIT:
                    hit(deck.deal());
                    break;
                case STAND:
                    stand();
                    break;
                case DOUBLE_DOWN_HIT:
                    if (isDoubleDownAllowed()) {
                        doubleDown(deck.deal());
                    } else {
                        hit(deck.deal());
                    }
                    break;
                case DOUBLE_DOWN_STAND:
                    if (isDoubleDownAllowed()) {
                        doubleDown(deck.deal());
                    } else {
                        stand();
                    }
                    break;
                case SPLIT:
                    BlackJackPlayer newPlayer = split(deck.deal(), deck.deal());
                    newPlayer.play(deck, dealer);
                    children.add(newPlayer);
                    break;
            }
        }

        // check for player bust
        if(isBust()) {
            settle(dealer);
        }

        LOGGER.trace("================== {} finish play =================", name);
    }

    private boolean isSplitAllowed() {
        boolean isSplitAllowed = splitCounter.i+1 < SplitCounter.MAX_SPLIT_TIMES && isPair();
        //LOGGER.trace("Checking is split allowed({}), splitCounter: {}, cards: {}", isSplitAllowed, splitCounter.i, cards);
        return isSplitAllowed;
    }

    private boolean isPair() {
        return cards.size() == 2 && cards.get(0).getRank().getValue() == cards.get(1).getRank().getValue();
    }

    public BlackJackPlayer split(Card card1, Card card2) {
        checkBet();

        if (!isSplitAllowed()) {
            throw new RuntimeException("SPLIT not allowed, only two of same card can split up to 3 times!");
        }

        Card splittingCard = cards.get(1);
        LOGGER.trace("[{}] SPLIT! at {}", name, splittingCard.getRank().getValue());
        splitCounter.i++;

        BlackJackPlayer p1 = new BlackJackPlayer(name + "_" + splitCounter.i, balance);

        // bet with p1
        p1.bet(betAmount);
        p1.splitCounter = splitCounter;
        p1.hit(cards.get(0));
        p1.hit(card1);

        // update player
        clearCardsAndRankings();
        hit(splittingCard);
        hit(card2);

        // auto-stand after deal if splitting ACE
        if (splittingCard.getRank().equals(Ranks.ACE)) {
            LOGGER.trace("auto-stand when splitting ACE!");
            p1.stand();
            stand();
        }

        return p1;
    }

    // compare players card with dealer
    public int settle(BlackJackDealer dealer) {
        // settle children if have any
        for (BlackJackPlayer child : children) {
            child.settle(dealer);
        }

        if (isSettle) {
            LOGGER.debug("{} already settled!", name);
            return -2;
        }
        isSettle = true;

        if (!isStand() && !dealer.isStand()) {
            throw new RuntimeException("Player or Dealer has to stand in order to settle!");
        }

        int ranking = getRanking();
        int dealerRanking = dealer.getRanking();

        LOGGER.debug("SETTLE [{}]:{} -- [{}]:{}", name, ranking, dealer.name, dealerRanking);

        if (isBust) {
            // player bust, you lose
            LOGGER.debug("--> {} bust, you *** LOSE{} ***", name, isDoubleDown?" DoubleDown":"");
            return -1;
        }

        if (dealer.isBust()) {
            // dealer bust, you win
            LOGGER.debug("--> {} bust, {} *** WIN{} ***", dealer.name, name, isDoubleDown?" DoubleDown":"");
            updateBalance(betAmount * 2);
            return 1;
        }

        if (ranking == dealerRanking) {
            // push
            LOGGER.debug("--> *** PUSH{} ***", isDoubleDown?" DoubleDown":"");
            updateBalance(betAmount);
            return 0;
        } else if (ranking > dealerRanking) {
            if (isBlackJack) {
                LOGGER.debug("--> {} BlackJack, you *** WIN ***", name);
                // get paid 3:2
                updateBalance(betAmount * 2.5);
            } else {
                LOGGER.debug("--> {} *** WIN{} ***", name, isDoubleDown?" DoubleDown":"");
                updateBalance(betAmount * 2);
            }
            return 1;
        } else {
            LOGGER.debug("--> {} *** LOSE{} ***", name, isDoubleDown?" DoubleDown":"");
            return -1;
        }
    }
}
