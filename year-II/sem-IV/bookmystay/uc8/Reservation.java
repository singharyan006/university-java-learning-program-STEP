public class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType) {
        this.reservationId = "PENDING";
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = "UNASSIGNED";
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

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void assignRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Reservation [Reservation ID: " + reservationId + ", Guest: " + guestName + ", Room Type: " + roomType + ", Room ID: " + roomId + "]";
    }
}
