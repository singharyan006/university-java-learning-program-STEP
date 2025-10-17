import java.util.*;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void showDetails() {
        System.out.println("Product: " + name + ", Price: ₹" + price);
    }
}

class Order {
    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void showOrderDetails() {
        System.out.println("Order contains:");
        for (Product product : products) {
            product.showDetails();
        }
    }
}

class Customer {
    private String name;
    private String email;
    private List<Order> orders;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public void showCustomerDetails() {
        System.out.println("Customer: " + name + ", Email: " + email);
        for (Order order : orders) {
            order.showOrderDetails();
        }
    }
}

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        // Create Products
        Product product1 = new Product("Laptop", 75000.0);
        Product product2 = new Product("Mouse", 1500.0);
        Product product3 = new Product("Keyboard", 2500.0);

        // Create Orders and add Products
        Order order1 = new Order();
        order1.addProduct(product1);
        order1.addProduct(product2);

        Order order2 = new Order();
        order2.addProduct(product3);

        // Create Customer and place Orders
        Customer customer1 = new Customer("Amit", "amit@example.com");
        customer1.placeOrder(order1);
        customer1.placeOrder(order2);

        // Display full details
        customer1.showCustomerDetails();
    }
}
