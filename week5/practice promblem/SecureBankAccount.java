public class SecureBankAccount {

    // Private fields - data hiding
    private final String accountNumber;  // read-only after creation
    private double balance;              // only modified via methods
    private int pin;                    // write-only (no getter)
    private boolean isLocked;
    private int failedAttempts;

    // Private constants
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    // Constructor
    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.pin = 0;          // must be set separately
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // Account Info Methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot access balance.");
            return -1;  // Indicate error
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // Security Methods

    /**
     * Change the PIN if old PIN matches.
     * @param oldPin current PIN for verification
     * @param newPin new PIN to set
     * @return true if changed successfully
     */
    public boolean setPin(int oldPin, int newPin) {
        if (pin == 0 || validatePin(oldPin)) {  // pin==0 means first time set
            this.pin = newPin;
            resetFailedAttempts();
            System.out.println("PIN changed successfully.");
            return true;
        } else {
            System.out.println("Old PIN incorrect. Cannot change PIN.");
            return false;
        }
    }

    /**
     * Validates entered PIN; increments failed attempts if wrong; locks account if limit exceeded.
     * @param enteredPin PIN entered
     * @return true if PIN is correct and account not locked
     */
    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked. Access denied.");
            return false;
        }
        if (this.pin == enteredPin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            System.out.println("Invalid PIN.");
            return false;
        }
    }

    /**
     * Unlocks the account if correct PIN provided.
     * @param correctPin correct PIN to unlock
     * @return true if unlocked successfully
     */
    public boolean unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked.");
            return true;
        } else {
            System.out.println("Incorrect PIN. Cannot unlock account.");
            return false;
        }
    }

    // Transaction Methods

    /**
     * Deposit money if PIN is valid and account not locked
     */
    public boolean deposit(double amount, int pin) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        if (validatePin(pin)) {
            balance += amount;
            System.out.printf("Deposited %.2f. New balance: %.2f%n", amount, balance);
            return true;
        }
        return false;
    }

    /**
     * Withdraw money if PIN valid, sufficient funds, and account not locked
     */
    public boolean withdraw(double amount, int pin) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (validatePin(pin)) {
            if (balance - amount >= MIN_BALANCE) {
                balance -= amount;
                System.out.printf("Withdrew %.2f. New balance: %.2f%n", amount, balance);
                return true;
            } else {
                System.out.println("Insufficient funds for withdrawal.");
                return false;
            }
        }
        return false;
    }

    /**
     * Transfer money from this account to target account, if PIN valid and sufficient funds.
     */
    public boolean transfer(SecureBankAccount target, double amount, int pin) {
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return false;
        }
        if (target == null) {
            System.out.println("Invalid target account.");
            return false;
        }
        if (validatePin(pin)) {
            if (balance - amount >= MIN_BALANCE) {
                balance -= amount;
                target.balance += amount;
                System.out.printf("Transferred %.2f to %s. New balance: %.2f%n", amount, target.getAccountNumber(), balance);
                return true;
            } else {
                System.out.println("Insufficient funds for transfer.");
                return false;
            }
        }
        return false;
    }

    // Private Helper Methods
    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to too many failed PIN attempts.");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        } else {
            System.out.printf("Failed attempts: %d%n", failedAttempts);
        }
    }

    // Demo main method to test behavior
    public static void main(String[] args) {
        // Create two accounts
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 500);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 1000);

        // Trying to access private fields directly (uncomment to see errors)
        // acc1.balance = 10000;   // Error: balance has private access
        // System.out.println(acc1.pin); // Error: pin has private access

        System.out.println("--- Setting PINs ---");
        acc1.setPin(0, 1234);    // first time setting, oldPin ignored
        acc2.setPin(0, 5678);

        System.out.println("\n--- Transactions ---");
        acc1.deposit(200, 1234);
        acc1.withdraw(100, 1234);

        System.out.println("\n--- Security Checks ---");
        // Wrong PIN attempts on acc1
        acc1.withdraw(50, 1111);
        acc1.withdraw(50, 2222);
        acc1.withdraw(50, 3333);  // Should lock account now

        System.out.println("Is acc1 locked? " + acc1.isAccountLocked());

        // Try operations on locked account
        acc1.deposit(50, 1234);
        acc1.withdraw(50, 1234);

        // Unlock account with correct PIN
        acc1.unlockAccount(1234);

        // Try again after unlock
        acc1.deposit(50, 1234);

        System.out.println("\n--- Transfer Money ---");
        acc1.transfer(acc2, 100, 1234);

        System.out.printf("acc1 balance: %.2f%n", acc1.getBalance());
        System.out.printf("acc2 balance: %.2f%n", acc2.getBalance());

        System.out.println("\n--- Attempt to withdraw more than balance ---");
        acc1.withdraw(10000, 1234);

        System.out.println("\nDemo complete.");
    }
}