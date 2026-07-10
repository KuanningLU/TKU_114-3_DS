import java.util.Scanner;

public class PatternGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int option = readMenuOption(sc);

            switch (option) {
                case 1:
                    System.out.println();
                    System.out.println("=== 九九乘法表 ===");
                    printMultiplicationTable();
                    break;

                case 2:
                    int height = readPositiveInt(
                            sc, "請輸入正三角形高度：");

                    System.out.println();
                    System.out.println("=== 正三角形星號 ===");
                    printTriangle(height);
                    break;

                case 3:
                    int reverseHeight = readPositiveInt(
                            sc, "請輸入倒三角形高度：");

                    System.out.println();
                    System.out.println("=== 倒三角形星號 ===");
                    printReverseTriangle(reverseHeight);
                    break;

                case 4:
                    int rows = readPositiveInt(
                            sc, "請輸入數字方格列數：");

                    int cols = readPositiveInt(
                            sc, "請輸入數字方格欄數：");

                    System.out.println();
                    System.out.println("=== 數字方格 ===");
                    printNumberGrid(rows, cols);
                    break;

                case 0:
                    System.out.println("程式結束。");
                    running = false;
                    break;

                default:
                   
                    break;
            }

            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Pattern Generator Menu ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
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

    public static int readPositiveInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("輸入錯誤：數值必須大於 0。");
            } catch (NumberFormatException e) {
                System.out.println("輸入錯誤：請輸入正確的整數。");
            }
        }
    }

    public static void printMultiplicationTable() {
        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= 9; col++) {
                System.out.printf(
                        "%d x %d = %2d  ",
                        row,
                        col,
                        row * col);
            }

            System.out.println();
        }
    }

    public static void printTriangle(int height) {
        for (int row = 1; row <= height; row++) {
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public static void printReverseTriangle(int height) {
        for (int row = height; row >= 1; row--) {
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                System.out.print(col);

                if (col < cols) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }
}