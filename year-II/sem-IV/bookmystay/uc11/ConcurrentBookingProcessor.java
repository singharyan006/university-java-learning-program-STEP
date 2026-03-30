import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBookingProcessor implements Runnable {
    private BookingRequestQueue queue;
    private RoomInventory inventory;
    private BookingHistory history;
    private AtomicInteger reservationCounter;

    public ConcurrentBookingProcessor(BookingRequestQueue queue, RoomInventory inventory, BookingHistory history, AtomicInteger reservationCounter) {
        this.queue = queue;
        this.inventory = inventory;
        this.history = history;
        this.reservationCounter = reservationCounter;
    }

    @Override
    public void run() {
        while (true) {
            Reservation request = queue.getNextRequest();
            if (request == null) {
                break;
            }

            String roomId = inventory.allocateRoom(request.getRoomType());
            if (roomId == null) {
                request.markRejected();
                System.out.println("[Rejected] " + Thread.currentThread().getName() + " -> " + request.getGuestName() + " (No " + request.getRoomType() + " available)");
                continue;
            }

            String reservationId = "RES-" + reservationCounter.incrementAndGet();
            request.setReservationId(reservationId);
            request.setRoomId(roomId);
            request.markConfirmed();
            history.addConfirmedReservation(request);

            System.out.println("[Confirmed] " + Thread.currentThread().getName() + " -> " + request.getGuestName() + " assigned " + roomId);
        }
    }
}
