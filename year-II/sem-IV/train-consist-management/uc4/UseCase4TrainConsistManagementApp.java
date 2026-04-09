import java.util.LinkedList;

public class UseCase4TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC4: Maintain Ordered Bogie IDs");

        LinkedList<String> consist = new LinkedList<>();

        consist.addLast("Engine");
        consist.addLast("Sleeper");
        consist.addLast("AC");
        consist.addLast("Cargo");
        consist.addLast("Guard");

        System.out.println("Initial train consist: " + consist);

        consist.add(2, "Pantry Car");
        System.out.println("After inserting Pantry Car at position 2: " + consist);

        consist.removeFirst();
        consist.removeLast();
        System.out.println("After removing first and last bogies: " + consist);

        System.out.println("Final ordered train consist: " + consist);
        System.out.println("Program continues.");
    }
}
