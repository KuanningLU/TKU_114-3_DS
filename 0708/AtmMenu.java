import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;
        int option = -1;
        int amount;

        while (option != 0) {
            System.out.println("=== ATM Menu ===");
            System.out.println("1. 查詢餘額");
            System.out.println("2. 存款");
            System.out.println("3. 提款");
            System.out.println("0. 離開");
            System.out.print("請輸入選項：");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("目前餘額：" + balance);
                    break;

                case 2:
                    System.out.print("請輸入存款金額：");
                    amount = sc.nextInt();

                    while (amount <= 0) {
                        System.out.print("存款金額必須大於 0，請重新輸入：");
                        amount = sc.nextInt();
                    }

                    balance += amount;

                    System.out.println("存款成功！");
                    System.out.println("目前餘額：" + balance);
                    break;

                case 3:
                    System.out.print("請輸入提款金額：");
                    amount = sc.nextInt();

                    while (amount <= 0 || amount > balance) {
                        if (amount <= 0) {
                            System.out.print("提款金額必須大於 0，請重新輸入：");
                        } else {
                            System.out.print("餘額不足，請重新輸入提款金額：");
                        }

                        amount = sc.nextInt();
                    }

                    balance -= amount;

                    System.out.println("提款成功！");
                    System.out.println("目前餘額：" + balance);
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
}