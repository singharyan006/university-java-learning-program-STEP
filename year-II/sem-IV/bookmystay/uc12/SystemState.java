import java.io.Serializable;

public class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    private RoomInventory inventory;
    private BookingHistory bookingHistory;

    public SystemState(RoomInventory inventory, BookingHistory bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }

    public RoomInventory getInventory() {
        return inventory;
    }

    public BookingHistory getBookingHistory() {
        return bookingHistory;
    }
}
