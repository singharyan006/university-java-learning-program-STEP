public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v9.0");
        System.out.println("======================================");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService(bookingHistory);
        InvalidBookingValidator validator = new InvalidBookingValidator();

        try {
            System.out.println("\n[System] Initializing room inventory...");
            inventory.addRoomType("Suite Room", 2);
            inventory.addRoomType("Single Room", 1);
            inventory.addRoomType("Double Room", 1);
            inventory.displayInventory();
        } catch (InventoryStateException ex) {
            System.out.println("[Fatal Inventory Error] " + ex.getMessage());
            return;
        }

        System.out.println("\n[System] Receiving booking requests including invalid scenarios...");
        queue.addRequest(new Reservation("Alice", "Suite Room"));
        queue.addRequest(new Reservation("", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Presidential Room"));
        queue.addRequest(new Reservation("Diana", "Suite Room"));
        queue.addRequest(new Reservation("Eve", "Single Room"));
        queue.addRequest(new Reservation("Frank", "Single Room"));

        queue.displayQueueStatus();

        System.out.println("\n[System] Processing queue with fail-fast validation and safe error handling...");
        int reservationCounter = 1000;

        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();

            try {
                validator.validateReservationInput(request, inventory);
                validator.validateBeforeAllocation(request.getRoomType(), inventory);

                String roomId = inventory.allocateRoom(request.getRoomType());
                String reservationId = "RES-" + (++reservationCounter);

                request.setReservationId(reservationId);
                request.assignRoomId(roomId);
                bookingHistory.addConfirmedReservation(request);

                System.out.println("[Confirmed] " + request + " -> Added to booking history");
            } catch (InvalidBookingException ex) {
                System.out.println("[Validation Failed] Guest='" + request.getGuestName() + "' RoomType='" + request.getRoomType() + "' | Reason: " + ex.getMessage());
            } catch (InventoryStateException ex) {
                System.out.println("[Inventory Error] " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("[Unexpected Error] " + ex.getMessage());
            }
        }

        System.out.println("\n[Admin] Viewing safe booking history after error-prone inputs...");
        bookingHistory.displayBookingHistory();

        System.out.println("\n[Admin] Generating summary report from validated records only...");
        reportService.generateSummaryReport();

        System.out.println("\n[Verification] Final inventory remains valid and non-negative:");
        inventory.displayInventory();
        inventory.displayRoomAllocations();

        System.out.println("\n[Result] UC9 completed: Validation and custom exception handling prevent invalid state changes while keeping the system stable.");
    }
}
