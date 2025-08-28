//  Hotel Reservation System 
// Topic: Multiple Classes with Complex Interactions 
// Problem Statement: Build a hotel reservation management system handling rooms, guests, 
// and bookings. 
// Requirements: 
// ● Create a Room class with attributes: roomNumber (String), roomType (String), 
// pricePerNight (double), isAvailable (boolean), maxOccupancy (int) 
// ● Create a Guest class with attributes: guestId (String), guestName (String), 
// phoneNumber (String), email (String), bookingHistory (String array) 
// ● Create a Booking class with attributes: bookingId (String), guest (Guest object), 
// room (Room object), checkInDate (String), checkOutDate (String), totalAmount 
// (double) 
// ● Include static variables: totalBookings (int), hotelRevenue (double), hotelName 
// (String) 
// ● Implement reservation management methods: makeReservation(), 
// cancelReservation(), checkAvailability(), calculateBill() 
// ● Create static methods for reporting: getOccupancyRate(), getTotalRevenue(), 
// getMostPopularRoomType() 
// ● Implement a complete booking workflow from room search to checkout 
// Deliverables: Full hotel management system with multiple room types, guest management, and 
// comprehensive booking operations. 


import java.util.*;

public class HotelReservationSystem {

    static class Room {
        private String roomNumber;
        private String roomType;
        private double pricePerNight;
        private boolean isAvailable;
        private int maxOccupancy;

        public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.pricePerNight = pricePerNight;
            this.maxOccupancy = maxOccupancy;
            this.isAvailable = true;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailability(boolean status) {
            isAvailable = status;
        }

        public String getRoomType() {
            return roomType;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }

        public void displayRoomInfo() {
            System.out.println("Room " + roomNumber + " | Type: " + roomType + " | ₹" + pricePerNight + " | Available: " + isAvailable);
        }
    }

    static class Guest {
        private String guestId;
        private String guestName;
        private String phoneNumber;
        private String email;
        private String[] bookingHistory;
        private int bookingCount;

        public Guest(String guestName, String phoneNumber, String email) {
            this.guestName = guestName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.guestId = generateGuestId();
            this.bookingHistory = new String[10];
            this.bookingCount = 0;
        }

        public String getGuestId() {
            return guestId;
        }

        public String getGuestName() {
            return guestName;
        }

        public void addBooking(String bookingId) {
            if (bookingCount < bookingHistory.length) {
                bookingHistory[bookingCount++] = bookingId;
            }
        }

        public void displayGuestInfo() {
            System.out.println("Guest ID: " + guestId + " | Name: " + guestName + " | Phone: " + phoneNumber + " | Email: " + email);
        }

        private static int guestCounter = 0;

        public static String generateGuestId() {
            return String.format("G%03d", ++guestCounter);
        }
    }

    static class Booking {
        private String bookingId;
        private Guest guest;
        private Room room;
        private String checkInDate;
        private String checkOutDate;
        private double totalAmount;

        private static int totalBookings = 0;
        private static double hotelRevenue = 0.0;
        private static String hotelName = "Tranquil Stay";

        public Booking(Guest guest, Room room, String checkInDate, String checkOutDate, int nights) {
            this.bookingId = generateBookingId();
            this.guest = guest;
            this.room = room;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.totalAmount = calculateBill(nights);
            totalBookings++;
            hotelRevenue += totalAmount;
            room.setAvailability(false);
            guest.addBooking(bookingId);
        }

        public static String generateBookingId() {
            return String.format("B%03d", totalBookings + 1);
        }

        public double calculateBill(int nights) {
            return room.getPricePerNight() * nights;
        }

        public void cancelReservation() {
            room.setAvailability(true);
            hotelRevenue -= totalAmount;
            System.out.println("Booking " + bookingId + " cancelled.");
        }

        public void displayBookingInfo() {
            System.out.println("Booking ID: " + bookingId + " | Guest: " + guest.getGuestName() +
                    " | Room: " + room.getRoomNumber() + " | ₹" + totalAmount +
                    " | Check-in: " + checkInDate + " | Check-out: " + checkOutDate);
        }

        public static double getTotalRevenue() {
            return hotelRevenue;
        }

        public static double getOccupancyRate(Room[] rooms) {
            int occupied = 0;
            for (Room r : rooms) {
                if (!r.isAvailable()) occupied++;
            }
            return (double) occupied / rooms.length * 100;
        }

        public static String getMostPopularRoomType(Room[] rooms) {
            Map<String, Integer> typeCount = new HashMap<>();
            for (Room r : rooms) {
                if (!r.isAvailable()) {
                    typeCount.put(r.getRoomType(), typeCount.getOrDefault(r.getRoomType(), 0) + 1);
                }
            }
            return typeCount.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("N/A");
        }

        public static void setHotelName(String name) {
            hotelName = name;
        }

        public static String getHotelName() {
            return hotelName;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Room[] rooms = {
            new Room("101", "Deluxe", 2500, 2),
            new Room("102", "Standard", 1800, 2),
            new Room("103", "Suite", 4000, 4),
            new Room("104", "Standard", 1800, 2),
            new Room("105", "Deluxe", 2500, 2)
        };

        Guest guest1 = new Guest("Alice", "9876543210", "alice@example.com");
        Guest guest2 = new Guest("Bob", "9123456780", "bob@example.com");

        Booking.setHotelName("Ocean View Resort");

        // Make reservations
        Booking booking1 = new Booking(guest1, rooms[0], "2025-08-28", "2025-08-30", 2);
        Booking booking2 = new Booking(guest2, rooms[2], "2025-08-29", "2025-09-01", 3);

        // Display info
        System.out.println("=== Hotel: " + Booking.getHotelName() + " ===");
        booking1.displayBookingInfo();
        booking2.displayBookingInfo();

        System.out.println("\n--- Room Status ---");
        for (Room r : rooms) r.displayRoomInfo();

        System.out.println("\n--- Guest Info ---");
        guest1.displayGuestInfo();
        guest2.displayGuestInfo();

        System.out.println("\n--- Hotel Reports ---");
        System.out.println("Total Revenue: ₹" + Booking.getTotalRevenue());
        System.out.println("Occupancy Rate: " + Booking.getOccupancyRate(rooms) + "%");
        System.out.println("Most Popular Room Type: " + Booking.getMostPopularRoomType(rooms));
    }
}
