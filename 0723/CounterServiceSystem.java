import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CounterServiceSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Queue<String> waitingQueue = new LinkedList<>();
        Queue<String> serviceHistory = new LinkedList<>();

        int nextNumber = 1;
        boolean running = true;

        while (running) {
            System.out.println("\n===== 櫃台叫號系統 =====");
            System.out.println("1. 取號");
            System.out.println("2. 叫號");
            System.out.println("3. 查看下一位");
            System.out.println("4. 查看等待人數");
            System.out.println("5. 查看處理紀錄");
            System.out.println("0. 結束程式");
            System.out.print("請輸入選項：");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    takeNumber(scanner, waitingQueue, nextNumber);
                    nextNumber++;
                    break;

                case "2":
                    callNext(waitingQueue, serviceHistory);
                    break;

                case "3":
                    showNext(waitingQueue);
                    break;

                case "4":
                    System.out.println("目前等待人數："
                            + waitingQueue.size() + " 人");
                    break;

                case "5":
                    showHistory(serviceHistory);
                    break;

                case "0":
                    running = false;
                    System.out.println("系統已結束。");
                    break;

                default:
                    System.out.println("輸入錯誤，請輸入 0～5。");
            }
        }

        scanner.close();
    }

    public static void takeNumber(
            Scanner scanner,
            Queue<String> waitingQueue,
            int number) {

        System.out.print("請輸入姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("姓名不可為空，取號失敗。");
            return;
        }

        String customerInfo = "號碼 "
                + number + "，姓名：" + name;

        waitingQueue.offer(customerInfo);

        System.out.println("取號成功！");
        System.out.println(customerInfo);
        System.out.println("前方等待人數："
                + (waitingQueue.size() - 1) + " 人");
    }

    public static void callNext(
            Queue<String> waitingQueue,
            Queue<String> serviceHistory) {

        if (waitingQueue.isEmpty()) {
            System.out.println("目前無人等待，無法叫號。");
            return;
        }

        String customer = waitingQueue.poll();
        serviceHistory.offer(customer);

        System.out.println("請 " + customer + " 至櫃台辦理。");
        System.out.println("剩餘等待人數："
                + waitingQueue.size() + " 人");
    }

    public static void showNext(Queue<String> waitingQueue) {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前無人等待。");
            return;
        }

        System.out.println("下一位為："
                + waitingQueue.peek());
    }

    public static void showHistory(
            Queue<String> serviceHistory) {

        if (serviceHistory.isEmpty()) {
            System.out.println("目前尚無處理紀錄。");
            return;
        }

        System.out.println("===== 處理紀錄 =====");

        int count = 1;

        for (String customer : serviceHistory) {
            System.out.println(count + ". " + customer);
            count++;
        }
    }
}