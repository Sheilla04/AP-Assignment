package Lecture4_interfaces_abstract_classes;
import java.util.Calendar;

public class TransactionDemo {
    public static void main(String[] args) throws InsufficientFundsException {
        Calendar date = Calendar.getInstance();

        // Create a BankAccount with an initial balance of 1000
        BankAccount account = new BankAccount(10000);

        // Create a DepositTransaction object
        DepositTransaction deposit = new DepositTransaction(2500, date);

        // Test apply() method of DepositTransaction
        System.out.println("Testing DepositTransaction:");
        deposit.printTransactionDetails();
        deposit.apply(account);
        System.out.println("Account balance after deposit: " + account.getBalance());

        // Create a WithdrawalTransaction object
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(1900, date);

        // Test apply() method of WithdrawalTransaction
        System.out.println("\nTesting WithdrawalTransaction:");
        withdrawal.printTransactionDetails();
        try {
            withdrawal.apply(account);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println("Account balance after withdrawal: " + account.getBalance());

        // Reset balance and test overloaded apply() method for partial withdrawal
        System.out.println("\nTesting Partial Withdrawal:");
        account.setBalance(1200); // Resetting balance to 200 for testing
        withdrawal.apply(account, true);
        System.out.println("Account balance after partial withdrawal: " + account.getBalance());

        // Type casting DepositTransaction to BaseTransaction and testing apply()
        System.out.println("\nTesting BaseTransaction behavior:");
        BaseTransaction baseDeposit = (BaseTransaction) deposit;
        baseDeposit.printTransactionDetails();
        baseDeposit.apply(account); // Calls the apply() method in DepositTransaction
        System.out.println("Account balance after base deposit transaction: " + account.getBalance());

        // Type casting WithdrawalTransaction to BaseTransaction and testing apply()
        BaseTransaction baseWithdrawal = (BaseTransaction) withdrawal;
        baseWithdrawal.printTransactionDetails();
        try {
            baseWithdrawal.apply(account); // Calls the apply() method in WithdrawalTransaction
        } catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println("Account balance after base withdrawal transaction: " + account.getBalance());
    }
}



