//  Bank Account System 
// �
// �
 
// Create a Bank Account management program. 
// ● Class BankAccount with fields: String accountHolder, int accountNumber, 
// double balance. 
// ● Implement constructor overloading: 
// ○ Default constructor → balance = 0. 
// ○ Constructor with name → assigns random account number. 
// ○ Constructor with name and initial balance → assigns both. 
// ● Add methods: 
// 1 
// ○ deposit(double amount) 
// ○ withdraw(double amount) 
// ○ displayAccount() 
// ● In main(): Create accounts, deposit/withdraw, and display balance. 


import java.util.*;

class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private double balance;

    // Default constructor
    public BankAccount() {
        this("Unknown", 0.0);
    }

    // Constructor with name only
    public BankAccount(String accountHolder) {
        this(accountHolder, 0.0);
    }

    // Constructor with name and initial balance
    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.balance = balance;
    }

    // Random account number generator
    private int generateAccountNumber() {
        return new Random().nextInt(900000) + 100000; // 6-digit number
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount + " to " + accountHolder + "'s account.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew ₹" + amount + " from " + accountHolder + "'s account.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Display account details
    public void displayAccount() {
        System.out.println("🏦 Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
        System.out.println("---------------------------");
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount("Aryan");
        BankAccount acc3 = new BankAccount("Riya", 5000.0);

        acc1.deposit(1000);
        acc2.deposit(2000);
        acc3.withdraw(1500);

        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();
    }
}
