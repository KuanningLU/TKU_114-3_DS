public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        head = null;
        size = 0;
    }

    public boolean contains(String taskCode) {
        return searchTask(taskCode) != null;
    }

    public TaskNode searchTask(String taskCode) {
        TaskNode current = head;

        while (current != null) {
            if (current.getTaskCode().equals(taskCode)) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    public boolean addUrgentTask(String taskCode, String description) {
        if (contains(taskCode)) {
            System.out.println("新增失敗，工作代碼不可重複：" + taskCode);
            return false;
        }

        TaskNode newNode = new TaskNode(taskCode, description);

        newNode.next = head;
        head = newNode;
        size++;

        System.out.println("已新增緊急工作：" + newNode);
        return true;
    }

    public boolean addNormalTask(String taskCode, String description) {
        if (contains(taskCode)) {
            System.out.println("新增失敗，工作代碼不可重複：" + taskCode);
            return false;
        }

        TaskNode newNode = new TaskNode(taskCode, description);

        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;

        System.out.println("已新增一般工作：" + newNode);
        return true;
    }

    public boolean completeTask(String taskCode) {
        TaskNode task = searchTask(taskCode);

        if (task == null) {
            System.out.println("找不到工作代碼：" + taskCode);
            return false;
        }

        if (task.isCompleted()) {
            System.out.println("此工作已經完成：" + taskCode);
            return false;
        }

        task.completeTask();
        System.out.println("工作已完成：" + task);
        return true;
    }

    public boolean removeTask(String taskCode) {
        if (head == null) {
            System.out.println("工作串列為空，無法刪除。");
            return false;
        }

        if (head.getTaskCode().equals(taskCode)) {
            TaskNode deletedNode = head;
            head = head.next;
            deletedNode.next = null;
            size--;

            System.out.println("已刪除工作：" + deletedNode);
            return true;
        }

        TaskNode current = head;

        while (current.next != null) {
            if (current.next.getTaskCode().equals(taskCode)) {
                TaskNode deletedNode = current.next;
                current.next = deletedNode.next;
                deletedNode.next = null;
                size--;

                System.out.println("已刪除工作：" + deletedNode);
                return true;
            }

            current = current.next;
        }

        System.out.println("找不到工作代碼：" + taskCode);
        return false;
    }

    public void printAllTasks() {
        System.out.println("目前所有工作：");

        if (head == null) {
            System.out.println("工作串列為空。");
            return;
        }

        TaskNode current = head;
        int order = 1;

        while (current != null) {
            System.out.println(order + ". " + current);
            current = current.next;
            order++;
        }
    }

    public void printIncompleteTasks() {
        System.out.println("未完成工作：");

        if (head == null) {
            System.out.println("工作串列為空。");
            return;
        }

        TaskNode current = head;
        int order = 1;

        while (current != null) {
            if (!current.isCompleted()) {
                System.out.println(order + ". " + current);
                order++;
            }

            current = current.next;
        }

        if (order == 1) {
            System.out.println("目前沒有未完成工作。");
        }
    }

    public int getSize() {
        return size;
    }

    public int getIncompleteCount() {
        int count = 0;
        TaskNode current = head;

        while (current != null) {
            if (!current.isCompleted()) {
                count++;
            }

            current = current.next;
        }

        return count;
    }

    public void printStatistics() {
        System.out.println("工作總數：" + getSize());
        System.out.println("未完成數量：" + getIncompleteCount());
    }
}