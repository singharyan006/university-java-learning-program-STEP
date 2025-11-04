public class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String processorId, String securityKey) {
        this.processorId = processorId;
        this.securityKey = securityKey;
    }

    public boolean processPayment(double amount) {
        // Dummy payment processing logic
        System.out.println("Processing payment of $" + String.format("%.2f", amount) + " with processor " + processorId);
        return amount > 0;
    }
}