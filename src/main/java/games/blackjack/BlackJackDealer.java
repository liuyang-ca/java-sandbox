package games.blackjack;

import games.card.Card;
import games.card.Ranks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackDealer {
    private final static Logger LOGGER = LoggerFactory.getLogger(BlackJackDealer.class);

    protected List<Card> cards = new ArrayList<Card>();
    protected List<Integer> rankings = new ArrayList<Integer>();
    protected int currentRanking;
    protected String name;
    protected boolean isStand;
    protected boolean isBust;
    protected boolean isBlackJack;
    protected boolean isDoubleDown;
    protected boolean hasAce;
    protected SplitCounter splitCounter;

    // dealer will stand >= 17
    protected int autoStandRanking = 17;

    public BlackJackDealer() {
        this("dealer");
    }

    public BlackJackDealer(String name) {
        this.name = name;
        rankings.add(0);
    }

    protected void clearCardsAndRankings() {
        cards.clear();
        rankings.clear();
        rankings.add(0);
        hasAce = false;
    }

    public void clear() {
        clearCardsAndRankings();
        isStand = false;
        isBust = false;
        isBlackJack = false;
        isDoubleDown = false;

        if (splitCounter == null) {
            splitCounter = new SplitCounter();
        }
        splitCounter.i = 0;
    }

    public void stand() {
        if (!isStand) {
            LOGGER.trace("[{}] STAND! at {}", name, currentRanking);
            isStand = true;
        }
    }

    public boolean isStand() {
        return isStand;
    }

    public boolean isBust() {
        return isBust;
    }

    public boolean isBlackJack() {
        return isBlackJack;
    }

    // return true when hit okay, otherwise return false
    public void hit(Card card) {
        if (card == null) {
            throw new RuntimeException("card cannot be null");
        }

        if (isStand) {
            throw new RuntimeException("[" + name + "] cannot hit, already stand");
        }

        cards.add(card);

        Ranks rank = card.getRank();
        for(int i = 0; i< rankings.size(); i++) {
            rankings.set(i, rankings.get(i) + rank.getValue());
        }

        if (rank == Ranks.ACE) {
            hasAce = true;
            List<Integer> tmpStandings = new ArrayList<Integer>();
            for(int standing : rankings) {
                tmpStandings.add(standing + 10);
            }
            rankings.addAll(tmpStandings);
        }

        Collections.sort(rankings);

        currentRanking = getRanking();

        if (currentRanking == 21 && cards.size() == 2 && splitCounter.i == 0) {
            isBlackJack = true;
        }

        LOGGER.trace("[{}] HIT! {} at {}", name, card.getRank(), currentRanking);

        if (currentRanking >= autoStandRanking) {
            stand();
        }
    }

    // get ranking
    public int getRanking() {
        // try to get max valid ranking
        for(int i = rankings.size()-1; i>=0; i--) {
            int ranking = rankings.get(i);
            if (ranking <= 21) {
                return ranking;
            }
        }

        // if no valid ranking, must bust, then return smallest bust number
        isBust = true;
        return rankings.get(0);
    }
}
