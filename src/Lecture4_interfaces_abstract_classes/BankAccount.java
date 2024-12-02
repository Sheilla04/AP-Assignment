package Lecture4_interfaces_abstract_classes;

public class BankAccount {
    private double balance;

    /** Constructor to initialize the account with a balance*/
    public BankAccount(double balance) {
        this.balance = balance;
    }

    /** Method to get the current balance*/
    public double getBalance() {
        return balance;
    }

    /** Method to set a new balance*/
    public void setBalance(double balance) {
        this.balance = balance;
        System.out.println("Updated balance: " + balance);
    }
}

