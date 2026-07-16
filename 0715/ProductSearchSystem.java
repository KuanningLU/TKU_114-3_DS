import java.util.Scanner;

public class ProductSearchSystem {

    static String[] productNames = {
        "Apple iPhone",
        "Samsung Galaxy",
        "ASUS Laptop",
        "Logitech Mouse",
        "Sony Headphones",
        "Apple Watch"
    };

    static int[] prices = {
        29900,
        25900,
        32000,
        1200,
        4500,
        12900
    };

    static int[] stocks = {
        10,
        8,
        5,
        20,
        12,
        7
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            showMenu();
            option = inputOption(scanner);

            switch (option) {
                case 1:
                    showAllProducts();
                    break;

                case 2:
                    searchExactProduct(scanner);
                    break;

                case 3:
                    searchPartialProduct(scanner);
                    break;

                case 4:
                    showLongestProductName();
                    break;

                case 5:
                    showKeywordFirstPosition(scanner);
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

    public static void showMenu() {
        System.out.println("===== 商品名稱搜尋系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋");
        System.out.println("3. 部分名稱搜尋");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示關鍵字第一次出現的位置");
        System.out.println("6. 結束");
    }

    public static int inputOption(Scanner scanner) {
        while (true) {
            System.out.print("請輸入選項：");
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("請輸入有效的整數選項。");
            }
        }
    }

    public static void showAllProducts() {
        System.out.println("\n===== 全部商品 =====");

        for (int i = 0; i < productNames.length; i++) {
            showProduct(i);
        }
    }

    public static void searchExactProduct(Scanner scanner) {
        String keyword = inputNonEmpty(scanner, "請輸入完整商品名稱：").trim();
        boolean found = false;

        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i].trim().equalsIgnoreCase(keyword)) {
                showProduct(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到完整名稱為「" + keyword + "」的商品。");
        }
    }

    public static void searchPartialProduct(Scanner scanner) {
        String keyword = inputNonEmpty(scanner, "請輸入部分商品名稱：").trim();
        boolean found = false;

        System.out.println("\n===== 搜尋結果 =====");

        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i].toLowerCase().contains(keyword.toLowerCase())) {
                showProduct(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println("沒有包含「" + keyword + "」的商品。");
        }
    }

    public static void showLongestProductName() {
        int longestIndex = 0;

        for (int i = 1; i < productNames.length; i++) {
            if (productNames[i].length() > productNames[longestIndex].length()) {
                longestIndex = i;
            }
        }

        System.out.println("名稱最長的商品：");
        showProduct(longestIndex);
        System.out.println("名稱長度：" + productNames[longestIndex].length());
    }

    public static void showKeywordFirstPosition(Scanner scanner) {
        String productKeyword = inputNonEmpty(
            scanner,
            "請輸入要尋找的商品名稱或部分名稱："
        ).trim();

        int productIndex = findProductIndex(productKeyword);

        if (productIndex == -1) {
            System.out.println("找不到符合的商品。");
            return;
        }

        String keyword = inputNonEmpty(
            scanner,
            "請輸入要尋找位置的關鍵字："
        ).trim();

        int position = productNames[productIndex]
            .toLowerCase()
            .indexOf(keyword.toLowerCase());

        System.out.println("商品名稱：" + productNames[productIndex]);

        if (position == -1) {
            System.out.println("關鍵字「" + keyword + "」未出現在商品名稱中。");
        } else {
            System.out.println(
                "關鍵字「" + keyword + "」第一次出現的位置：" + position
            );
        }
    }

    public static int findProductIndex(String keyword) {
        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i].equalsIgnoreCase(keyword)) {
                return i;
            }
        }

        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i].toLowerCase().contains(keyword.toLowerCase())) {
                return i;
            }
        }

        return -1;
    }

    public static String inputNonEmpty(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (!input.trim().isEmpty()) {
                return input;
            }

            System.out.println("輸入不可為空白，請重新輸入。");
        }
    }

    public static void showProduct(int index) {
        System.out.println(
            (index + 1)
                + ". 商品名稱：" + productNames[index]
                + "｜價格：$" + prices[index]
                + "｜庫存：" + stocks[index]
        );
    }

    /*
     * 測試案例：
     *
     * 1. 選擇 1
     *    ===== 全部商品 =====
    1. 商品名稱：Apple iPhone｜價格：$29900｜庫存：10
    2. 商品名稱：Samsung Galaxy｜價格：$25900｜庫存：8
    3. 商品名稱：ASUS Laptop｜價格：$32000｜庫存：5
    4. 商品名稱：Logitech Mouse｜價格：$1200｜庫存：20
    5. 商品名稱：Sony Headphones｜價格：$4500｜庫存：12
    6. 商品名稱：Apple Watch｜價格：$12900｜庫存：7
     *
     * 2. 選擇 2
    請輸入完整商品名稱：Apple Watch
    商品名稱：Apple Watch｜價格：$12900｜庫存：7
     *
     * 3. 選擇 3
    請輸入部分商品名稱：apple
    ===== 搜尋結果 =====
    1. 商品名稱：Apple iPhone｜價格：$29900｜庫存：10
    6. 商品名稱：Apple Watch｜價格：$12900｜庫存：7
     *
     * 4. 選擇 4
    名稱最長的商品：
    5. 商品名稱：Sony Headphones｜價格：$4500｜庫存：12 
    名稱長度：15
     *
     * 5. 選擇 5
    請輸入要尋找的商品名稱或部分名稱：Sony Headphones
    請輸入要尋找位置的關鍵字：sony
    商品名稱：Sony Headphones
    關鍵字「sony」第一次出現的位置：0
     *
     * 6. 選擇 6
     * 系統已結束。
     */
}