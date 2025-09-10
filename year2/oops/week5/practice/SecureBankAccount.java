// PRACTICE PROBLEM 3: Data Hiding Mastery 
// Implementing proper encapsulation with private fields and public methods 
 
// public class SecureBankAccount { 
//     // TODO: Create private fields that should NEVER be accessed directly: 
//     // - accountNumber (String) - read-only after creation 
//     // - balance (double) - only modified through controlled methods 
//     // - pin (int) - write-only for security 
//     // - isLocked (boolean) - internal security state 
//     // - failedAttempts (int) - internal security counter 
     
//     // TODO: Create private constants: 
//     // - MAX_FAILED_ATTEMPTS (int) = 3 
//     // - MIN_BALANCE (double) = 0.0 
     
//     // TODO: Create constructor that takes accountNumber and initial balance 
//     // TODO: Initialize pin to 0 (must be set separately) 
     
//     // TODO: Create PUBLIC methods for controlled access: 
     
//     // Account Info Methods: 
//     // - getAccountNumber() - returns account number 
//     // - getBalance() - returns current balance (only if not locked) 
//     // - isAccountLocked() - returns lock status 
// 3 
 
     
//     // Security Methods: 
//     // - setPin(int oldPin, int newPin) - changes PIN if old PIN correct 
//     // - validatePin(int enteredPin) - checks PIN, handles failed attempts 
//     // - unlockAccount(int correctPin) - unlocks if PIN correct 
     
//     // Transaction Methods: 
//     // - deposit(double amount, int pin) - adds money if PIN valid 
//     // - withdraw(double amount, int pin) - removes money if PIN valid and sufficient funds 
//     // - transfer(SecureBankAccount target, double amount, int pin) - transfers between accounts 
     
//     // TODO: Create private helper methods: 
//     // - lockAccount() - sets isLocked to true 
//     // - resetFailedAttempts() - resets counter to 0 
//     // - incrementFailedAttempts() - increases counter, locks if needed 
     
//     public static void main(String[] args) { 
//         // TODO: Create two SecureBankAccount objects 
//         // TODO: Try to access private fields directly (should fail) 
//         // TODO: Demonstrate proper usage through public methods: 
//         //   - Set PINs for both accounts 
//         //   - Make deposits and withdrawals 
//         //   - Show security features (account locking) 
//         //   - Transfer money between accounts 
//         // TODO: Attempt security breaches: 
//         //   - Wrong PIN multiple times 
//         //   - Withdrawing more than balance 
//         //   - Operating on locked account 
//     } 
// } 
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
        this.pin = 0;
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // Account Info Methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot retrieve balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // Security Methods
    public boolean setPin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            resetFailedAttempts();
            return true;
        }
        incrementFailedAttempts();
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (this.pin == enteredPin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }

    public boolean unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            return true;
        }
        return false;
    }

    // Transaction Methods
    public boolean deposit(double amount, int pin) {
        if (isLocked || !validatePin(pin)) return false;
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount, int pin) {
        if (isLocked || !validatePin(pin)) return false;
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        System.out.println("Insufficient funds or invalid amount.");
        return false;
    }

    public boolean transfer(SecureBankAccount target, double amount, int pin) {
        if (withdraw(amount, pin)) {
            return target.deposit(amount, pin);
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
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 5000);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 3000);

        // Direct access (should fail)
        // System.out.println(acc1.balance); // ❌ Error: private field

        // Set PINs
        acc1.setPin(0, 1234);
        acc2.setPin(0, 5678);

        // Valid transactions
        acc1.deposit(1000, 1234);
        acc1.withdraw(2000, 1234);
        acc1.transfer(acc2, 500, 1234);

        // Security breach: wrong PIN multiple times
        acc1.validatePin(1111);
        acc1.validatePin(2222);
        acc1.validatePin(3333); // Should lock account

        // Attempt operation on locked account
        acc1.withdraw(100, 1234); // Should fail

        // Unlock account
        acc1.unlockAccount(1234);
        acc1.withdraw(100, 1234); // Should succeed
    }
}
