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

    public void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
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

    public Task markTask(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsDone();
            return tasks[index];
        }
        return null;
    }

    public Task unmarkTask(int index) {
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsNotDone();
            return tasks[index];
        }
        return null;
    }

    public Task getTask(int index) {
        if (index >= 0 && index < taskCount) {
            return tasks[index];
        }
        return null;
    }
}