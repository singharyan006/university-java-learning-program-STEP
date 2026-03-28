import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingHistory {
    private List<Reservation> chronologicalReservations;
    private Map<String, Reservation> reservationById;

    public BookingHistory() {
        chronologicalReservations = new ArrayList<>();
        reservationById = new HashMap<>();
    }

    public void addConfirmedReservation(Reservation reservation) {
        chronologicalReservations.add(reservation);
        reservationById.put(reservation.getReservationId(), reservation);
    }

    public Reservation getReservationById(String reservationId) {
        return reservationById.get(reservationId);
    }

    public void markReservationCancelled(String reservationId) throws InvalidCancellationException {
        Reservation reservation = reservationById.get(reservationId);
        if (reservation == null) {
            throw new InvalidCancellationException("Reservation not found: " + reservationId);
        }
        if (reservation.isCancelled()) {
            throw new InvalidCancellationException("Reservation already cancelled: " + reservationId);
        }
        reservation.markCancelled();
    }

    public List<Reservation> getAllReservations() {
        return Collections.unmodifiableList(chronologicalReservations);
    }

    public void displayBookingHistory() {
        System.out.println("\n--- Booking History (Chronological) ---");
        if (chronologicalReservations.isEmpty()) {
            System.out.println("No reservations recorded.");
            System.out.println("---------------------------------------");
            return;
        }

        int index = 1;
        for (Reservation reservation : chronologicalReservations) {
            System.out.println(index + ". " + reservation);
            index++;
        }
        System.out.println("---------------------------------------");
    }
}
