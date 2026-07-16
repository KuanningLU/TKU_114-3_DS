import java.util.Scanner;

public class ProductArraySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {
            "Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"
        };

        int[] prices = {
            890, 490, 5200, 250, 1290
        };

        int[] stocks = {
            12, 20, 5, 30, 8
        };

        int purchaseCount = 0;
        int restockCount = 0;
        int option;

        do {
            showMenu();
            option = readInteger(sc, "請輸入選項：");

            switch (option) {
                case 1:
                    displayProducts(names, prices, stocks);
                    break;

                case 2:
                    searchProduct(sc, names, prices, stocks);
                    break;

                case 3:
                    if (purchaseProduct(sc, names, prices, stocks)) {
                        purchaseCount++;
                    }
                    break;

                case 4:
                    if (restockProduct(sc, names, stocks)) {
                        restockCount++;
                    }
                    break;

                case 5:
                    displayLowStock(names, prices, stocks);
                    break;

                case 6:
                    displayTotalStockValue(prices, stocks);
                    break;

                case 7:
                    displaySummary(purchaseCount, restockCount, prices, stocks);
                    System.out.println("系統結束。");
                    break;

                default:
                    System.out.println("選項錯誤，請輸入 1～7。");
            }

        } while (option != 7);

        sc.close();
    }

    public static void showMenu() {
        System.out.println("\n===== 商品陣列管理系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束並顯示操作摘要");
    }

    public static int readInteger(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                return sc.nextInt();
            }

            System.out.println("輸入錯誤，請輸入整數。");
            sc.next();
        }
    }

    public static void displayProducts(
        String[] names,
        int[] prices,
        int[] stocks
    ) {
        System.out.println("\n===== 全部商品 =====");
        System.out.printf(
            "%-8s %-15s %-10s %-10s%n",
            "編號", "商品名稱", "價格", "庫存"
        );

        for (int i = 0; i < names.length; i++) {
            System.out.printf(
                "%-8d %-15s %-10d %-10d%n",
                i + 1, names[i], prices[i], stocks[i]
            );
        }
    }

    public static int readProductIndex(
        Scanner sc,
        int productCount
    ) {
        while (true) {
            int number = readInteger(
                sc,
                "請輸入商品編號（1～" + productCount + "）："
            );

            if (number >= 1 && number <= productCount) {
                return number - 1;
            }

            System.out.println("商品編號錯誤。");
        }
    }

    public static void searchProduct(
        Scanner sc,
        String[] names,
        int[] prices,
        int[] stocks
    ) {
        int index = readProductIndex(sc, names.length);

        System.out.println("\n商品編號：" + (index + 1));
        System.out.println("商品名稱：" + names[index]);
        System.out.println("商品價格：" + prices[index]);
        System.out.println("商品庫存：" + stocks[index]);
    }

    public static boolean purchaseProduct(
        Scanner sc,
        String[] names,
        int[] prices,
        int[] stocks
    ) {
        int index = readProductIndex(sc, names.length);
        int quantity = readInteger(sc, "請輸入購買數量：");

        if (quantity <= 0) {
            System.out.println("購買數量必須大於 0。");
            return false;
        }

        if (quantity > stocks[index]) {
            System.out.println("庫存不足，目前庫存為 " + stocks[index] + "。");
            return false;
        }

        stocks[index] -= quantity;
        int amount = prices[index] * quantity;

        System.out.println("購買成功。");
        System.out.println("商品名稱：" + names[index]);
        System.out.println("購買數量：" + quantity);
        System.out.println("購買金額：" + amount);
        System.out.println("剩餘庫存：" + stocks[index]);

        return true;
    }

    public static boolean restockProduct(
        Scanner sc,
        String[] names,
        int[] stocks
    ) {
        int index = readProductIndex(sc, names.length);
        int quantity = readInteger(sc, "請輸入補貨數量：");

        if (quantity <= 0) {
            System.out.println("補貨數量必須大於 0。");
            return false;
        }

        stocks[index] += quantity;

        System.out.println("補貨成功。");
        System.out.println("商品名稱：" + names[index]);
        System.out.println("補貨數量：" + quantity);
        System.out.println("目前庫存：" + stocks[index]);

        return true;
    }

    public static void displayLowStock(
        String[] names,
        int[] prices,
        int[] stocks
    ) {
        boolean found = false;

        System.out.println("\n===== 低庫存商品 =====");
        System.out.printf(
            "%-8s %-15s %-10s %-10s%n",
            "編號", "商品名稱", "價格", "庫存"
        );

        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf(
                    "%-8d %-15s %-10d %-10d%n",
                    i + 1, names[i], prices[i], stocks[i]
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static int calculateTotalStockValue(
        int[] prices,
        int[] stocks
    ) {
        int total = 0;

        for (int i = 0; i < prices.length; i++) {
            total += prices[i] * stocks[i];
        }

        return total;
    }

    public static void displayTotalStockValue(
        int[] prices,
        int[] stocks
    ) {
        int total = calculateTotalStockValue(prices, stocks);
        System.out.println("全部庫存總價值：" + total);
    }

    public static void displaySummary(
        int purchaseCount,
        int restockCount,
        int[] prices,
        int[] stocks
    ) {
        int totalValue = calculateTotalStockValue(prices, stocks);

        System.out.println("\n===== 操作摘要 =====");
        System.out.println("成功購買次數：" + purchaseCount);
        System.out.println("成功補貨次數：" + restockCount);
        System.out.println("目前全部庫存總價值：" + totalValue);
    }
}