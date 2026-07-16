import java.util.Scanner;

public class ProductDataManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        String[] productNames = new String[records.length + 1];
        int[] prices = new int[records.length + 1];
        int[] stocks = new int[records.length + 1];

        int productCount = parseRecords(records, productNames, prices, stocks);

        showProductTable(productNames, prices, stocks, productCount);

        int option;

        do {
            showMenu();
            option = inputInteger(scanner, "請輸入選項：");

            switch (option) {
                case 1:
                    showProductTable(productNames, prices, stocks, productCount);
                    break;

                case 2:
                    searchProduct(scanner, productNames, prices, stocks, productCount);
                    break;

                case 3:
                    showLowStockProducts(productNames, prices, stocks, productCount);
                    break;

                case 4:
                    showTotalStockValue(prices, stocks, productCount);
                    break;

                case 5:
                    if (productCount >= productNames.length) {
                        System.out.println("商品資料已滿，無法新增。");
                    } else {
                        productCount = addProduct(
                            scanner,
                            productNames,
                            prices,
                            stocks,
                            productCount
                        );
                    }
                    break;

                case 6:
                    System.out.println("系統已結束。");
                    break;

                default:
                    System.out.println("選項錯誤，請輸入 1 到 6。");
            }

            System.out.println();

        } while (option != 6);

        scanner.close();
    }

    public static int parseRecords(
        String[] records,
        String[] productNames,
        int[] prices,
        int[] stocks
    ) {
        int count = 0;

        for (String record : records) {
            String[] data = record.split(",");

            if (data.length != 3) {
                System.out.println("資料格式錯誤：" + record);
                continue;
            }

            try {
                productNames[count] = data[0].trim();
                prices[count] = Integer.parseInt(data[1].trim());
                stocks[count] = Integer.parseInt(data[2].trim());
                count++;
            } catch (NumberFormatException e) {
                System.out.println("價格或庫存格式錯誤：" + record);
            }
        }

        return count;
    }

    public static void showMenu() {
        System.out.println("===== 商品文字資料管理器 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 搜尋商品");
        System.out.println("3. 顯示低庫存商品");
        System.out.println("4. 顯示庫存總價值");
        System.out.println("5. 新增商品");
        System.out.println("6. 結束");
    }

    public static void showProductTable(
        String[] productNames,
        int[] prices,
        int[] stocks,
        int productCount
    ) {
        System.out.println("\n===== 商品資料表 =====");
        System.out.printf("%-5s %-20s %-10s %-10s%n",
            "編號", "商品名稱", "價格", "庫存");
        System.out.println("-----------------------------------------------");

        for (int i = 0; i < productCount; i++) {
            System.out.printf(
                "%-5d %-20s %-10d %-10d%n",
                i + 1,
                productNames[i],
                prices[i],
                stocks[i]
            );
        }
    }

    public static void searchProduct(
        Scanner scanner,
        String[] productNames,
        int[] prices,
        int[] stocks,
        int productCount
    ) {
        String keyword = inputNonEmptyString(
            scanner,
            "請輸入完整或部分商品名稱："
        ).trim().toLowerCase();

        boolean found = false;

        System.out.println("\n===== 搜尋結果 =====");

        for (int i = 0; i < productCount; i++) {
            if (productNames[i].toLowerCase().contains(keyword)) {
                System.out.println("商品名稱：" + productNames[i]);
                System.out.println("價格：" + prices[i]);
                System.out.println("庫存：" + stocks[i]);
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到符合的商品。");
        }
    }

    public static void showLowStockProducts(
        String[] productNames,
        int[] prices,
        int[] stocks,
        int productCount
    ) {
        boolean found = false;

        System.out.println("\n===== 低庫存商品 =====");

        for (int i = 0; i < productCount; i++) {
            if (stocks[i] < 10) {
                System.out.println(
                    productNames[i]
                    + "，價格：" + prices[i]
                    + "，庫存：" + stocks[i]
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static void showTotalStockValue(
        int[] prices,
        int[] stocks,
        int productCount
    ) {
        long totalValue = 0;

        for (int i = 0; i < productCount; i++) {
            totalValue += (long) prices[i] * stocks[i];
        }

        System.out.println("庫存總價值：" + totalValue + " 元");
    }

    public static int addProduct(
        Scanner scanner,
        String[] productNames,
        int[] prices,
        int[] stocks,
        int productCount
    ) {
        String newRecord = inputNonEmptyString(
            scanner,
            "請輸入新商品資料，格式為 商品名稱,價格,庫存："
        );

        String[] data = newRecord.split(",");

        if (data.length != 3) {
            System.out.println("格式錯誤，必須使用 商品名稱,價格,庫存 的格式。");
            return productCount;
        }

        String productName = data[0].trim();

        if (productName.isEmpty()) {
            System.out.println("商品名稱不可為空白。");
            return productCount;
        }

        try {
            int price = Integer.parseInt(data[1].trim());
            int stock = Integer.parseInt(data[2].trim());

            if (price < 0 || stock < 0) {
                System.out.println("價格與庫存不可為負數。");
                return productCount;
            }

            productNames[productCount] = productName;
            prices[productCount] = price;
            stocks[productCount] = stock;

            System.out.println("商品新增成功。");
            return productCount + 1;

        } catch (NumberFormatException e) {
            System.out.println("價格與庫存必須是整數。");
            return productCount;
        }
    }

    public static int inputInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入整數。");
            }
        }
    }

    public static String inputNonEmptyString(
        Scanner scanner,
        String message
    ) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (!input.trim().isEmpty()) {
                return input;
            }

            System.out.println("輸入不可為空白，請重新輸入。");
        }
    }

    /*
     * 測試案例 1：
    ===== 商品資料表 =====
編號    商品名稱                 價格         庫存        
-----------------------------------------------
1     Keyboard             890        12        
2     Mouse                490        20        
3     Monitor              5200       5         
4     USB Cable            250        30        
5     Headset              1290       8  
     *
     * 測試案例 2：
     * 請輸入完整或部分商品名稱：Keyboard 

===== 搜尋結果 =====
商品名稱：Keyboard
價格：890
庫存：12
--------------------
     *
     * 測試案例 3：
     * 請輸入選項：3

===== 低庫存商品 =====
Monitor，價格：5200，庫存：5
Headset，價格：1290，庫存：8
     *
     * 測試案例 4：
     * 庫存總價值：64300 元
     *
     * 測試案例 5：
     * 請輸入選項：5
請輸入新商品資料，格式為 商品名稱,價格,庫存：JBL,1600,25
商品新增成功。
     *
     * 測試案例 6：
     * 請輸入新商品資料，格式為 商品名稱,價格,庫存：jbl,1600  
格式錯誤，必須使用 商品名稱,價格,庫存 的格式。
     *
     * 測試案例 7：
     * 請輸入選項：6
        系統已結束。
     *
     * 測試案例 8：
     * 請輸入選項：2
請輸入完整或部分商品名稱：Headse

===== 搜尋結果 =====
商品名稱：Headset
價格：1290
庫存：8
--------------------
     */
}