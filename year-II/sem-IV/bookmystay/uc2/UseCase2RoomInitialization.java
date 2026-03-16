public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v2.0");
        System.out.println("======================================");

        // Initialize room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Store room availability using simple variables
        int singleRoomAvailability = 10;
        int doubleRoomAvailability = 15;
        int suiteRoomAvailability = 5;

        // Display room details and availability
        System.out.println("\n--- Room Details and Availability ---\n");
        
        singleRoom.displayDetails();
        System.out.println("Available: " + singleRoomAvailability);
        System.out.println("--------------------------------------");

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleRoomAvailability);
        System.out.println("--------------------------------------");

        suiteRoom.displayDetails();
        System.out.println("Available: " + suiteRoomAvailability);
        System.out.println("--------------------------------------");
    }
}
