// HW PROBLEM 3: Game and Card Game Objects
// Topic: Overriding Object Methods
// Problem Statement:

// 1

// Create Game class overriding toString() and equals(). Create CardGame extending Game
// and override these methods properly.
// Hints:
// ● Override toString(), equals(), and hashCode()
// ● Call super.toString() in child class override
// ● Test equality between objects


import java.util.Objects;

public class GameTest {

    // Base class Game
    static class Game {
        protected String name;
        protected int players;

        public Game(String name, int players) {
            this.name = name;
            this.players = players;
        }

        @Override
        public String toString() {
            return "Game{name='" + name + "', players=" + players + "}";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Game game = (Game) obj;
            return players == game.players && Objects.equals(name, game.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, players);
        }
    }

    // Subclass CardGame
    static class CardGame extends Game {
        private String deckType;

        public CardGame(String name, int players, String deckType) {
            super(name, players);
            this.deckType = deckType;
        }

        @Override
        public String toString() {
            return super.toString() + ", CardGame{deckType='" + deckType + "'}";
        }

        @Override
        public boolean equals(Object obj) {
            if (!super.equals(obj)) return false;
            if (getClass() != obj.getClass()) return false;
            CardGame that = (CardGame) obj;
            return Objects.equals(deckType, that.deckType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), deckType);
        }
    }

    // Main method to test equality and string representation
    public static void main(String[] args) {
        Game g1 = new Game("Chess", 2);
        Game g2 = new Game("Chess", 2);
        Game g3 = new Game("Monopoly", 4);

        CardGame cg1 = new CardGame("Poker", 4, "Standard");
        CardGame cg2 = new CardGame("Poker", 4, "Standard");
        CardGame cg3 = new CardGame("Poker", 4, "Custom");

        System.out.println("Game Objects:");
        System.out.println(g1);
        System.out.println(g2);
        System.out.println("g1 equals g2: " + g1.equals(g2));
        System.out.println("g1 equals g3: " + g1.equals(g3));

        System.out.println("\nCardGame Objects:");
        System.out.println(cg1);
        System.out.println(cg2);
        System.out.println("cg1 equals cg2: " + cg1.equals(cg2));
        System.out.println("cg1 equals cg3: " + cg1.equals(cg3));
    }
}

