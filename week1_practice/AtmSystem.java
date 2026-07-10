import java.util.Scanner;

public class AtmSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;

        boolean running = true;

        while (running) {
            printMenu();
            int option = readMenuOption(sc);

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int depositAmount = readPositiveAmount(
                            sc, "請輸入存款金額：");

                    if (depositAmount > Integer.MAX_VALUE - balance) {
                        System.out.println("存款失敗：金額過大。");
                        break;
                    }

                    balance = deposit(balance, depositAmount);
                    depositCount++;
                    totalDeposit += depositAmount;

                    System.out.println("存款成功。");
                    printBalance(balance);
                    break;

                case 3:
                    int withdrawAmount = readPositiveAmount(
                            sc, "請輸入提款金額：");

                    if (canWithdraw(balance, withdrawAmount)) {
                        balance = withdraw(balance, withdrawAmount);
                        withdrawCount++;
                        totalWithdraw += withdrawAmount;

                        System.out.println("提款成功。");
                        printBalance(balance);
                    } else {
                        System.out.println("提款失敗：帳戶餘額不足。");
                        printBalance(balance);
                    }
                    break;

                case 4:
                    
                    printSummary(
                            balance,
                            depositCount,
                            withdrawCount,
                            totalDeposit,
                            totalWithdraw);
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    
                    break;
            }

            System.out.println();
        }

        printSummary(
                balance,
                depositCount,
                withdrawCount,
                totalDeposit,
                totalWithdraw);

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    public static int readMenuOption(Scanner sc) {
        while (true) {
            System.out.print("請輸入選項：");
            String input = sc.nextLine().trim();

            try {
                int option = Integer.parseInt(input);

                if (option >= 0 && option <= 4) {
                    return option;
                }

                System.out.println("輸入錯誤：選項必須介於 0 到 4。");
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤：請輸入整數選項。");
            }
        }
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                int amount = Integer.parseInt(input);

                if (amount > 0) {
                    return amount;
                }

                System.out.println("輸入錯誤：金額必須大於 0。");
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤：請輸入正確的整數金額。");
            }
        }
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }

    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }

    public static void printSummary(
            int balance,
            int depositCount,
            int withdrawCount,
            int totalDeposit,
            int totalWithdraw) {

        System.out.println("=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
    }
}