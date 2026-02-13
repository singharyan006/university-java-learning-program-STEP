import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId, String name, String category, String manufacturer,
                    double basePrice, double weight, String[] features, Map<String, String> specifications) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = features != null ? Arrays.copyOf(features, features.length) : new String[0];
        this.specifications = specifications != null ? new HashMap<>(specifications) : Collections.emptyMap();
    }

    // Factory methods
    public static Product createElectronics(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Electronics", manufacturer, price, weight, features, specs);
    }

    public static Product createClothing(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Clothing", manufacturer, price, weight, features, specs);
    }

    public static Product createBooks(String id, String name, String manufacturer, double price, double weight, String[] features, Map<String, String> specs) {
        return new Product(id, name, "Books", manufacturer, price, weight, features, specs);
    }

    // Getters with defensive copying
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getWeight() {
        return weight;
    }

    public String[] getFeatures() {
        return Arrays.copyOf(features, features.length);
    }

    public Map<String, String> getSpecifications() {
        return Collections.unmodifiableMap(new HashMap<>(specifications));
    }

    // Business rule consistency method
    public final double calculateTax(String region) {
        if ("US".equalsIgnoreCase(region)) {
            return basePrice * 0.08; // Example tax rate
        } else if ("EU".equalsIgnoreCase(region)) {
            return basePrice * 0.20;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}