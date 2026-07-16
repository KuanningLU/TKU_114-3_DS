import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] sales = new int[3][4];

        inputSales(sc, sales);

        System.out.println("\n===== 銷售矩陣報表 =====");
        displaySales(sales);

        int[] productTotals = calculateProductTotals(sales);
        int[] dayTotals = calculateDayTotals(sales);

        displayProductTotals(productTotals);
        displayDayTotals(dayTotals);

        int highestProduct = findHighestProduct(productTotals);

        System.out.println("\n總銷售量最高的商品：商品 " + (highestProduct + 1));
        System.out.println("最高總銷售量：" + productTotals[highestProduct]);

        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    System.out.print(
                        "請輸入商品 " + (i + 1)
                        + " 第 " + (j + 1)
                        + " 天的銷售量："
                    );

                    if (sc.hasNextInt()) {
                        int value = sc.nextInt();

                        if (value >= 0) {
                            sales[i][j] = value;
                            break;
                        }

                        System.out.println("輸入錯誤，銷售量不得小於 0。");
                    } else {
                        System.out.println("輸入錯誤，請輸入整數。");
                        sc.next();
                    }
                }
            }
        }
    }

    public static void displaySales(int[][] sales) {
        System.out.printf("%-10s", "");

        for (int day = 0; day < sales[0].length; day++) {
            System.out.printf("%-10s", "第" + (day + 1) + "天");
        }

        System.out.println();

        for (int i = 0; i < sales.length; i++) {
            System.out.printf("%-10s", "商品" + (i + 1));

            for (int j = 0; j < sales[i].length; j++) {
                System.out.printf("%-10d", sales[i][j]);
            }

            System.out.println();
        }
    }

    public static int[] calculateProductTotals(int[][] sales) {
        int[] totals = new int[sales.length];

        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                totals[i] += sales[i][j];
            }
        }

        return totals;
    }

    public static int[] calculateDayTotals(int[][] sales) {
        int[] totals = new int[sales[0].length];

        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                totals[j] += sales[i][j];
            }
        }

        return totals;
    }

    public static void displayProductTotals(int[] productTotals) {
        System.out.println("\n===== 各商品銷售總量 =====");

        for (int i = 0; i < productTotals.length; i++) {
            System.out.println(
                "商品 " + (i + 1)
                + " 銷售總量：" + productTotals[i]
            );
        }
    }

    public static void displayDayTotals(int[] dayTotals) {
        System.out.println("\n===== 每日銷售總量 =====");

        for (int i = 0; i < dayTotals.length; i++) {
            System.out.println(
                "第 " + (i + 1)
                + " 天銷售總量：" + dayTotals[i]
            );
        }
    }

    public static int findHighestProduct(int[] productTotals) {
        int highestIndex = 0;

        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > productTotals[highestIndex]) {
                highestIndex = i;
            }
        }

        return highestIndex;
    }
}