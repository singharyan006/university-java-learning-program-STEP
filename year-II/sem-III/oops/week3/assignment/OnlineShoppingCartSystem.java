//  Online Shopping Cart System 
// Topic: Object Relationships and Method Interaction 
// Problem Statement: Develop an online shopping cart system that manages products and 
// customer purchases. 
// Requirements: 
// ● Create a Product class with attributes: productId (String), productName (String), 
// price (double), category (String), stockQuantity (int) 
// ● Create a ShoppingCart class with attributes: cartId (String), customerName 
// (String), products (Product array), quantities (int array), cartTotal (double) 
// ● Include static variables in Product class: totalProducts (int), categories (String 
// array) 
// ● Implement methods in ShoppingCart: addProduct(Product product, int 
// quantity), removeProduct(String productId), calculateTotal(), 
// displayCart(), checkout() 
// ● Create static methods in Product class: findProductById(Product[] products, 
// String productId), getProductsByCategory(Product[] products, 
// String category) 
// ● Create a menu-driven system allowing users to browse products, add/remove items from 
// cart, and checkout 
// ● Demonstrate object interaction where ShoppingCart objects contain and manipulate 
// Product objects 
// Deliverables: Complete shopping cart system with at least 10 different products and 
// comprehensive testing of all functionalities. 


import java.util.Scanner;

public class OnlineShoppingCartSystem {

    static class Product {
        private String productId;
        private String productName;
        private double price;
        private String category;
        private int stockQuantity;

        private static int totalProducts = 0;
        private static String[] categories = {"Electronics", "Clothing", "Books", "Home", "Toys"};

        // Constructor
        public Product(String productName, double price, String category, int stockQuantity) {
            this.productName = productName;
            this.price = price;
            this.category = category;
            this.stockQuantity = stockQuantity;
            this.productId = generateProductId();
            totalProducts++;
        }

        // Static method to generate product ID
        public static String generateProductId() {
            return String.format("P%03d", totalProducts + 1);
        }

        // Static method to find product by ID
        public static Product findProductById(Product[] products, String productId) {
            for (Product p : products) {
                if (p != null && p.productId.equals(productId)) {
                    return p;
                }
            }
            return null;
        }

        // Static method to get products by category
        public static void getProductsByCategory(Product[] products, String category) {
            System.out.println("Products in category: " + category);
            for (Product p : products) {
                if (p != null && p.category.equalsIgnoreCase(category)) {
                    System.out.println(p.productId + " - " + p.productName + " - ₹" + p.price);
                }
            }
        }

        public void reduceStock(int quantity) {
            stockQuantity -= quantity;
        }

        public void increaseStock(int quantity) {
            stockQuantity += quantity;
        }

        public int getStockQuantity() {
            return stockQuantity;
        }

        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }

        public void displayProduct() {
            System.out.println(productId + " | " + productName + " | ₹" + price + " | " + category + " | Stock: " + stockQuantity);
        }
    }

    static class ShoppingCart {
        private String cartId;
        private String customerName;
        private Product[] products;
        private int[] quantities;
        private double cartTotal;
        private int itemCount;

        // Constructor
        public ShoppingCart(String customerName) {
            this.customerName = customerName;
            this.cartId = generateCartId();
            this.products = new Product[20];
            this.quantities = new int[20];
            this.cartTotal = 0.0;
            this.itemCount = 0;
        }

        public static String generateCartId() {
            return "CART" + System.currentTimeMillis();
        }

        public void addProduct(Product product, int quantity) {
            if (product.getStockQuantity() < quantity) {
                System.out.println("Not enough stock for " + product.getProductName());
                return;
            }
            products[itemCount] = product;
            quantities[itemCount] = quantity;
            product.reduceStock(quantity);
            itemCount++;
            calculateTotal();
            System.out.println("Added " + quantity + " x " + product.getProductName() + " to cart.");
        }

        public void removeProduct(String productId) {
            for (int i = 0; i < itemCount; i++) {
                if (products[i].getProductId().equals(productId)) {
                    products[i].increaseStock(quantities[i]);
                    System.out.println("Removed " + products[i].getProductName() + " from cart.");
                    products[i] = null;
                    quantities[i] = 0;
                    // Shift remaining items
                    for (int j = i; j < itemCount - 1; j++) {
                        products[j] = products[j + 1];
                        quantities[j] = quantities[j + 1];
                    }
                    products[itemCount - 1] = null;
                    quantities[itemCount - 1] = 0;
                    itemCount--;
                    calculateTotal();
                    return;
                }
            }
            System.out.println("Product not found in cart.");
        }

        public void calculateTotal() {
            cartTotal = 0.0;
            for (int i = 0; i < itemCount; i++) {
                cartTotal += products[i].getPrice() * quantities[i];
            }
        }

        public void displayCart() {
            System.out.println("=== Cart Summary for " + customerName + " ===");
            for (int i = 0; i < itemCount; i++) {
                System.out.println(products[i].getProductName() + " x " + quantities[i] + " = ₹" + (products[i].getPrice() * quantities[i]));
            }
            System.out.println("Total: ₹" + cartTotal);
            System.out.println("===============================");
        }

        public void checkout() {
            displayCart();
            System.out.println("Checkout complete. Thank you for shopping!");
            itemCount = 0;
            cartTotal = 0.0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create products
        Product[] products = new Product[10];
        products[0] = new Product("Laptop", 55000, "Electronics", 5);
        products[1] = new Product("T-Shirt", 499, "Clothing", 20);
        products[2] = new Product("Book - Java", 799, "Books", 15);
        products[3] = new Product("Mixer Grinder", 2999, "Home", 10);
        products[4] = new Product("Toy Car", 399, "Toys", 25);
        products[5] = new Product("Smartphone", 15000, "Electronics", 8);
        products[6] = new Product("Jeans", 999, "Clothing", 12);
        products[7] = new Product("Cookbook", 599, "Books", 10);
        products[8] = new Product("Wall Clock", 799, "Home", 7);
        products[9] = new Product("Puzzle Game", 299, "Toys", 30);

        ShoppingCart cart = new ShoppingCart("Alice");

        while (true) {
            System.out.println("\n=== Online Shopping Menu ===");
            System.out.println("1. View All Products");
            System.out.println("2. View Products by Category");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    for (Product p : products) {
                        p.displayProduct();
                    }
                    break;
                case 2:
                    System.out.print("Enter category: ");
                    String cat = sc.nextLine();
                    Product.getProductsByCategory(products, cat);
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    String pid = sc.nextLine();
                    Product prod = Product.findProductById(products, pid);
                    if (prod != null) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        cart.addProduct(prod, qty);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Product ID to remove: ");
                    String removeId = sc.nextLine();
                    cart.removeProduct(removeId);
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    cart.checkout();
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
