// PROBLEM 1: Food Delivery App
// Concept: Method Overloading
// You're creating a food ordering system. Design a class that can calculate delivery
// charges in different ways:
// ● Basic delivery (just distance)
// ● Premium delivery (distance + priority fee)
// ● Group delivery (distance + number of orders discount)
// ● Festival special (distance + discount percentage + free delivery over certain
// amount)
// Each calculation should show a different message about the delivery cost breakdown.
// Hint: Same method name, different parameters - let Java pick the right one!



public class FoodDeliveryCharges {
    public static void main(String[] args) {
        FoodDeliveryCharges calc = new FoodDeliveryCharges();

        System.out.println("Basic Delivery:");
        calc.calculateCharge(10.0); // distance in km
        System.out.println();

        System.out.println("Premium Delivery:");
        calc.calculateCharge(10.0, 5.0); // distance + priority fee
        System.out.println();

        System.out.println("Group Delivery:");
        calc.calculateCharge(10.0, 4); // distance + number of orders discount
        System.out.println();

        System.out.println("Festival Special:");
        calc.calculateCharge(150.0, 10.0, 1000.0); // distance, discountPercent, freeOverAmount
        System.out.println();
    }

    // Basic delivery: base rate per km
    public void calculateCharge(double distanceKm) {
        double ratePerKm = 5.0; // currency units per km
        double charge = distanceKm * ratePerKm;
        System.out.println(String.format("Distance: %.1f km | Rate: %.2f per km | Total: %.2f", distanceKm, ratePerKm, charge));
    }

    // Premium delivery: distance + priority fee
    public void calculateCharge(double distanceKm, double priorityFee) {
        double ratePerKm = 5.0;
        double base = distanceKm * ratePerKm;
        double total = base + priorityFee;
        System.out.println(String.format("Distance: %.1f km | Base: %.2f | Priority fee: %.2f | Total: %.2f", distanceKm, base, priorityFee, total));
    }

    // Group delivery: distance + number of orders (discount per extra order)
    public void calculateCharge(double distanceKm, int numberOfOrders) {
        double ratePerKm = 5.0;
        double base = distanceKm * ratePerKm;
        double discountPerOrder = 1.0; // discount per extra order
        double discount = Math.max(0, (numberOfOrders - 1) * discountPerOrder);
        double total = Math.max(0, base - discount);
        System.out.println(String.format("Distance: %.1f km | Base: %.2f | Orders: %d | Discount: %.2f | Total: %.2f", distanceKm, base, numberOfOrders, discount, total));
    }

    // Festival special: distance + percentage discount, free over certain amount
    public void calculateCharge(double distanceKm, double discountPercent, double freeOverAmount) {
        double ratePerKm = 5.0;
        double base = distanceKm * ratePerKm;
        double discounted = base * (1 - discountPercent / 100.0);
        double total = discounted;
        boolean free = discounted >= freeOverAmount;
        if (free) {
            total = 0.0;
        }
        System.out.println(String.format("Distance: %.1f km | Base: %.2f | Discount: %.1f%% | After discount: %.2f | Free over: %.2f | Final: %.2f", distanceKm, base, discountPercent, discounted, freeOverAmount, total));
    }
}
