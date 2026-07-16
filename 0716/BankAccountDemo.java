public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("A001", "Amy", 5000);
        BankAccount account2 = new BankAccount("A002", "Bob", 2000);

        System.out.println("===== Initial Accounts =====");
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n===== Deposit Test =====");
        boolean depositResult = account1.deposit(1000);
        System.out.println("Deposit $1000: " + depositResult);
        System.out.println(account1);

        System.out.println("\n===== Withdraw Test =====");
        boolean withdrawResult = account2.withdraw(500);
        System.out.println("Withdraw $500: " + withdrawResult);
        System.out.println(account2);

        System.out.println("\n===== Successful Transfer Test =====");
        boolean transferResult1 = account1.transferTo(account2, 2000);
        System.out.println("Transfer $2000: " + transferResult1);
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n===== Failed Transfer Test =====");
        boolean transferResult2 = account2.transferTo(account1, 10000);
        System.out.println("Transfer $10000: " + transferResult2);
        System.out.println(account1);
        System.out.println(account2);
    }
}