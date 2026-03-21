import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingHistory {
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    public void addConfirmedReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getAllConfirmedReservations() {
        return Collections.unmodifiableList(confirmedReservations);
    }

    public int getTotalConfirmedBookings() {
        return confirmedReservations.size();
    }

    public void displayBookingHistory() {
        System.out.println("\n--- Booking History (Chronological) ---");

        if (confirmedReservations.isEmpty()) {
            System.out.println("No confirmed reservations in history.");
            System.out.println("---------------------------------------");
            return;
        }

        int serial = 1;
        for (Reservation reservation : confirmedReservations) {
            System.out.println(serial + ". " + reservation);
            serial++;
        }
        System.out.println("---------------------------------------");
    }
}
