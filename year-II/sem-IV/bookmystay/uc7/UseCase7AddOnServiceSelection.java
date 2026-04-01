import java.util.ArrayList;
import java.util.List;

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v7.0");
        System.out.println("======================================");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();

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

        queue.displayQueueStatus();

        System.out.println("\n[System] Confirming reservations and allocating rooms...");
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

            System.out.println("[Confirmed] " + request);
        }

        System.out.println("\n[System] Inventory after core booking confirmations:");
        inventory.displayInventory();
        inventory.displayRoomAllocations();

        System.out.println("\n[System] Guest add-on service selection started...");
        AddOnServiceManager addOnManager = new AddOnServiceManager();

        AddOnService breakfast = new AddOnService("Breakfast", 500.0);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 1200.0);
        AddOnService spaAccess = new AddOnService("Spa Access", 1800.0);
        AddOnService lateCheckout = new AddOnService("Late Checkout", 700.0);

        if (confirmedReservations.size() > 0) {
            addOnManager.addServiceToReservation(confirmedReservations.get(0).getReservationId(), breakfast);
            addOnManager.addServiceToReservation(confirmedReservations.get(0).getReservationId(), airportPickup);
        }

        if (confirmedReservations.size() > 1) {
            addOnManager.addServiceToReservation(confirmedReservations.get(1).getReservationId(), spaAccess);
        }

        if (confirmedReservations.size() > 2) {
            addOnManager.addServiceToReservation(confirmedReservations.get(2).getReservationId(), lateCheckout);
            addOnManager.addServiceToReservation(confirmedReservations.get(2).getReservationId(), breakfast);
        }

        for (Reservation reservation : confirmedReservations) {
            String reservationId = reservation.getReservationId();
            System.out.println("\nReservation: " + reservationId + " | Guest: " + reservation.getGuestName());
            addOnManager.displayServicesForReservation(reservationId);
        }

        addOnManager.displayAllServiceMappings();

        System.out.println("\n[Verification] Re-checking inventory to prove add-on flow did not alter core state:");
        inventory.displayInventory();

        System.out.println("\n[Result] UC7 completed: Add-on service mapping and cost aggregation are active without changing booking/inventory logic.");
    }
}
