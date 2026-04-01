import java.util.ArrayList;
import java.util.List;

public class ClientRiskScoreRanking {

    static class Client {
        final String id;
        final int riskScore;
        final double accountBalance;

        Client(String id, int riskScore, double accountBalance) {
            this.id = id;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return id + "(" + riskScore + ", bal=" + accountBalance + ")";
        }
    }

    public static int bubbleSortRiskAscending(Client[] clients) {
        int swaps = 0;
        int n = clients.length;

        for (int i = 0; i < n - 1; i++) {
            boolean changed = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }
        return swaps;
    }

    public static void insertionSortRiskDescThenBalance(Client[] clients) {
        for (int i = 1; i < clients.length; i++) {
            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 && compareForDesc(clients[j], key) > 0) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }
    }

    private static int compareForDesc(Client a, Client b) {
        if (a.riskScore != b.riskScore) {
            return Integer.compare(b.riskScore, a.riskScore);
        }
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    public static List<Client> topHighestRisk(Client[] sortedDesc, int topN) {
        List<Client> top = new ArrayList<>();
        int limit = Math.min(topN, sortedDesc.length);
        for (int i = 0; i < limit; i++) {
            top.add(sortedDesc[i]);
        }
        return top;
    }

    public static void main(String[] args) {
        Client[] sample = {
            new Client("clientC", 80, 6000),
            new Client("clientA", 20, 3000),
            new Client("clientB", 50, 1500)
        };

        Client[] bubbleInput = sample.clone();
        int swaps = bubbleSortRiskAscending(bubbleInput);
        System.out.println("Bubble (asc):");
        for (Client client : bubbleInput) {
            System.out.println(client.id + ":" + client.riskScore);
        }
        System.out.println("Swaps=" + swaps);

        Client[] insertionInput = sample.clone();
        insertionSortRiskDescThenBalance(insertionInput);
        System.out.println("Insertion (desc risk + balance):");
        for (Client client : insertionInput) {
            System.out.println(client.id + ":" + client.riskScore);
        }

        System.out.println("Top 3 risks: " + topHighestRisk(insertionInput, 3));
    }
}
