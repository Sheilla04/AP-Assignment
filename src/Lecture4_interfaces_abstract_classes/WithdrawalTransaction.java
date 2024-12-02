package Lecture4_interfaces_abstract_classes;
import org.jetbrains.annotations.NotNull;
import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {
    private boolean isReversed = false; // Flag to track if the transaction has been reversed
    private double amountNotWithdrawn = 0.0; // To record the amount not withdrawn

    public WithdrawalTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    // Helper method to check if withdrawal amount is valid
    private boolean checkWithdrawalAmount(int amt) {
        return amt > 0; // Return true if the amount is positive
    }

    // Original apply method with exception handling
    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        double currBalance = ba.getBalance();
        if (currBalance >= getAmount()) {
            ba.setBalance(currBalance - getAmount());
            System.out.println("Withdrawal applied successfully.");
        } else {
            throw new InsufficientFundsException("Insufficient funds for the transaction.");
        }
    }

    // Overloaded apply method with additional checks
    public void apply(BankAccount ba, boolean allowPartialWithdrawal) {
        double currBalance = ba.getBalance();
        try {
            if (currBalance <= 0) {
                throw new InsufficientFundsException("Account balance is zero, withdrawal cannot proceed.");
            } else if (currBalance < getAmount() && allowPartialWithdrawal) {
                amountNotWithdrawn = getAmount() - currBalance;
                ba.setBalance(0); // Withdraw all available balance
                System.out.println("Partial withdrawal applied. Remaining amount not withdrawn: " + amountNotWithdrawn);
            } else {
                apply(ba); // Use the original apply method
            }
        } catch (InsufficientFundsException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        } finally {
            System.out.println("Transaction complete.");
        }
    }

    // Method to reverse the transaction
    public boolean reverse(BankAccount ba) {
        if (!isReversed) { // Ensure the transaction hasn't already been reversed
            double newBalance = ba.getBalance() + getAmount();
            ba.setBalance(newBalance);
            isReversed = true; // Mark the transaction as reversed
            System.out.println("Withdrawal reversed successfully.");
            return true;
        } else {
            System.out.println("Transaction has already been reversed.");
            return false;
        }
    }

    // Method to print a transaction receipt or details
    @Override
    public void printTransactionDetails() {
        System.out.println("Withdrawal Transaction Details:");
        System.out.println("Amount: " + getAmount());
        System.out.println("Date: " + getDate().getTime());
        System.out.println("Transaction ID: " + getTransactionID());
        if (amountNotWithdrawn > 0) {
            System.out.println("Amount not withdrawn: " + amountNotWithdrawn);
        }
    }
}


