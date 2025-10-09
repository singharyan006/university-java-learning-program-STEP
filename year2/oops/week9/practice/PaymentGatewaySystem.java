// File: PaymentGatewaySystem.java

class Payment {
    public void pay() {
        System.out.println("Generic payment");
    }
}

class CreditCardPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Paid using Credit Card");
    }
}

class WalletPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Paid using Wallet");
    }
}

public class PaymentGatewaySystem {
    public static void main(String[] args) {
        // 1. Create array of Payment references
        Payment[] payments = {
            new CreditCardPayment(),
            new WalletPayment(),
            new Payment()
        };

        // 2. Loop, call getClass().getSimpleName(), and pay()
        for (Payment p : payments) {
            System.out.println("Payment Type: " + p.getClass().getSimpleName());
            p.pay();
            System.out.println("---");
        }
    }
}
