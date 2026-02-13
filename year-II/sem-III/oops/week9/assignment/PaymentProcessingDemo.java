public class PaymentProcessingDemo {

    interface Discount {
        double apply(double amount);
    }

    static class Payment {
        private double amount;

        public Payment(double amount) {
            this.amount = amount;
        }

        public void processTransaction() {
            // Local Inner Class
            class Validator {
                public boolean isValid() {
                    return amount > 0;
                }
            }

            Validator validator = new Validator();
            if (!validator.isValid()) {
                System.out.println("Invalid payment amount.");
                return;
            }

            // Anonymous Inner Class
            Discount discount = new Discount() {
                @Override
                public double apply(double amt) {
                    return amt * 0.90; // 10% discount
                }
            };

            double finalAmount = discount.apply(amount);
            System.out.println("Original Amount: ₹" + amount);
            System.out.println("Final Amount after Discount: ₹" + finalAmount);
        }
    }

    public static void main(String[] args) {
        Payment payment = new Payment(1000.0);
        payment.processTransaction();
    }
}
