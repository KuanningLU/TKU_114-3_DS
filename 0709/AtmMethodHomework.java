import java.util.Scanner;

public class AtmMethodHomework {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;
        int option = -1;

        while (option != 0) {
            printMenu();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int depositAmount = readPositiveAmount(
                            sc, "請輸入存款金額：");

                    balance = deposit(balance, depositAmount);

                    System.out.println("存款成功！");
                    printBalance(balance);
                    break;

                case 3:
                    int withdrawAmount = readPositiveAmount(
                            sc, "請輸入提款金額：");

                    while (withdrawAmount > balance) {
                        System.out.println("餘額不足，目前餘額為：" + balance);

                        withdrawAmount = readPositiveAmount(
                                sc, "請重新輸入提款金額：");
                    }

                    balance = withdraw(balance, withdrawAmount);

                    System.out.println("提款成功！");
                    printBalance(balance);
                    break;

                case 0:
                    System.out.println("謝謝使用 ATM！");
                    break;

                default:
                    System.out.println("選項錯誤，請重新輸入。");
            }

            System.out.println();
        }

        sc.close();
    }


    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("0. 離開");
        System.out.print("請輸入選項：");
    }


    public static int readPositiveAmount(Scanner sc, String message) {
        System.out.print(message);
        int amount = sc.nextInt();

        while (amount <= 0) {
            System.out.print("金額必須大於 0，請重新輸入：");
            amount = sc.nextInt();
        }

        return amount;
    }

    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    public static void printBalance(int balance) {
        System.out.println("目前餘額：" + balance);
    }
}