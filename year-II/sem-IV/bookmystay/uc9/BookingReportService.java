import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingReportService {
    private BookingHistory bookingHistory;

    public BookingReportService(BookingHistory bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    public void generateSummaryReport() {
        List<Reservation> reservations = bookingHistory.getAllConfirmedReservations();

        System.out.println("\n=== Booking Summary Report ===");
        System.out.println("Total Confirmed Bookings: " + reservations.size());

        Map<String, Integer> roomTypeCounts = new HashMap<>();
        for (Reservation reservation : reservations) {
            String roomType = reservation.getRoomType();
            roomTypeCounts.put(roomType, roomTypeCounts.getOrDefault(roomType, 0) + 1);
        }

        System.out.println("Bookings by Room Type:");
        if (roomTypeCounts.isEmpty()) {
            System.out.println("No bookings available for reporting.");
        } else {
            for (Map.Entry<String, Integer> entry : roomTypeCounts.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }

        System.out.println("==============================");
    }
}
