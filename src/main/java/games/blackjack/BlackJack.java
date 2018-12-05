package games.blackjack;

import games.card.Card;
import games.card.DeckOfCards;
import games.card.Ranks;

public class BlackJack {
    private DeckOfCards deck;
    private BlackJackDealer dealer;
    private BlackJackPlayer player;

    public DeckOfCards getDeck() {
        return deck;
    }

    public BlackJackDealer getDealer() {
        return dealer;
    }

    public BlackJackPlayer getPlayer() {
        return player;
    }

    public BlackJack(int numberOfDeck, double playerBalance) {
        deck = new DeckOfCards(numberOfDeck);
        player = new BlackJackPlayer(playerBalance);
        dealer = new BlackJackDealer();
    }

    // play with given scenario
    public void play(double betAmount, String playersHand, String dealersFaceUpCard) {

        // if players hand has Pairs or Ace
        if (playersHand.contains(",")) {
            String[] array = playersHand.split(",");
            play(betAmount,
                    deck.deal(Ranks.fromCode(array[0])),
                    deck.deal(),
                    deck.deal(Ranks.fromCode(array[1])),
                    deck.deal(Ranks.fromCode(dealersFaceUpCard)));
        } else {
            // player hand is hard
            int sum = Integer.valueOf(playersHand);
            Card playerCard1 = null;
            switch (sum) {
                case 19:
                case 18:
                    playerCard1 = deck.deal(Ranks.fromValue(10));
                    break;

                case 17:
                case 16:
                    playerCard1 = deck.deal(Ranks.fromValue(9), Ranks.fromValue(10));
                    break;

                case 15:
                case 14:
                    playerCard1 = deck.deal(Ranks.fromValue(8), Ranks.fromValue(10));
                    break;

                case 13:
                case 12:
                    playerCard1 = deck.deal(Ranks.fromValue(7), Ranks.fromValue(10));
                    break;

                case 11:
                    playerCard1 = deck.deal(Ranks.fromValue(6), Ranks.fromValue(9));
                    break;

                case 10:
                    playerCard1 = deck.deal(Ranks.fromValue(6), Ranks.fromValue(8));
                    break;

                case 9:
                    playerCard1 = deck.deal(Ranks.fromValue(5), Ranks.fromValue(7));
                    break;

                case 8:
                    playerCard1 = deck.deal(Ranks.fromValue(5), Ranks.fromValue(6));
                    break;

                case 7:
                    playerCard1 = deck.deal(Ranks.fromValue(4), Ranks.fromValue(5));
                    break;

                case 6:
                    playerCard1 = deck.deal(Ranks.fromValue(4));
                    break;

                case 5:
                    playerCard1 = deck.deal(Ranks.fromValue(3));
                    break;

                default:
                    throw new RuntimeException("invalid players hard hand: " + sum);
            }

            play(betAmount,
                    playerCard1,
                    deck.deal(),
                    deck.deal(Ranks.fromValue(sum - playerCard1.getRank().getValue())),
                    deck.deal(Ranks.fromCode(dealersFaceUpCard)));

        }
    }

    public void play(double betAmount) {
        play(betAmount, deck.deal(), deck.deal(), deck.deal(), deck.deal());
    }

    private void play(double betAmount, Card playerCard1, Card dealerCard1, Card playerCard2, Card dealerCard2) {
        // shuffle before each play
        deck.reset();

        player.clear();
        dealer.clear();

        player.bet(betAmount);

        // player get 1st card
        player.hit(playerCard1);

        // dealer get 1st card
        dealer.hit(dealerCard1);

        // player get 2nd card
        player.hit(playerCard2);

        // dealer get 2nd card
        dealer.hit(dealerCard2);

        // check BlackJack for player or dealer
        if (player.isBlackJack() || dealer.isBlackJack()) {
            player.settle(dealer);
            return;
        }

        // ********** Game Setup Finish, NOW Play **************

        // player play until stand
        player.play(deck, dealer);

        // deal with player bust
        if (player.isSettle()) {
            return;
        }

        // if dealer not stand, then keep hitting
        while (!dealer.isStand()) {
            dealer.hit(deck.deal());
        }

        // ********** Game Play Finish, Now settle ************

        player.settle(dealer);
    }
}
