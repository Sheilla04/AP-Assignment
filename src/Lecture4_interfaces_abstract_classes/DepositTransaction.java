package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;
import java.util.Calendar;

public class DepositTransaction extends BaseTransaction {

    public DepositTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    // Helper method to check if deposit amount is valid
    private boolean checkDepositAmount(int amt) {
        return amt > 0; // Return true if the amount is positive
    }

    // Method to print a transaction receipt or details
    @Override
    public void printTransactionDetails() {
        System.out.println("Deposit Transaction Details:");
        System.out.println("Amount: " + getAmount());
        System.out.println("Date: " + getDate().getTime());
        System.out.println("Transaction ID: " + getTransactionID());
    }

    // Method to apply the deposit to the bank account
    @Override
    public void apply(BankAccount ba) {
        if (checkDepositAmount((int) getAmount())) {
            double currBalance = ba.getBalance();
            double newBalance = currBalance + getAmount();
            ba.setBalance(newBalance);
            System.out.println("Deposit applied successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}

