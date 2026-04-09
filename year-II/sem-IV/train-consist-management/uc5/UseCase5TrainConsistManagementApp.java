import java.util.LinkedHashSet;
import java.util.Set;

public class UseCase5TrainConsistManagementApp {
    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("Welcome to the Train Consist Management System");
        System.out.println("UC5: Preserve Insertion Order of Bogies");

        Set<String> formation = new LinkedHashSet<>();

        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("Final formation order: " + formation);
        System.out.println("Total unique bogies: " + formation.size());
        System.out.println("Program continues.");
    }
}
