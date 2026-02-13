class BankAccount {
    private String accountNumber;
    private double balance;
    private int pin;

    public BankAccount(String accountNumber, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }

    public boolean validatePin(int enteredPin) {
        return enteredPin == pin;
    }

    public void debit(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn. Remaining balance: ₹" + balance);
        } else {
            System.out.println("Insufficient balance. Transaction failed.");
        }
    }
}

class ATM {
    private BankAccount linkedAccount;

    public ATM(BankAccount linkedAccount) {
        this.linkedAccount = linkedAccount;
    }

    public void withdraw(int enteredPin, double amount) {
        System.out.println("insertCard()");
        System.out.println("enterPIN()");
        System.out.println("requestWithdrawal(" + amount + ")");
        if (linkedAccount.validatePin(enteredPin)) {
            linkedAccount.debit(amount);
            System.out.println("dispenseCash()");
            System.out.println("showConfirmation()");
        } else {
            System.out.println("Invalid PIN. Transaction failed.");
        }
    }
}

class Customer {
    private String name;
    private ATM atm;

    public Customer(String name, ATM atm) {
        this.name = name;
        this.atm = atm;
    }

    public void performWithdrawal(int pin, double amount) {
        System.out.println(name + " is requesting withdrawal...");
        atm.withdraw(pin, amount);
    }
}

public class ATMDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("1234567890", 50000.0, 1234);
        ATM atm = new ATM(account);
        Customer customer = new Customer("Ravi", atm);

        // Correct PIN
        customer.performWithdrawal(1234, 10000.0);

        System.out.println();

        // Incorrect PIN
        customer.performWithdrawal(9999, 5000.0);
    }
}
