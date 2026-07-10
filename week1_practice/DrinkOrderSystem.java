import java.util.Scanner;

public class DrinkOrderSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;

        int totalItems = 0;
        int totalAmount = 0;

        while (true) {
            printMenu();

            int option = readMenuOption(sc);

            if (option == 0) {
                break;
            }

            int price = getPrice(option);
            String itemName = getItemName(option);
            int quantity = readValidQuantity(sc);
            int subtotal = calculateSubtotal(price, quantity);

            totalItems += quantity;
            totalAmount += subtotal;

            switch (option) {
                case 1:
                    blackTeaCount += quantity;
                    break;
                case 2:
                    greenTeaCount += quantity;
                    break;
                case 3:
                    milkTeaCount += quantity;
                    break;
                case 4:
                    coffeeCount += quantity;
                    break;
            }

            System.out.println(itemName + " x " + quantity);
            System.out.println("Subtotal: " + subtotal);
            System.out.println();
        }

        int finalAmount = calculateDiscountedTotal(totalAmount);

        printReceipt(
                blackTeaCount,
                greenTeaCount,
                milkTeaCount,
                coffeeCount,
                totalItems,
                totalAmount,
                finalAmount);

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
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

    public static int getPrice(int option) {
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 45;
            case 4:
                return 50;
            default:
                return 0;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1:
                return "Black tea";
            case 2:
                return "Green tea";
            case 3:
                return "Milk tea";
            case 4:
                return "Coffee";
            default:
                return "Unknown item";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        while (true) {
            System.out.print("請輸入數量：");
            String input = sc.nextLine().trim();

            try {
                int quantity = Integer.parseInt(input);

                if (quantity > 0) {
                    return quantity;
                }

                System.out.println("輸入錯誤：數量必須大於 0。");
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤：請輸入整數數量。");
            }
        }
    }


    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return totalAmount * 9 / 10;
        }

        return totalAmount;
    }

    public static void printReceipt(
            int blackTeaCount,
            int greenTeaCount,
            int milkTeaCount,
            int coffeeCount,
            int totalItems,
            int totalAmount,
            int finalAmount) {

        System.out.println();
        System.out.println("=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);

        if (totalAmount >= 300) {
            System.out.println("Discount: Yes");
        } else {
            System.out.println("Discount: No");
        }

        System.out.println("Final amount: " + finalAmount);
    }
}