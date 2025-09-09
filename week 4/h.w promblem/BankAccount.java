import java.util.Random;

public class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private double balance;

    private static Random rand = new Random();

    // Default constructor → balance = 0
    public BankAccount() {
        this("Unknown", 0);
    }

    // Constructor with name → assigns random account number, balance = 0
    public BankAccount(String accountHolder) {
        this(accountHolder, 0);
        this.accountNumber = generateAccountNumber();
    }

    // Constructor with name and initial balance → assigns both, random account number
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.accountNumber = generateAccountNumber();
    }

    // Helper method to generate random 8-digit account number
    private int generateAccountNumber() {
        return 10000000 + rand.nextInt(90000000);
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("₹%.2f deposited. New balance: ₹%.2f%n", amount, balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.printf("₹%.2f withdrawn. New balance: ₹%.2f%n", amount, balance);
            } else {
                System.out.println("Insufficient funds!");
            }
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    // Display account details
    public void displayAccount() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Balance: ₹%.2f%n", balance);
        System.out.println("------------------------");
    }

    // Main method to test BankAccount
    public static void main(String[] args) {
        BankAccount a1 = new BankAccount();
        BankAccount a2 = new BankAccount("Alice");
        BankAccount a3 = new BankAccount("Bob", 5000);

        a1.displayAccount();
        a2.displayAccount();
        a3.displayAccount();

        a2.deposit(1500);
        a3.withdraw(1000);
        a3.withdraw(6000); // insufficient funds

        a2.displayAccount();
        a3.displayAccount();
    }
}