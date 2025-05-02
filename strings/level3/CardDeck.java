import java.util.Arrays;

public class CardDeck {

    public static String[] initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] deck = new String[suits.length * ranks.length];
        int index = 0;

        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = rank + " of " + suit;
            }
        }

        return deck;
    }

    public static String[] shuffleDeck(String[] deck) {
        int n = deck.length;
        for (int i = 0; i < n; i++) {
            int randomCardNumber = i + (int) (Math.random() * (n - i));
            String temp = deck[i];
            deck[i] = deck[randomCardNumber];
            deck[randomCardNumber] = temp;
        }
        return deck;
    }

    public static String[][] distributeCards(String[] deck, int numCards, int numPlayers) {
        if (numCards % numPlayers != 0) {
            System.out.println("The cards cannot be evenly distributed among players.");
            return null;
        }

        String[][] players = new String[numPlayers][numCards / numPlayers];

        int cardIndex = 0;
        for (int i = 0; i < numPlayers; i++) {
            for (int j = 0; j < numCards / numPlayers; j++) {
                players[i][j] = deck[cardIndex++];
            }
        }

        return players;
    }

    public static void printPlayersCards(String[][] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println("Player " + (i + 1) + "'s Cards: " + Arrays.toString(players[i]));
        }
    }

    public static void main(String[] args) {
        String[] deck = initializeDeck();
        deck = shuffleDeck(deck);

        int numCards = 52;
        int numPlayers = 4;  // You can change the number of players here

        String[][] players = distributeCards(deck, numCards, numPlayers);

        if (players != null) {
            printPlayersCards(players);
        }
    }
}
