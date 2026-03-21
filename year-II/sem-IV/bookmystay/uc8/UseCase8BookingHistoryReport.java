import java.util.ArrayList;
import java.util.List;

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v8.0");
        System.out.println("======================================");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService(bookingHistory);
        AddOnServiceManager addOnManager = new AddOnServiceManager();

        System.out.println("\n[System] Initializing room inventory...");
        inventory.addRoomType("Suite Room", 2);
        inventory.addRoomType("Single Room", 1);
        inventory.addRoomType("Double Room", 1);
        inventory.displayInventory();

        System.out.println("\n[System] Receiving booking requests...");
        queue.addRequest(new Reservation("Alice", "Suite Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Double Room"));
        queue.addRequest(new Reservation("Diana", "Suite Room"));
        queue.addRequest(new Reservation("Eve", "Single Room"));

        queue.displayQueueStatus();

        System.out.println("\n[System] Confirming reservations and recording booking history...");
        List<Reservation> confirmedReservations = new ArrayList<>();
        int reservationCounter = 1000;

        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            String roomType = request.getRoomType();

            if (inventory.getAvailableRooms(roomType) <= 0) {
                System.out.println("[Rejected] " + request.getGuestName() + " -> No " + roomType + " available");
                continue;
            }

            String allocatedRoomId = inventory.allocateRoom(roomType);
            if (allocatedRoomId == null) {
                System.out.println("[Rejected] " + request.getGuestName() + " -> Allocation failed due to no availability");
                continue;
            }

            String reservationId = "RES-" + (++reservationCounter);
            request.setReservationId(reservationId);
            request.assignRoomId(allocatedRoomId);

            confirmedReservations.add(request);
            bookingHistory.addConfirmedReservation(request);
            System.out.println("[Confirmed] " + request + " -> Added to booking history");
        }

        System.out.println("\n[System] Applying add-on services (core booking state remains unchanged)...");
        AddOnService breakfast = new AddOnService("Breakfast", 500.0);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1200.0);
        AddOnService spaAccess = new AddOnService("Spa Access", 1800.0);

        if (confirmedReservations.size() > 0) {
            addOnManager.addServiceToReservation(confirmedReservations.get(0).getReservationId(), breakfast);
            addOnManager.addServiceToReservation(confirmedReservations.get(0).getReservationId(), airportPickup);
        }
        if (confirmedReservations.size() > 1) {
            addOnManager.addServiceToReservation(confirmedReservations.get(1).getReservationId(), spaAccess);
        }

        System.out.println("\n[Admin] Viewing booking history...");
        bookingHistory.displayBookingHistory();

        System.out.println("\n[Admin] Generating summary and detailed reports...");
        reportService.generateSummaryReport();
        reportService.generateDetailedReport();

        System.out.println("\n[Admin] Viewing add-on details for each reservation...");
        for (Reservation reservation : confirmedReservations) {
            addOnManager.displayServicesForReservation(reservation.getReservationId());
        }

        System.out.println("\n[Verification] Final inventory and room allocations:");
        inventory.displayInventory();
        inventory.displayRoomAllocations();

        System.out.println("\n[Result] UC8 completed: Booking history and reporting are active with ordered, read-only historical tracking.");
    }
}
