package TaskManager;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private String[] tasks;
    private int taskCount;

    public TaskManager() {
        this.tasks = new String[MAX_TASKS];
        this.taskCount = 0;
    }

    public void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
        }
    }

    public String[] getTasks() {
        String[] result = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            result[i] = tasks[i];
        }
        return result;
    }

    public int getTaskCount() {
        return taskCount;
    }
}