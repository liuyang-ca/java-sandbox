package games.card;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeckOfCards {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeckOfCards.class);

    private Stack<Card> deck = new Stack<Card>();
    private int numberOfDecks;

    // is this needed?
    private Stack<Card> dump = new Stack<Card>();

    public DeckOfCards(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        for (int i = 0; i < numberOfDecks; i++) {
            for (Suits suits : Suits.values()) {
                for (Ranks ranks : Ranks.values()) {
                    deck.push(new Card(suits, ranks));
                }
            }
        }

        shuffle();
    }

    public DeckOfCards() {
        this(1);
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void shuffle() {
        LOGGER.trace("shuffle deck...");
        Collections.shuffle(deck);
    }

    public Card deal() {
        try {
            Card card = deck.pop();
            dump.push(card);
            return card;
        } catch (EmptyStackException e) {
            return null;
        }
    }

    public Card deal(Ranks rank) {
        for (Card card : deck) {
            if (card.getRank() == rank) {
                deck.remove(card);
                dump.push(card);
                LOGGER.trace("searching {}, found {}", rank, card);
                return card;
            }
        }
        throw new RuntimeException("searching " + rank + ", found nothing");
    }

    public Card deal(Ranks rank1, Ranks rank2) {
        // if rank1 is bigger, then swap
        if (rank1.getValue() > rank2.getValue()) {
            Ranks tmp = rank1;
            rank1 = rank2;
            rank2 = tmp;
        }

        for (Card card : deck) {
            if (card.getRank().getValue() <= rank2.getValue() && card.getRank().getValue() >= rank1.getValue() ) {
                deck.remove(card);
                dump.push(card);
                LOGGER.trace("searching {}-{}, found {}", rank1, rank2, card);
                return card;
            }
        }
        throw new RuntimeException("searching " + rank1 + "-" + rank2 + ", found nothing");
    }

    // move all card from dump to deck
    public void reset() {
        LOGGER.trace("deck size: {}, dump size: {}", size(), dumpSize());
        for (Card card : dump) {
            deck.push(card);
        }
        dump.clear();
        shuffle();
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public int size() {
        return deck.size();
    }

    public int dumpSize() {
        return dump.size();
    }
}
