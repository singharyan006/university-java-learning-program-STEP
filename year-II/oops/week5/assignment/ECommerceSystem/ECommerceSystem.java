import java.util.HashMap;
import java.util.Map;

public final class ECommerceSystem {
    private static final Map<String, Product> productCatalog = new HashMap<>();

    static {
        // Populate the product catalog
        productCatalog.put("101", Product.createElectronics("101", "Laptop", "TechCorp", 1200.0, 3.5, new String[]{"16GB RAM"}, Map.of("CPU", "Intel i7")));
        productCatalog.put("102", Product.createClothing("102", "T-Shirt", "FashionWear", 25.0, 0.2, new String[]{"Cotton"}, Map.of()));
        productCatalog.put("103", Product.createBooks("103", "Java Guide", "CodePub", 45.0, 1.0, new String[]{"Hardcover"}, Map.of("Author", "J. Doe")));
    }

    public static boolean processOrder(Order order, Customer customer) {
        System.out.println("Processing order " + order.getOrderId() + " for customer " + customer.getName());

        // Validate order and customer
        if (order.getCart().getTotalAmount() <= 0) {
            System.out.println("Order failed: Cart is empty or invalid.");
            return false;
        }

        // Get total amount and process payment
        PaymentProcessor paymentProcessor = new PaymentProcessor("P-1", "SEC-KEY-123");
        boolean paymentSuccess = paymentProcessor.processPayment(order.getCart().getTotalAmount());

        if (paymentSuccess) {
            System.out.println("Payment successful. Fulfilling order.");
            return fulfillOrder(order);
        } else {
            System.out.println("Order failed: Payment could not be processed.");
            return false;
        }
    }

    // Static method for inventory management
    public static boolean checkInventory(String productId, int quantity) {
        Product product = productCatalog.get(productId);
        if (product != null) {
            System.out.println("Inventory check for " + product.getName() + ": available.");
            return true;
        }
        System.out.println("Inventory check failed: Product " + productId + " not found.");
        return false;
    }

    private static boolean fulfillOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + " fulfilled. Shipping items...");
        // Dummy shipping logic
        return true;
    }

    public static void main(String[] args) {
        // Scenario 1: Registered customer order
        Customer customer = new Customer("C-123", "jane.doe@example.com", "Jane Doe", "555-1111", "en");
        ShoppingCart cart = new ShoppingCart("CART-01", customer.getCustomerId());
        
        Product laptop = productCatalog.get("101");
        cart.addItem(laptop, 1);
        
        Order order = new Order("ORD-001", cart, customer.getCustomerId());
        ECommerceSystem.processOrder(order, customer);

        System.out.println("\n----------------------------------\n");

        // Scenario 2: Guest checkout
        Customer guest = new Customer("guest@example.com", "555-2222");
        ShoppingCart guestCart = new ShoppingCart("G-CART-02", guest.getCustomerId());
        Product tShirt = productCatalog.get("102");
        
        guestCart.addItem(tShirt, 3);
        
        Order guestOrder = new Order("ORD-002", guestCart, guest.getCustomerId());
        ECommerceSystem.processOrder(guestOrder, guest);
    }
}