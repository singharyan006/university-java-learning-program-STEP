import java.util.Stack;

public class CancellationService {
    private Stack<String> rollbackRoomStack;

    public CancellationService() {
        rollbackRoomStack = new Stack<>();
    }

    public void cancelReservation(String reservationId, BookingHistory bookingHistory, RoomInventory inventory)
            throws InvalidCancellationException {

        Reservation reservation = bookingHistory.getReservationById(reservationId);
        if (reservation == null) {
            throw new InvalidCancellationException("Cannot cancel. Reservation does not exist: " + reservationId);
        }

        if (reservation.isCancelled()) {
            throw new InvalidCancellationException("Cannot cancel. Reservation already cancelled: " + reservationId);
        }

        if ("UNASSIGNED".equals(reservation.getRoomId())) {
            throw new InvalidCancellationException("Cannot cancel. Reservation has no allocated room: " + reservationId);
        }

        String roomIdToRelease = reservation.getRoomId();
        rollbackRoomStack.push(roomIdToRelease);

        inventory.releaseRoom(reservation.getRoomType(), roomIdToRelease);
        bookingHistory.markReservationCancelled(reservationId);
    }

    public void displayRollbackStack() {
        System.out.println("\n--- Rollback Stack (Most Recent Release on Top) ---");
        if (rollbackRoomStack.isEmpty()) {
            System.out.println("No cancelled rooms recorded yet.");
        } else {
            for (int i = rollbackRoomStack.size() - 1; i >= 0; i--) {
                System.out.println((rollbackRoomStack.size() - i) + ". " + rollbackRoomStack.get(i));
            }
        }
        System.out.println("---------------------------------------------------");
    }
}
