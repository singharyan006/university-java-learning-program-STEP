import java.util.Scanner;

public class RockPaperScissorsGame {

    public static String getComputerChoice() {
        int choice = (int) (Math.random() * 3); // 0 - rock, 1 - paper, 2 - scissors
        return switch (choice) {
            case 0 -> "rock";
            case 1 -> "paper";
            default -> "scissors";
        };
    }

    public static String findWinner(String player, String computer) {
        if (player.equals(computer)) return "draw";

        return switch (player) {
            case "rock" -> computer.equals("scissors") ? "player" : "computer";
            case "paper" -> computer.equals("rock") ? "player" : "computer";
            case "scissors" -> computer.equals("paper") ? "player" : "computer";
            default -> "invalid";
        };
    }

    public static String[][] calculateStats(String[][] rounds, int totalGames) {
        int playerWins = 0, computerWins = 0, draws = 0;

        for (String[] round : rounds) {
            if (round[2].equals("player")) playerWins++;
            else if (round[2].equals("computer")) computerWins++;
            else draws++;
        }

        String[][] stats = new String[3][3];

        stats[0][0] = "Player Wins";
        stats[0][1] = String.valueOf(playerWins);
        stats[0][2] = String.format("%.2f", (playerWins * 100.0) / totalGames) + "%";

        stats[1][0] = "Computer Wins";
        stats[1][1] = String.valueOf(computerWins);
        stats[1][2] = String.format("%.2f", (computerWins * 100.0) / totalGames) + "%";

        stats[2][0] = "Draws";
        stats[2][1] = String.valueOf(draws);
        stats[2][2] = String.format("%.2f", (draws * 100.0) / totalGames) + "%";

        return stats;
    }

    public static void displayResults(String[][] rounds, String[][] stats) {
        System.out.printf("%-10s %-15s %-10s%n", "Player", "Computer", "Result");
        System.out.println("----------------------------------------");

        for (String[] round : rounds) {
            System.out.printf("%-10s %-15s %-10s%n", round[0], round[1], round[2]);
        }

        System.out.println("\nWin Statistics:");
        System.out.printf("%-15s %-10s %-10s%n", "Category", "Count", "Percentage");
        System.out.println("----------------------------------------");

        for (String[] stat : stats) {
            System.out.printf("%-15s %-10s %-10s%n", stat[0], stat[1], stat[2]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of games: ");
        int totalGames = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String[][] rounds = new String[totalGames][3];

        for (int i = 0; i < totalGames; i++) {
            System.out.print("Enter your choice (rock/paper/scissors): ");
            String player = scanner.nextLine().toLowerCase();
            String computer = getComputerChoice();

            String result = findWinner(player, computer);
            rounds[i][0] = player;
            rounds[i][1] = computer;
            rounds[i][2] = result;
        }

        String[][] stats = calculateStats(rounds, totalGames);
        displayResults(rounds, stats);
    }
}
