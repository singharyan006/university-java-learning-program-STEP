import java.util.Map;

public class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    public ShippingCalculator(Map<String, Double> rates) {
        this.shippingRates = rates;
    }

    public double calculateShippingCost(String region, double weight) {
        double rate = shippingRates.getOrDefault(region, 0.0);
        return rate * weight;
    }
}