import java.time.LocalDateTime;

public class Order {
    private final String orderId;
    private final LocalDateTime orderTime;
    private final ShoppingCart cart;
    private final String customerId;

    public Order(String orderId, ShoppingCart cart, String customerId) {
        this.orderId = orderId;
        this.orderTime = LocalDateTime.now();
        this.cart = cart;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public String getCustomerId() {
        return customerId;
    }
}