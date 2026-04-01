import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(new ArrayList<>(reservations));
    }

    public void displayHistory() {
        System.out.println("\n--- Booking History ---");
        if (reservations.isEmpty()) {
            System.out.println("No reservations recorded.");
            System.out.println("-----------------------");
            return;
        }

        int index = 1;
        for (Reservation reservation : reservations) {
            System.out.println(index + ". " + reservation);
            index++;
        }
        System.out.println("-----------------------");
    }
}
