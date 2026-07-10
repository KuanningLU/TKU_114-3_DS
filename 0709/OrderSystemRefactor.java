import java.util.Scanner;

public class OrderSystemRefactor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalItems = 0;
        int totalAmount = 0;

        while (true) {
            printMenu();

            int option = sc.nextInt();

            if (option == 0) {
                printReceipt(totalItems, totalAmount);
                break;
            }

            int price = getPrice(option);

            if (price == -1) {
                System.out.println("選項錯誤，請重新輸入。");
                System.out.println();
                continue;
            }

            String itemName = getItemName(option);
            int quantity = readValidQuantity(sc);
            int subtotal = calculateSubtotal(price, quantity);

            totalItems += quantity;
            totalAmount += subtotal;

            System.out.println("商品：" + itemName);
            System.out.println("單價：" + price);
            System.out.println("數量：" + quantity);
            System.out.println("小計：" + subtotal);
            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("===== 點餐系統 =====");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
        System.out.print("請輸入選項：");
    }


    public static int getPrice(int option) {
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 50;
            default:
                return -1;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1:
                return "Black tea";
            case 2:
                return "Green tea";
            case 3:
                return "Coffee";
            default:
                return "";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();

        while (quantity <= 0) {
            System.out.print("數量必須大於 0，請重新輸入：");
            quantity = sc.nextInt();
        }

        return quantity;
    }

    // 計算小計
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("===== 結帳 =====");
        System.out.println("總數量：" + totalItems);
        System.out.println("總金額：" + totalAmount);
    }
}