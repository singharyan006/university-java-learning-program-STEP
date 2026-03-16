import java.util.LinkedList;
import java.util.Queue;

public class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        this.requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("[Request Queued] " + reservation.getGuestName() + " requested a " + reservation.getRoomType());
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
    
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public void displayQueueStatus() {
        System.out.println("\n--- Booking Request Queue Status ---");
        System.out.println("Pending Requests: " + requestQueue.size());

        if (!requestQueue.isEmpty()) {
            System.out.println("Queue Order (First to Last):");
            int position = 1;
            for (Reservation req : requestQueue) {
                System.out.println(position + ". Guest: " + req.getGuestName() + " | Room Type: " + req.getRoomType());
                position++;
            }
        } else {
            System.out.println("The queue is currently empty.");
        }
        System.out.println("------------------------------------");
    }
}