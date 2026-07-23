import java.util.Scanner;
import java.util.Stack;

public class BrowserUndoSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack<String> backStack = new Stack<>();
        Stack<String> forwardStack = new Stack<>();

        String currentPage = "首頁";
        int operationCount = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n===== 瀏覽器操作復原系統 =====");
            System.out.println("目前頁面：" + currentPage);
            System.out.println("1. 開啟新頁面");
            System.out.println("2. 返回上一頁");
            System.out.println("3. 前往下一頁");
            System.out.println("4. 查看操作紀錄");
            System.out.println("0. 結束程式");
            System.out.print("請輸入選項：");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.print("請輸入新頁面名稱：");
                    String newPage = scanner.nextLine().trim();

                    if (newPage.isEmpty()) {
                        System.out.println("頁面名稱不可為空。");
                        break;
                    }

                    backStack.push(currentPage);
                    currentPage = newPage;
                    forwardStack.clear();
                    operationCount++;

                    System.out.println("已開啟新頁面：" + currentPage);
                    break;

                case "2":
                    if (backStack.isEmpty()) {
                        System.out.println("無法返回，已經是最前面的頁面。");
                    } else {
                        forwardStack.push(currentPage);
                        currentPage = backStack.pop();
                        operationCount++;

                        System.out.println("已返回上一頁：" + currentPage);
                    }
                    break;

                case "3":
                    if (forwardStack.isEmpty()) {
                        System.out.println("無法前往下一頁，沒有下一頁紀錄。");
                    } else {
                        backStack.push(currentPage);
                        currentPage = forwardStack.pop();
                        operationCount++;

                        System.out.println("已前往下一頁：" + currentPage);
                    }
                    break;

                case "4":
                    System.out.println("\n===== 目前瀏覽紀錄 =====");
                    System.out.println("上一頁堆疊：" + backStack);
                    System.out.println("目前頁面：" + currentPage);
                    System.out.println("下一頁堆疊：" + forwardStack);
                    System.out.println("成功操作次數：" + operationCount);
                    break;

                case "0":
                    running = false;
                    System.out.println("程式結束，共完成 " + operationCount + " 次操作。");
                    break;

                default:
                    System.out.println("輸入錯誤，請輸入 0～4。");
            }
        }

        scanner.close();
    }
}