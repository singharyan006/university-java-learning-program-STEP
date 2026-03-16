public abstract class Room {
    protected String roomType;
    protected int numberOfBeds;
    protected double size;
    protected double price;

    public Room(String roomType, int numberOfBeds, double size, double price) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.size = size;
        this.price = price;
    }

    public abstract void displayDetails();
}
