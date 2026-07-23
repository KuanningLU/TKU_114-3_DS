import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class ClinicQueueSystem {

    public static void registerPatient(
            Scanner scanner,
            Queue<Patient> waitingQueue,
            Set<String> usedNumbers) {

        System.out.print("請輸入掛號號碼：");
        String number = scanner.nextLine().trim();

        if (number.isEmpty()) {
            System.out.println("掛號號碼不可為空。");
            return;
        }

        if (usedNumbers.contains(number)) {
            System.out.println("掛號失敗：此號碼已經使用過，不可重複。");
            return;
        }

        System.out.print("請輸入病患姓名：");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("病患姓名不可為空。");
            return;
        }

        System.out.print("請輸入科別：");
        String department = scanner.nextLine().trim();

        if (department.isEmpty()) {
            System.out.println("科別不可為空。");
            return;
        }

        Patient patient = new Patient(number, name, department);

        waitingQueue.offer(patient);
        usedNumbers.add(number);

        System.out.println("掛號成功！");
        System.out.println(patient);
        System.out.println("目前總等待人數：" + waitingQueue.size() + " 人");
    }

    public static int callNextPatient(
            Queue<Patient> waitingQueue,
            int totalServed) {

        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有病患等待，無法叫號。");
            return totalServed;
        }

        Patient patient = waitingQueue.poll();
        totalServed++;

        System.out.println("請以下病患前往診間：");
        System.out.println(patient);
        System.out.println("目前剩餘等待人數："
                + waitingQueue.size() + " 人");

        return totalServed;
    }

    public static void showNextPatient(
            Queue<Patient> waitingQueue) {

        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有病患等待。");
            return;
        }

        System.out.println("下一位病患：");
        System.out.println(waitingQueue.peek());
    }

    public static void showWaitingList(
            Queue<Patient> waitingQueue) {

        if (waitingQueue.isEmpty()) {
            System.out.println("目前等待清單為空。");
            return;
        }

        System.out.println("\n===== 病患等待清單 =====");

        int order = 1;

        for (Patient patient : waitingQueue) {
            System.out.println(order + ". " + patient);
            order++;
        }
    }

    public static void showStatistics(
            Queue<Patient> waitingQueue,
            int totalServed) {

        Map<String, Integer> departmentCounts = new HashMap<>();

        for (Patient patient : waitingQueue) {
            String department = patient.getDepartment();

            departmentCounts.put(
                    department,
                    departmentCounts.getOrDefault(department, 0) + 1
            );
        }

        System.out.println("\n===== 診所統計資料 =====");

        if (departmentCounts.isEmpty()) {
            System.out.println("目前各科別皆無病患等待。");
        } else {
            System.out.println("各科別等待人數：");

            for (Map.Entry<String, Integer> entry
                    : departmentCounts.entrySet()) {

                System.out.println(entry.getKey()
                        + "：" + entry.getValue() + " 人");
            }
        }

        System.out.println("總等待人數：" + waitingQueue.size() + " 人");
        System.out.println("總服務人數：" + totalServed + " 人");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Queue<Patient> waitingQueue = new LinkedList<>();
        Set<String> usedNumbers = new HashSet<>();

        int totalServed = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n===== 診所叫號系統 =====");
            System.out.println("1. 病患掛號");
            System.out.println("2. 叫號");
            System.out.println("3. 查看下一位病患");
            System.out.println("4. 查看等待清單");
            System.out.println("5. 查看統計資料");
            System.out.println("0. 結束系統");
            System.out.print("請輸入選項：");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    registerPatient(
                            scanner,
                            waitingQueue,
                            usedNumbers
                    );
                    break;

                case "2":
                    totalServed = callNextPatient(
                            waitingQueue,
                            totalServed
                    );
                    break;

                case "3":
                    showNextPatient(waitingQueue);
                    break;

                case "4":
                    showWaitingList(waitingQueue);
                    break;

                case "5":
                    showStatistics(
                            waitingQueue,
                            totalServed
                    );
                    break;

                case "0":
                    running = false;
                    System.out.println("診所叫號系統已結束。");
                    break;

                default:
                    System.out.println("輸入錯誤，請輸入 0～5。");
            }
        }

        scanner.close();
    }
}