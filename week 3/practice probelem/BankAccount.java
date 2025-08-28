public class BankAccount {
    // Static variables
    private static String bankName;
    private static int totalAccounts = 0;
    private static double interestRate;

    // Instance variables
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++;
    }

    // Static methods
    public static void setBankName(String name) { bankName = name; }
    public static void setInterestRate(double rate) { interestRate = rate; }
    public static int getTotalAccounts() { return totalAccounts; }
    public static void displayBankInfo() {
        System.out.println("Bank: " + bankName + ", Total Accounts: " + totalAccounts + ", Interest Rate: " + interestRate + "%");
    }

    // Instance methods
    public void deposit(double amount) {
        balance += amount;
        System.out.println(accountHolder + " deposited $" + amount + ". New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew $" + amount + ". Remaining Balance: " + balance);
        } else {
            System.out.println("Insufficient balance for " + accountHolder);
        }
    }

    public void calculateInterest() {
        double interest = balance * interestRate / 100;
        System.out.println(accountHolder + " earned interest: $" + interest);
    }

    public void displayAccountInfo() {
        System.out.println("Account: " + accountNumber + ", Holder: " + accountHolder + ", Balance: " + balance);
    }

    public static void main(String[] args) {
        // Static method usage
        BankAccount.setBankName("State Bank of India");
        BankAccount.setInterestRate(5.0);

        // Creating accounts
        BankAccount acc1 = new BankAccount("A001", "Alice", 1000);
        BankAccount acc2 = new BankAccount("A002", "Bob", 2000);

        // Instance method usage
        acc1.deposit(500);
        acc2.withdraw(300);

        acc1.calculateInterest();
        acc2.calculateInterest();

        // Static method usage
        BankAccount.displayBankInfo();

        /*
         * Static variables (bankName, interestRate, totalAccounts) are shared by all objects.
         * Instance variables (accountNumber, accountHolder, balance) are unique per object.
         */
    }
}
