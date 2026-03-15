public class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 400.0, 150.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Number of Beds: " + numberOfBeds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price: $" + price + " per night");
    }
}
