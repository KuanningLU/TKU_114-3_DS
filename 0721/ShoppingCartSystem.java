import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();

        int choice;

        do {
            showMenu();
            choice = readInteger(scanner, "請輸入選項：");

            switch (choice) {
                case 1:
                    addItem(scanner, cart);
                    break;

                case 2:
                    updateQuantity(scanner, cart);
                    break;

                case 3:
                    removeItem(scanner, cart);
                    break;

                case 4:
                    showCart(cart);
                    break;

                case 5:
                    showTotal(cart);
                    break;

                case 0:
                    System.out.println("購物車系統已結束。");
                    break;

                default:
                    System.out.println("輸入錯誤，請輸入 0～5。");
            }

        } while (choice != 0);

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("\n===== 購物車系統 =====");
        System.out.println("1. 新增商品");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 顯示購物車內容");
        System.out.println("5. 計算購物車總額");
        System.out.println("0. 結束程式");
    }

    public static void addItem(
            Scanner scanner,
            ArrayList<CartItem> cart) {

        System.out.print("請輸入商品代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗，商品代碼不得為空白。");
            return;
        }

        CartItem existingItem = findItemByCode(cart, code);

        if (existingItem != null) {
            int quantity = readInteger(scanner, "商品已存在，請輸入增加數量：");

            if (quantity <= 0) {
                System.out.println("新增失敗，數量必須大於 0。");
                return;
            }

            existingItem.addQuantity(quantity);
            System.out.println("商品數量增加成功。");
            System.out.println(existingItem);
            return;
        }

        System.out.print("請輸入商品名稱：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("新增失敗，商品名稱不得為空白。");
            return;
        }

        double unitPrice = readDouble(scanner, "請輸入商品單價：");

        if (unitPrice < 0) {
            System.out.println("新增失敗，商品單價不得小於 0。");
            return;
        }

        int quantity = readInteger(scanner, "請輸入商品數量：");

        if (quantity <= 0) {
            System.out.println("新增失敗，數量必須大於 0。");
            return;
        }

        CartItem item = new CartItem(code, name, unitPrice, quantity);
        cart.add(item);

        System.out.println("商品新增成功。");
        System.out.println(item);
    }

    public static void updateQuantity(
            Scanner scanner,
            ArrayList<CartItem> cart) {

        System.out.print("請輸入要修改的商品代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("修改失敗，商品代碼不得為空白。");
            return;
        }

        CartItem item = findItemByCode(cart, code);

        if (item == null) {
            System.out.println("修改失敗，找不到商品代碼：" + code);
            return;
        }

        System.out.println("目前數量：" + item.getQuantity());
        int newQuantity = readInteger(scanner, "請輸入新的數量：");

        if (newQuantity <= 0) {
            System.out.println("修改失敗，數量必須大於 0。");
            return;
        }

        item.setQuantity(newQuantity);
        System.out.println("商品數量修改成功。");
        System.out.println(item);
    }

    public static void removeItem(
            Scanner scanner,
            ArrayList<CartItem> cart) {

        System.out.print("請輸入要移除的商品代碼：");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("移除失敗，商品代碼不得為空白。");
            return;
        }

        CartItem item = findItemByCode(cart, code);

        if (item == null) {
            System.out.println("移除失敗，找不到商品代碼：" + code);
            return;
        }

        cart.remove(item);
        System.out.println("商品移除成功：" + item.getName());
    }

    public static void showCart(ArrayList<CartItem> cart) {
        System.out.println("\n===== 購物車內容 =====");

        if (cart.isEmpty()) {
            System.out.println("購物車目前沒有商品。");
            return;
        }

        for (int i = 0; i < cart.size(); i++) {
            System.out.println((i + 1) + ". " + cart.get(i));
        }

        System.out.printf("購物車總額：%.2f%n", calculateTotal(cart));
    }

    public static void showTotal(ArrayList<CartItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("購物車目前沒有商品，總額為 0 元。");
            return;
        }

        System.out.printf("購物車總額：%.2f 元%n", calculateTotal(cart));
    }

    public static double calculateTotal(ArrayList<CartItem> cart) {
        double total = 0;

        for (CartItem item : cart) {
            total += item.calculateSubtotal();
        }

        return total;
    }

    public static CartItem findItemByCode(
            ArrayList<CartItem> cart,
            String targetCode) {

        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(targetCode)) {
                return item;
            }
        }

        return null;
    }

    public static int readInteger(
            Scanner scanner,
            String message) {

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

    public static double readDouble(
            Scanner scanner,
            String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤，請輸入有效數字。");
            }
        }
    }
}