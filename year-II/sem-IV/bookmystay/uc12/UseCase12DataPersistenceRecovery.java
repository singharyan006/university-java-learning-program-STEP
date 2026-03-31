public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("   Hotel Booking System v12.0");
        System.out.println("======================================");

        String stateFile = "system-state.ser";
        PersistenceService persistenceService = new PersistenceService(stateFile);

        System.out.println("\n[Startup] Attempting recovery from persisted data...");
        SystemState recoveredState = persistenceService.loadOrDefault();

        RoomInventory inventory = recoveredState.getInventory();
        BookingHistory history = recoveredState.getBookingHistory();

        if (inventory.getSnapshot().isEmpty()) {
            System.out.println("[Bootstrap] No prior inventory snapshot found. Initializing baseline inventory...");
            inventory.addRoomType("Suite Room", 2);
            inventory.addRoomType("Single Room", 1);
            inventory.addRoomType("Double Room", 1);
        }

        if (history.getReservations().isEmpty()) {
            System.out.println("[Bootstrap] No prior booking history found. Creating initial confirmed bookings...");
            Reservation r1 = new Reservation("RES-1201", "Alice", "Suite Room", "SUITE_ROOM-001", "CONFIRMED");
            Reservation r2 = new Reservation("RES-1202", "Bob", "Single Room", "SINGLE_ROOM-001", "CONFIRMED");
            history.addReservation(r1);
            history.addReservation(r2);

            inventory.updateCount("Suite Room", 1);
            inventory.updateCount("Single Room", 0);
            inventory.updateCount("Double Room", 1);
        }

        System.out.println("\n[System] Current in-memory state before shutdown save:");
        inventory.displayInventory();
        history.displayHistory();

        System.out.println("\n[Shutdown] Persisting current state...");
        persistenceService.save(new SystemState(inventory, history));

        System.out.println("\n[Restart] Simulating application restart and recovery...");
        SystemState restartedState = persistenceService.loadOrDefault();

        System.out.println("\n[Recovered] Restored inventory and booking history:");
        restartedState.getInventory().displayInventory();
        restartedState.getBookingHistory().displayHistory();

        System.out.println("\n[Result] UC12 completed: Persistence and recovery are active with safe fallback handling.");
    }
}
