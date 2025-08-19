package TaskManager;

import Task.Task;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int taskCount;

    public TaskManager() {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    public void addTask(String description) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(description);
            taskCount++;
        }
    }

    public Task[] getTasks() {
        Task[] result = new Task[taskCount];
        for (int i = 0; i < taskCount; i++) {
            result[i] = tasks[i];
        }
        return result;
    }

    public int getTaskCount() {
        return taskCount;
    }
}