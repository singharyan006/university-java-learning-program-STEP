public class PersonalFinanceManager {

    static class PersonalAccount {
        private String accountHolderName;
        private String accountNumber;
        private double currentBalance;
        private double totalIncome;
        private double totalExpenses;

        private static int totalAccounts = 0;
        private static String bankName = "Default Bank";

        // Constructor
        public PersonalAccount(String accountHolderName) {
            this.accountHolderName = accountHolderName;
            this.accountNumber = generateAccountNumber();
            this.currentBalance = 0.0;
            this.totalIncome = 0.0;
            this.totalExpenses = 0.0;
            totalAccounts++;
        }

        // Static method to generate account number
        public static String generateAccountNumber() {
            return String.format("AC%03d", totalAccounts + 1);
        }

        // Static method to set bank name
        public static void setBankName(String name) {
            bankName = name;
        }

        // Static method to get total accounts
        public static int getTotalAccounts() {
            return totalAccounts;
        }

        // Add income
        public void addIncome(double amount, String description) {
            if (amount <= 0) {
                System.out.println("Invalid income amount.");
                return;
            }
            currentBalance += amount;
            totalIncome += amount;
            System.out.println(description + ": ₹" + amount + " added to " + accountNumber);
        }

        // Add expense
        public void addExpense(double amount, String description) {
            if (amount <= 0) {
                System.out.println("Invalid expense amount.");
                return;
            }
            if (amount > currentBalance) {
                System.out.println("Insufficient balance for expense: " + description);
                return;
            }
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println(description + ": ₹" + amount + " deducted from " + accountNumber);
        }

        // Calculate savings
        public double calculateSavings() {
            return totalIncome - totalExpenses;
        }

        // Display account summary
        public void displayAccountSummary() {
            System.out.println("=== Account Summary ===");
            System.out.println("Bank Name       : " + bankName);
            System.out.println("Account Number  : " + accountNumber);
            System.out.println("Holder Name     : " + accountHolderName);
            System.out.println("Current Balance : ₹" + String.format("%.2f", currentBalance));
            System.out.println("Total Income    : ₹" + String.format("%.2f", totalIncome));
            System.out.println("Total Expenses  : ₹" + String.format("%.2f", totalExpenses));
            System.out.println("Savings         : ₹" + String.format("%.2f", calculateSavings()));
            System.out.println("========================\n");
        }
    }

    public static void main(String[] args) {
        // Set bank name
        PersonalAccount.setBankName("Unity Bank");

        // Create accounts
        PersonalAccount acc1 = new PersonalAccount("Alice");
        PersonalAccount acc2 = new PersonalAccount("Bob");
        PersonalAccount acc3 = new PersonalAccount("Charlie");

        // Perform transactions
        acc1.addIncome(10000, "Salary");
        acc1.addExpense(2500, "Rent");
        acc1.addExpense(1200, "Groceries");

        acc2.addIncome(8000, "Freelance");
        acc2.addExpense(3000, "Laptop Purchase");

        acc3.addIncome(15000, "Consulting");
        acc3.addExpense(5000, "Travel");
        acc3.addExpense(2000, "Dining");

        // Display summaries
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Show static vs instance
        System.out.println("Total Accounts Created : " + PersonalAccount.getTotalAccounts());
        System.out.println("Bank Name (Shared)     : " + PersonalAccount.bankName);
    }
}
