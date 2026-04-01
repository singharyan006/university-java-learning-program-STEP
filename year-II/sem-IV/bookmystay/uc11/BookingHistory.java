import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingHistory {
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        this.confirmedReservations = new ArrayList<>();
    }

    public synchronized void addConfirmedReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public synchronized List<Reservation> getAllConfirmedReservations() {
        return Collections.unmodifiableList(new ArrayList<>(confirmedReservations));
    }

    public synchronized void displayHistory() {
        System.out.println("\n--- Confirmed Booking History (Chronological) ---");
        if (confirmedReservations.isEmpty()) {
            System.out.println("No confirmed reservations.");
            System.out.println("-----------------------------------------------");
            return;
        }

        int index = 1;
        for (Reservation reservation : confirmedReservations) {
            System.out.println(index + ". " + reservation);
            index++;
        }
        System.out.println("-----------------------------------------------");
    }
}
