import java.io.Serializable;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;
    private String bookingStatus;

    public Reservation(String reservationId, String guestName, String roomType, String roomId, String bookingStatus) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.bookingStatus = bookingStatus;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    @Override
    public String toString() {
        return "Reservation [Reservation ID: " + reservationId + ", Guest: " + guestName + ", Room Type: " + roomType + ", Room ID: " + roomId + ", Status: " + bookingStatus + "]";
    }
}
