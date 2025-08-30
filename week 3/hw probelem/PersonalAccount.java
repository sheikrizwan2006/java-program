public class PersonalAccount {
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";

    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        totalAccounts++;
    }

    public void addIncome(double amount, String description) {
        if (amount > 0) {
            totalIncome += amount;
            currentBalance += amount;
            System.out.println(description + " added: $" + amount);
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            totalExpenses += amount;
            currentBalance -= amount;
            System.out.println(description + " spent: $" + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount for " + description);
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n--- Account Summary ---");
        System.out.println("Bank Name: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + currentBalance);
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpenses);
        System.out.println("Savings: $" + calculateSavings());
    }

    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static String generateAccountNumber() {
        return "ACCT" + (1000 + totalAccounts + 1);
    }
}

public class Main {
    public static void main(String[] args) {
        PersonalAccount.setBankName("Future Finance Bank");

        PersonalAccount acc1 = new PersonalAccount("Alice Johnson");
        PersonalAccount acc2 = new PersonalAccount("Bob Smith");
        PersonalAccount acc3 = new PersonalAccount("Charlie Brown");

        acc1.addIncome(2000, "Salary");
        acc1.addExpense(500, "Rent");
        acc1.addExpense(200, "Groceries");

        acc2.addIncome(3000, "Freelance Project");
        acc2.addExpense(1000, "Travel");
        acc2.addExpense(500, "Utilities");

        acc3.addIncome(1500, "Part-time Job");
        acc3.addExpense(300, "Phone Bill");

        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        System.out.println("\nTotal Accounts Created: " + PersonalAccount.getTotalAccounts());

        System.out.println("\nDemonstrating Static vs Instance Variables:");
        System.out.println("Bank Name (static): Shared by all accounts.");
        System.out.println("Balance (instance): Unique to each account.");
    }
}