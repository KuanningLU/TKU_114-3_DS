import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int option = -1;
        int quantity;
        int price = 0;
        int subtotal;
        int totalQuantity = 0;
        int totalAmount = 0;
        String productName = "";

        while (option != 0) {
            System.out.println("===== 點餐系統 =====");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入選項：");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    productName = "Black tea";
                    price = 30;
                    break;

                case 2:
                    productName = "Green tea";
                    price = 35;
                    break;

                case 3:
                    productName = "Coffee";
                    price = 50;
                    break;

                case 0:
                    System.out.println("===== 結帳 =====");
                    System.out.println("總數量：" + totalQuantity);
                    System.out.println("總金額：" + totalAmount);
                    break;

                default:
                    System.out.println("選項錯誤，請重新輸入。");
                    continue;
            }

            if (option >= 1 && option <= 3) {
                System.out.print("請輸入數量：");
                quantity = sc.nextInt();

                while (quantity <= 0) {
                    System.out.print("數量必須大於 0，請重新輸入：");
                    quantity = sc.nextInt();
                }

                subtotal = price * quantity;

                totalQuantity += quantity;
                totalAmount += subtotal;

                System.out.println("商品：" + productName);
                System.out.println("單價：" + price);
                System.out.println("數量：" + quantity);
                System.out.println("小計：" + subtotal);
                System.out.println();
            }
        }

        sc.close();
    }
}