//  Food Delivery Order 
// �
// �
 
// Create a program to simulate a Food Delivery System. 
// 2 
// ● Class FoodOrder with fields: String customerName, String foodItem, int 
// quantity, double price. 
// ● Constructor overloading: 
// 1. Default constructor → assigns "Unknown" order. 
// 2. Constructor with food item → sets quantity = 1, price = default. 
// 3. Constructor with food item and quantity → calculates price = quantity × 
// fixedRate. 
// ● Method: printBill() → displays order details and total price. 
// ● In main(): Create multiple orders and print bills. 


class FoodOrder {
    private String customerName;
    private String foodItem;
    private int quantity;
    private double price;

    private static final double FIXED_RATE = 150.0;

    // Default constructor
    public FoodOrder() {
        this("Unknown", "Unknown", 0, 0.0);
    }

    // Constructor with food item only
    public FoodOrder(String foodItem) {
        this("Customer", foodItem, 1, FIXED_RATE);
    }

    // Constructor with food item and quantity
    public FoodOrder(String foodItem, int quantity) {
        this("Customer", foodItem, quantity, quantity * FIXED_RATE);
    }

    // Full constructor
    public FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    // Method to print bill
    public void printBill() {
        System.out.println("🧾 Food Order Bill");
        System.out.println("Customer: " + customerName);
        System.out.println("Food Item: " + foodItem);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: ₹" + price);
        System.out.println("---------------------------");
    }
}

public class FoodDeliverySystem {
    public static void main(String[] args) {
        FoodOrder order1 = new FoodOrder();
        FoodOrder order2 = new FoodOrder("Burger");
        FoodOrder order3 = new FoodOrder("Pizza", 3);
        FoodOrder order4 = new FoodOrder("Aryan", "Pasta", 2, 300.0);

        order1.printBill();
        order2.printBill();
        order3.printBill();
        order4.printBill();
    }
}
