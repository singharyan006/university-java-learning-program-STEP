public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v6.0");
        System.out.println("======================================");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();

        System.out.println("\n[System] Initializing room inventory...");
        inventory.addRoomType("Suite Room", 2);
        inventory.addRoomType("Single Room", 1);
        inventory.addRoomType("Double Room", 1);
        inventory.displayInventory();

        System.out.println("\n[System] Receiving incoming booking requests during peak hours...");
        queue.addRequest(new Reservation("Alice", "Suite Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Double Room"));
        queue.addRequest(new Reservation("Diana", "Suite Room"));
        queue.addRequest(new Reservation("Eve", "Single Room"));

        queue.displayQueueStatus();

        System.out.println("\n[System] Processing queue in FIFO order and allocating rooms...");
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

            request.assignRoomId(allocatedRoomId);
            System.out.println("[Confirmed] " + request.getGuestName() + " assigned " + allocatedRoomId + " (" + roomType + ")");
        }

        System.out.println("\n[System] Final synchronized state after confirmations:");
        inventory.displayInventory();
        inventory.displayRoomAllocations();

        System.out.println("\n[Result] UC6 completed: Reservation confirmation and safe room allocation are active.");
    }
}
