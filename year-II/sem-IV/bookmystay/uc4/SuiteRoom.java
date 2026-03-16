public class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 2, 800.0, 300.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Number of Beds: " + numberOfBeds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price: $" + price + " per night");
    }
}
