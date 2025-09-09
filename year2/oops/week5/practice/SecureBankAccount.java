public class SecureBankAccount {
    // Private fields
    private final String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;

    // Private constants
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    // Constructor
    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, MIN_BALANCE);
        this.pin = 0; // Must be set separately
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // Account Info Methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (!isLocked) {
            return balance;
        }
        System.out.println("Account is locked. Cannot retrieve balance.");
        return -1;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // Security Methods
    public boolean setPin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN updated successfully.");
            return true;
        }
        System.out.println("Incorrect old PIN.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked.");
            return false;
        }
        if (enteredPin == pin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }

    public boolean unlockAccount(int correctPin) {
        if (correctPin == pin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked.");
            return true;
        }
        System.out.println("Incorrect PIN. Cannot unlock.");
        return false;
    }

    // Transaction Methods
    public boolean deposit(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount);
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
                System.out.println("Withdrew: " + amount);
                return true;
            } else {
                System.out.println("Insufficient funds.");
            }
        }
        return false;
    }

    public boolean transfer(SecureBankAccount target, double amount, int pin) {
        if (withdraw(amount, pin)) {
            target.deposit(amount, pin);
            System.out.println("Transferred: " + amount + " to " + target.getAccountNumber());
            return true;
        }
        return false;
    }

    // Private helper methods
    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to multiple failed attempts.");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        System.out.println("Failed attempt #" + failedAttempts);
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 500.0);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 300.0);

        // Direct access to private fields (should fail)
        // System.out.println(acc1.balance); // ❌ Compile-time error

        // Set PINs
        acc1.setPin(0, 1234);
        acc2.setPin(0, 5678);

        // Valid transactions
        acc1.deposit(200, 1234);
        acc1.withdraw(100, 1234);
        acc1.transfer(acc2, 50, 1234);

        // Security breach: wrong PIN multiple times
        acc1.validatePin(1111);
        acc1.validatePin(2222);
        acc1.validatePin(3333); // Should lock account

        // Attempt operation on locked account
        acc1.withdraw(50, 1234); // Should fail

        // Unlock account
        acc1.unlockAccount(1234);
        acc1.withdraw(50, 1234); // Should succeed

        // Over-withdrawal
        acc2.withdraw(1000, 5678); // Should fail
    }
}
