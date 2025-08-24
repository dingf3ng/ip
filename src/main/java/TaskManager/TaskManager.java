package taskmanager;

import storage.Storage;
import task.Task;

import java.util.ArrayList;
import java.io.IOException;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskManager() {
        this.tasks = new ArrayList<Task>();
        this.storage = new Storage("./data/duke.txt");
        loadTasks();
    }

    private void saveTasks() {
        try {
            storage.saveTasks(tasks.toArray(new Task[0]));
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try {
            ArrayList<Task> loadedTasks = storage.loadTasks();
            this.tasks = loadedTasks;
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            this.tasks = new ArrayList<Task>();
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            saveTasks();
            return tasks.get(index);
        }
        return null;
    }

    public Task unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            saveTasks();
            return tasks.get(index);
        }
        return null;
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task deletedTask = tasks.remove(index);
            saveTasks();
            return deletedTask;
        }
        return null;
    }
}