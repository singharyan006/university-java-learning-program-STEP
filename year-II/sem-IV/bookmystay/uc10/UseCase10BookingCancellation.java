public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v10.0");
        System.out.println("======================================");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService(bookingHistory);
        CancellationService cancellationService = new CancellationService();

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

        System.out.println("\n[System] Confirming reservations and recording history...");
        int reservationCounter = 1000;

        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            String roomType = request.getRoomType();

            if (!inventory.hasRoomType(roomType)) {
                System.out.println("[Rejected] Invalid room type: " + roomType);
                continue;
            }

            String allocatedRoomId = inventory.allocateRoom(roomType);
            if (allocatedRoomId == null) {
                System.out.println("[Rejected] " + request.getGuestName() + " -> No " + roomType + " available");
                continue;
            }

            request.setReservationId("RES-" + (++reservationCounter));
            request.assignRoomId(allocatedRoomId);
            bookingHistory.addConfirmedReservation(request);

            System.out.println("[Confirmed] " + request);
        }

        System.out.println("\n[State] Before cancellation operations:");
        bookingHistory.displayBookingHistory();
        inventory.displayInventory();
        inventory.displayRoomAllocations();

        System.out.println("\n[System] Processing cancellation requests...");
        String validCancellationId = "RES-1002";
        String duplicateCancellationId = "RES-1002";
        String invalidCancellationId = "RES-9999";

        try {
            cancellationService.cancelReservation(validCancellationId, bookingHistory, inventory);
            System.out.println("[Cancelled] Reservation " + validCancellationId + " cancelled successfully.");
        } catch (InvalidCancellationException ex) {
            System.out.println("[Cancellation Failed] " + ex.getMessage());
        }

        try {
            cancellationService.cancelReservation(duplicateCancellationId, bookingHistory, inventory);
            System.out.println("[Cancelled] Reservation " + duplicateCancellationId + " cancelled successfully.");
        } catch (InvalidCancellationException ex) {
            System.out.println("[Cancellation Failed] " + ex.getMessage());
        }

        try {
            cancellationService.cancelReservation(invalidCancellationId, bookingHistory, inventory);
            System.out.println("[Cancelled] Reservation " + invalidCancellationId + " cancelled successfully.");
        } catch (InvalidCancellationException ex) {
            System.out.println("[Cancellation Failed] " + ex.getMessage());
        }

        System.out.println("\n[State] After cancellation rollback:");
        bookingHistory.displayBookingHistory();
        inventory.displayInventory();
        inventory.displayRoomAllocations();
        cancellationService.displayRollbackStack();

        System.out.println("\n[Admin] Generating report after cancellations...");
        reportService.generateSummaryReport();

        System.out.println("\n[Result] UC10 completed: Safe cancellation and inventory rollback are active.");
    }
}
