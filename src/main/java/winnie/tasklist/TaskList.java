package winnie.tasklist;

import winnie.task.Showable;
import winnie.task.SnoozedWrapper;
import winnie.task.Taskable;
import java.util.ArrayList;

/**
 * Abstraction over a list of tasks.
 */
public class TaskList {

    private ArrayList<Showable> tasks;
    private ArrayList<SnoozedWrapper> snoozedTasks;

    /**
     * Creates a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList with the given list of tasks.
     */
    public TaskList(ArrayList<Showable> tasks, ArrayList<SnoozedWrapper> snoozedTasks) {
        this.tasks = tasks;
        this.snoozedTasks = snoozedTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Taskable task) {
        if (task instanceof Showable) {
            this.tasks.add((Showable) task);
        } else {
            this.snoozedTasks.add((SnoozedWrapper) task);
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark.
     * @return The marked task, or null if the index is invalid.
     */
    public Showable markTask(int index) {
        // This assertion will hold since we check the index before calling this method.
        // Checked using "find all references" in IntelliJ, as a static analysis tool.
        assert index >= 0 && index < tasks.size() : "Task index cannot be negative";
        assert !tasks.get(index).isSnoozed() : "Snoozed cannot be marked as done";
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The index of the task to unmark.
     * @return The unmarked task, or null if the index is invalid.
     */
    public Showable unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Showable getVisibleTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public Taskable[] getAllTasks() {
        Taskable[] allTasks = new Taskable[tasks.size() + snoozedTasks.size()];
        int i = 0;
        for (Showable task : tasks) {
            allTasks[i++] = task;
        }
        for (SnoozedWrapper task : snoozedTasks) {
            allTasks[i++] = task;
        }
        return allTasks;
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to delete.
     * @return The deleted task, or null if the index is invalid.
     */
    public Showable deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        return null;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Finds tasks in the list that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing the matching tasks.
     */
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Showable task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns a TaskList containing only the active (non-snoozed) tasks.
     *
     * @return A TaskList containing active tasks.
     */
    public TaskList getActiveTasks() {
        TaskList activeTasks = new TaskList();

        for (Showable task : tasks) {
            activeTasks.addTask(task);
        }

        for (SnoozedWrapper task : snoozedTasks) {
            if (task.getSnoozeUntil().isBefore(java.time.LocalDateTime.now())) {
                // If the snooze time has passed, unsnooze the task and add it to active tasks
                activeTasks.addTask(task.unsnooze());
                tasks.add(task.unsnooze());
                snoozedTasks.remove(task);
            }
        }

        return activeTasks;
    }

    /**
     * Returns a TaskList containing only the snoozed tasks.
     *
     * @return A TaskList containing snoozed tasks.
     */
    public TaskList getSnoozedTasks() {
        return new TaskList(new ArrayList<>(), this.snoozedTasks);
    }

    /**
     * Returns a copy of the tasks in the task list. This should only be used
     * for testing purposes.
     */
    protected Showable[] getTasks() {
        return tasks.toArray(new Showable[0]);
    }
}
