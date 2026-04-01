import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingReportService {
    private BookingHistory bookingHistory;

    public BookingReportService(BookingHistory bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    public void generateSummaryReport() {
        List<Reservation> reservations = bookingHistory.getAllReservations();

        int confirmedCount = 0;
        int cancelledCount = 0;
        Map<String, Integer> roomTypeCounts = new HashMap<>();

        for (Reservation reservation : reservations) {
            if (reservation.isCancelled()) {
                cancelledCount++;
            } else {
                confirmedCount++;
            }
            String roomType = reservation.getRoomType();
            roomTypeCounts.put(roomType, roomTypeCounts.getOrDefault(roomType, 0) + 1);
        }

        System.out.println("\n=== Booking Summary Report ===");
        System.out.println("Total Reservations Recorded: " + reservations.size());
        System.out.println("Active Confirmed Reservations: " + confirmedCount);
        System.out.println("Cancelled Reservations: " + cancelledCount);
        System.out.println("Reservations by Room Type:");
        for (Map.Entry<String, Integer> entry : roomTypeCounts.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("==============================");
    }
}
