public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("===== 初始工作串列 =====");
        taskList.printAllTasks();
        taskList.printStatistics();

        System.out.println("\n===== 新增一般工作 =====");
        taskList.addNormalTask("T001", "完成 Java 作業");
        taskList.addNormalTask("T002", "整理課堂筆記");
        taskList.addNormalTask("T003", "上傳作業到 GitHub");
        taskList.printAllTasks();

        System.out.println("\n===== 新增緊急工作 =====");
        taskList.addUrgentTask("T004", "準備明天的考試");
        taskList.addUrgentTask("T005", "繳交今天到期的報告");
        taskList.printAllTasks();

        System.out.println("\n===== 測試重複工作代碼 =====");
        taskList.addNormalTask("T002", "重複的工作");
        taskList.printAllTasks();

        System.out.println("\n===== 搜尋工作 =====");
        TaskNode result = taskList.searchTask("T003");

        if (result != null) {
            System.out.println("找到工作：" + result);
        } else {
            System.out.println("找不到工作。");
        }

        result = taskList.searchTask("T999");

        if (result != null) {
            System.out.println("找到工作：" + result);
        } else {
            System.out.println("找不到工作代碼：T999");
        }

        System.out.println("\n===== 完成工作 =====");
        taskList.completeTask("T001");
        taskList.completeTask("T004");
        taskList.completeTask("T999");
        taskList.printAllTasks();

        System.out.println("\n===== 列出未完成工作 =====");
        taskList.printIncompleteTasks();

        System.out.println("\n===== 工作統計 =====");
        taskList.printStatistics();

        System.out.println("\n===== 刪除第一個工作 =====");
        taskList.removeTask("T005");
        taskList.printAllTasks();
        taskList.printStatistics();

        System.out.println("\n===== 刪除中間工作 =====");
        taskList.removeTask("T002");
        taskList.printAllTasks();
        taskList.printStatistics();

        System.out.println("\n===== 刪除最後工作 =====");
        taskList.removeTask("T003");
        taskList.printAllTasks();
        taskList.printStatistics();

        System.out.println("\n===== 刪除不存在的工作 =====");
        taskList.removeTask("T999");
        taskList.printAllTasks();

        System.out.println("\n===== 最後結果 =====");
        taskList.printIncompleteTasks();
        taskList.printStatistics();
    }
}