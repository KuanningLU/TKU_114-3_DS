public class TaskNode {
    private String taskCode;
    private String description;
    private boolean completed;
    TaskNode next;

    public TaskNode(String taskCode, String description) {
        this.taskCode = taskCode;
        this.description = description;
        this.completed = false;
        this.next = null;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void completeTask() {
        completed = true;
    }

    public String getStatus() {
        return completed ? "已完成" : "未完成";
    }

    @Override
    public String toString() {
        return taskCode + "｜" + description + "｜" + getStatus();
    }
}