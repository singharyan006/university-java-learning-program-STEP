import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("   Hotel Booking System v11.0");
        System.out.println("======================================");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        AtomicInteger reservationCounter = new AtomicInteger(1000);

        System.out.println("\n[System] Initializing shared inventory...");
        inventory.addRoomType("Suite Room", 2);
        inventory.addRoomType("Single Room", 2);
        inventory.addRoomType("Double Room", 2);
        inventory.displayInventory();

        System.out.println("\n[System] Starting concurrent booking simulation...");

        List<Reservation> batch1 = new ArrayList<>();
        batch1.add(new Reservation("Alice", "Suite Room"));
        batch1.add(new Reservation("Bob", "Single Room"));
        batch1.add(new Reservation("Charlie", "Double Room"));

        List<Reservation> batch2 = new ArrayList<>();
        batch2.add(new Reservation("Diana", "Suite Room"));
        batch2.add(new Reservation("Eve", "Single Room"));
        batch2.add(new Reservation("Frank", "Double Room"));

        List<Reservation> batch3 = new ArrayList<>();
        batch3.add(new Reservation("Grace", "Suite Room"));
        batch3.add(new Reservation("Henry", "Single Room"));
        batch3.add(new Reservation("Ivy", "Double Room"));

        CountDownLatch startSignal = new CountDownLatch(1);

        Thread guestThread1 = new Thread(() -> submitBatch(queue, batch1, startSignal), "Guest-Submitter-1");
        Thread guestThread2 = new Thread(() -> submitBatch(queue, batch2, startSignal), "Guest-Submitter-2");
        Thread guestThread3 = new Thread(() -> submitBatch(queue, batch3, startSignal), "Guest-Submitter-3");

        Thread processor1 = new Thread(new ConcurrentBookingProcessor(queue, inventory, history, reservationCounter), "Processor-1");
        Thread processor2 = new Thread(new ConcurrentBookingProcessor(queue, inventory, history, reservationCounter), "Processor-2");
        Thread processor3 = new Thread(new ConcurrentBookingProcessor(queue, inventory, history, reservationCounter), "Processor-3");

        guestThread1.start();
        guestThread2.start();
        guestThread3.start();

        processor1.start();
        processor2.start();
        processor3.start();

        startSignal.countDown();

        guestThread1.join();
        guestThread2.join();
        guestThread3.join();

        queue.closeIntake();

        processor1.join();
        processor2.join();
        processor3.join();

        System.out.println("\n[System] Concurrent processing completed.");
        System.out.println("Pending queue count: " + queue.pendingCount());

        history.displayHistory();
        inventory.displayInventory();
        inventory.displayAllocations();

        verifyNoDoubleAllocation(history.getAllConfirmedReservations());

        System.out.println("\n[Result] UC11 completed: Thread-safe concurrent booking prevented allocation conflicts.");
    }

    private static void submitBatch(BookingRequestQueue queue, List<Reservation> reservations, CountDownLatch startSignal) {
        try {
            startSignal.await();
            for (Reservation reservation : reservations) {
                queue.addRequest(reservation);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private static void verifyNoDoubleAllocation(List<Reservation> confirmedReservations) {
        Set<String> allocatedIds = new HashSet<>();
        boolean duplicateFound = false;

        for (Reservation reservation : confirmedReservations) {
            String roomId = reservation.getRoomId();
            if (!allocatedIds.add(roomId)) {
                duplicateFound = true;
                System.out.println("[Validation Error] Duplicate room allocation detected: " + roomId);
            }
        }

        if (!duplicateFound) {
            System.out.println("\n[Validation] No duplicate room allocation detected under concurrent execution.");
        }
    }
}
