import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<CartItem> items;
    private double totalAmount;
    private int itemCount;

    private static class CartItem {
        private final Product product;
        private final int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    public ShoppingCart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        this.itemCount = 0;
    }

    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product)) {
            System.out.println("Invalid item. Only products can be added to the cart.");
            return false;
        }

        Product p = (Product) product;
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return false;
        }

        items.add(new CartItem(p, quantity));
        updateCartTotals();
        System.out.println(quantity + " of " + p.getName() + " added to cart.");
        return true;
    }

    private void updateCartTotals() {
        this.totalAmount = 0.0;
        this.itemCount = 0;
        for (CartItem item : items) {
            this.totalAmount += item.getProduct().getBasePrice() * item.getQuantity();
            this.itemCount += item.getQuantity();
        }
        this.totalAmount -= calculateDiscount();
    }

    private double calculateDiscount() { // Internal pricing logic
        // Example: 5% discount on orders over $500
        if (totalAmount > 500) {
            return totalAmount * 0.05;
        }
        return 0;
    }

    // Package-private for checkout process
    String getCartSummary() {
        return "Cart " + cartId + ": " + itemCount + " items, Total: $" + String.format("%.2f", totalAmount);
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}