// Write a program to create a Bank Account management system 
// without using built-in collection classes 
// Hint => 
// a. Create a BankAccount class with private instance variables: accountNumber 
// (String), accountHolderName (String), balance (double), and a static variable 
// totalAccounts (int) 
// b. Create a constructor that takes account holder name and initial deposit, 
// automatically generates account number using a static counter 
// c. Create instance methods: deposit(double amount), withdraw(double amount), 
// checkBalance() with proper validation for negative amounts and insufficient funds 
// d. Create static methods: getTotalAccounts(), generateAccountNumber() that 
// returns a unique account number like "ACC001", "ACC002" 
// e. Create a method displayAccountInfo() to show all account details in a formatted 
// manner 
// f. 
// In the main method, create an array of BankAccount objects, demonstrate 
// creating multiple accounts, performing transactions, and showing the difference 
// between static and instance variables 


public class BankAccountManager {

    static class BankAccount {
        private String accountNumber;
        private String accountHolderName;
        private double balance;
        private static int totalAccounts = 0;

        // Constructor
        public BankAccount(String accountHolderName, double initialDeposit) {
            if (initialDeposit < 0) {
                throw new IllegalArgumentException("Initial deposit cannot be negative.");
            }
            this.accountHolderName = accountHolderName;
            this.balance = initialDeposit;
            this.accountNumber = generateAccountNumber();
            totalAccounts++;
        }

        // Static method to generate unique account number
        public static String generateAccountNumber() {
            return String.format("ACC%03d", totalAccounts + 1);
        }

        // Static method to get total accounts
        public static int getTotalAccounts() {
            return totalAccounts;
        }

        // Deposit method
        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Deposit amount must be positive.");
                return;
            }
            balance += amount;
            System.out.println("Deposited ₹" + amount + " to " + accountNumber);
        }

        // Withdraw method
        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be positive.");
                return;
            }
            if (amount > balance) {
                System.out.println("Insufficient funds in " + accountNumber);
                return;
            }
            balance -= amount;
            System.out.println("Withdrew ₹" + amount + " from " + accountNumber);
        }

        // Check balance
        public double checkBalance() {
            return balance;
        }

        // Display account info
        public void displayAccountInfo() {
            System.out.println("----- Account Info -----");
            System.out.println("Account Number : " + accountNumber);
            System.out.println("Holder Name    : " + accountHolderName);
            System.out.println("Balance        : ₹" + String.format("%.2f", balance));
            System.out.println("------------------------");
        }
    }

    public static void main(String[] args) {
        // Create array of BankAccount objects
        BankAccount[] accounts = new BankAccount[3];

        // Create accounts
        accounts[0] = new BankAccount("Alice", 5000);
        accounts[1] = new BankAccount("Bob", 3000);
        accounts[2] = new BankAccount("Charlie", 7000);

        // Perform transactions
        accounts[0].deposit(1500);
        accounts[1].withdraw(1000);
        accounts[2].withdraw(8000); // Should show insufficient funds

        // Display account info
        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
        }

        // Show static vs instance variable
        System.out.println("Total Bank Accounts Created: " + BankAccount.getTotalAccounts());
    }
}
