import java.util.LinkedList;
import java.util.Queue;

public class BookingRequestQueue {
    private Queue<Reservation> requestQueue;
    private boolean acceptingRequests;

    public BookingRequestQueue() {
        this.requestQueue = new LinkedList<>();
        this.acceptingRequests = true;
    }

    public synchronized void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("[Queued] " + Thread.currentThread().getName() + " -> " + reservation.getGuestName() + " requested " + reservation.getRoomType());
        notifyAll();
    }

    public synchronized Reservation getNextRequest() {
        while (requestQueue.isEmpty() && acceptingRequests) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        if (requestQueue.isEmpty() && !acceptingRequests) {
            return null;
        }

        return requestQueue.poll();
    }

    public synchronized void closeIntake() {
        acceptingRequests = false;
        notifyAll();
    }

    public synchronized int pendingCount() {
        return requestQueue.size();
    }
}
