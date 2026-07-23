import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DeliveryProcessingSystem {

    public static void addTask(
            Scanner scanner,
            Queue<DeliveryTask> waitingQueue,
            int taskNumber) {

        System.out.print("請輸入客戶姓名：");
        String customerName = scanner.nextLine().trim();

        if (customerName.isEmpty()) {
            System.out.println("客戶姓名不可為空，新增失敗。");
            return;
        }

        System.out.print("請輸入配送地址：");
        String address = scanner.nextLine().trim();

        if (address.isEmpty()) {
            System.out.println("配送地址不可為空，新增失敗。");
            return;
        }

        DeliveryTask task =
                new DeliveryTask(taskNumber, customerName, address);

        waitingQueue.offer(task);

        System.out.println("配送工作新增成功！");
        System.out.println(task);
        System.out.println("目前等待數：" + waitingQueue.size());
    }

    public static void completeTask(
            Queue<DeliveryTask> waitingQueue,
            Stack<DeliveryTask> completedStack) {

        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的配送工作。");
            return;
        }

        DeliveryTask completedTask = waitingQueue.poll();
        completedStack.push(completedTask);

        System.out.println("已完成以下配送工作：");
        System.out.println(completedTask);
    }

    public static void showNextTask(
            Queue<DeliveryTask> waitingQueue) {

        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有下一筆配送工作。");
            return;
        }

        System.out.println("下一筆配送工作：");
        System.out.println(waitingQueue.peek());
    }

    public static void restoreLastTask(
            Queue<DeliveryTask> waitingQueue,
            Stack<DeliveryTask> completedStack) {

        if (completedStack.isEmpty()) {
            System.out.println("目前沒有可以復原的完成紀錄。");
            return;
        }

        DeliveryTask restoredTask = completedStack.pop();

        waitingQueue.offer(restoredTask);

        System.out.println("已復原最近完成的配送工作：");
        System.out.println(restoredTask);
        System.out.println("此工作已回到等待 Queue 尾端。");
    }

    public static void showWaitingTasks(
            Queue<DeliveryTask> waitingQueue) {

        System.out.println("\n===== 等待中的配送工作 =====");

        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的配送工作。");
            return;
        }

        int order = 1;

        for (DeliveryTask task : waitingQueue) {
            System.out.println(order + ". " + task);
            order++;
        }

        System.out.println("等待數：" + waitingQueue.size());
    }

    public static void showCompletedTasks(
            Stack<DeliveryTask> completedStack) {

        System.out.println("\n===== 完成紀錄 =====");

        if (completedStack.isEmpty()) {
            System.out.println("目前沒有完成紀錄。");
            return;
        }

        for (int i = completedStack.size() - 1; i >= 0; i--) {
            System.out.println(
                    (completedStack.size() - i)
                            + ". "
                            + completedStack.get(i)
            );
        }

        System.out.println("完成數：" + completedStack.size());
    }

    public static void showAllRecords(
            Queue<DeliveryTask> waitingQueue,
            Stack<DeliveryTask> completedStack) {

        showWaitingTasks(waitingQueue);
        showCompletedTasks(completedStack);

        System.out.println("\n===== 數量統計 =====");
        System.out.println("等待數：" + waitingQueue.size());
        System.out.println("完成數：" + completedStack.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Queue<DeliveryTask> waitingQueue = new LinkedList<>();
        Stack<DeliveryTask> completedStack = new Stack<>();

        int nextTaskNumber = 1;
        boolean running = true;

        while (running) {
            System.out.println("\n===== 配送工作流程系統 =====");
            System.out.println("1. 新增配送工作");
            System.out.println("2. 完成下一筆工作");
            System.out.println("3. 查看下一筆工作");
            System.out.println("4. 復原最近完成工作");
            System.out.println("5. 查看所有處理紀錄");
            System.out.println("0. 結束程式");
            System.out.print("請輸入選項：");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    int oldSize = waitingQueue.size();

                    addTask(
                            scanner,
                            waitingQueue,
                            nextTaskNumber
                    );

                    if (waitingQueue.size() > oldSize) {
                        nextTaskNumber++;
                    }
                    break;

                case "2":
                    completeTask(
                            waitingQueue,
                            completedStack
                    );
                    break;

                case "3":
                    showNextTask(waitingQueue);
                    break;

                case "4":
                    restoreLastTask(
                            waitingQueue,
                            completedStack
                    );
                    break;

                case "5":
                    showAllRecords(
                            waitingQueue,
                            completedStack
                    );
                    break;

                case "0":
                    running = false;
                    System.out.println("配送工作流程系統已結束。");
                    break;

                default:
                    System.out.println("輸入錯誤，請輸入 0～5。");
            }
        }

        scanner.close();
    }
}