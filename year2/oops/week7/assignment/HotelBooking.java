// PROBLEM 1: Hotel Booking System
// Concept: Method Overloading
// You're building a hotel reservation system that calculates room prices in various ways:
// ● Standard booking (just room type and nights)
// ● Seasonal booking (room type, nights + seasonal multiplier)
// ● Corporate booking (room type, nights + corporate discount + meal package)
// ● Wedding package (room type, nights + guest count + decoration fee + catering
// options)
// Each calculation should display a detailed breakdown of costs and savings applied.
// Hint: Multiple ways to book the same room - let method signatures handle the
// complexity!




public class HotelBooking {
    public static void main(String[] args) {
        HotelBooking hb = new HotelBooking();

        System.out.println("Standard Booking:");
        hb.calculatePrice("Deluxe", 3);
        System.out.println();

        System.out.println("Seasonal Booking:");
        hb.calculatePrice("Deluxe", 3, 1.25); // seasonal multiplier
        System.out.println();

        System.out.println("Corporate Booking:");
        hb.calculatePrice("Deluxe", 5, 0.10, true); // discount 10%, meal package true
        System.out.println();

        System.out.println("Wedding Package:");
        hb.calculatePrice("Suite", 2, 50, 500.0, true); // guests, decoration, catering
        System.out.println();
    }

    // Standard booking: room type and nights
    public void calculatePrice(String roomType, int nights) {
        double rate = baseRate(roomType);
        double total = rate * nights;
        System.out.println(String.format("Room: %s | Nights: %d | Rate: %.2f | Total: %.2f", roomType, nights, rate, total));
    }

    // Seasonal booking: multiplier applies
    public void calculatePrice(String roomType, int nights, double seasonalMultiplier) {
        double rate = baseRate(roomType);
        double subtotal = rate * nights;
        double total = subtotal * seasonalMultiplier;
        System.out.println(String.format("Room: %s | Nights: %d | Base: %.2f | Multiplier: %.2f | Total: %.2f", roomType, nights, subtotal, seasonalMultiplier, total));
    }

    // Corporate booking: discount and optional meal package
    public void calculatePrice(String roomType, int nights, double corporateDiscount, boolean mealPackage) {
        double rate = baseRate(roomType);
        double base = rate * nights;
        double discount = base * corporateDiscount;
        double meal = mealPackage ? 20.0 * nights : 0.0; // per-night meal cost
        double total = base - discount + meal;
        System.out.println(String.format("Room: %s | Nights: %d | Base: %.2f | Discount: %.2f | Meal: %.2f | Total: %.2f", roomType, nights, base, discount, meal, total));
    }

    // Wedding package: guests, decoration fee, catering per guest
    public void calculatePrice(String roomType, int nights, int guestCount, double decorationFee, boolean catering) {
        double rate = baseRate(roomType);
        double base = rate * nights;
        double cateringCost = catering ? guestCount * 15.0 : 0.0;
        double total = base + decorationFee + cateringCost;
        System.out.println(String.format("Room: %s | Nights: %d | Base: %.2f | Guests: %d | Decoration: %.2f | Catering: %.2f | Total: %.2f", roomType, nights, base, guestCount, decorationFee, cateringCost, total));
    }

    private double baseRate(String roomType) {
        switch (roomType.toLowerCase()) {
            case "suite": return 500.0;
            case "deluxe": return 200.0;
            default: return 100.0;
        }
    }
}
