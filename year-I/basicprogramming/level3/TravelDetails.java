import java.util.*;

public class TravelDetails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking user inputs
        System.out.print("Enter your name: ");
        String name = sc.next();

        System.out.print("Enter starting city: ");
        String fromCity = sc.next();

        System.out.print("Enter via city: ");
        String viaCity = sc.next();

        System.out.print("Enter destination city: ");
        String toCity = sc.next();

        System.out.print("Enter distance from " + fromCity + " to " + viaCity + " (miles): ");
        double fromToVia = sc.nextDouble();

        System.out.print("Enter distance from " + viaCity + " to " + toCity + " (miles): ");
        double viaToFinalCity = sc.nextDouble();

        System.out.print("Enter time taken from " + fromCity + " to " + viaCity + " (minutes): ");
        double timeFromToVia = sc.nextDouble();

        System.out.print("Enter time taken from " + viaCity + " to " + toCity + " (minutes): ");
        double timeViaToFinalCity = sc.nextDouble();

        double totalDistance = (fromToVia + viaToFinalCity) * 1.60; // Converting miles to km
        double totalTime = timeFromToVia + timeViaToFinalCity; // Calculating the total time taken 

        System.out.println("The Total Distance travelled by " + name + " from " + fromCity + " to " + toCity + " via " + viaCity + " is " + totalDistance + " km and the Total Time taken is " + totalTime + " minutes.");

        sc.close();
    }
}
