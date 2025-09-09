package winnie.task;

import java.time.LocalDateTime;

/**
 * Represents a task.
 */
public abstract class Task implements Showable {

    private String description;
    private TaskEnum taskType;

    /**
     * Creates a task.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task.
     */
    public Task(String description, TaskEnum taskType) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return false;
    }

    /**
     * Marks the task as done.
     */
    public DoneWrapper markAsDone() {
        return new DoneWrapper(this);
    }

    /**
     * Marks the task as not done.
     */
    public Task markAsNotDone() {
        return this; // Identity, as task is not done
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return "[ ]";
    }

    /**
     * Gets the type icon of the task.
     *
     * @return The type icon of the task.
     */
    public String getTypeIcon() {
        return taskType.getIcon();
    }

    /**
     * Gets the task type of the task.
     *
     * @return The task type of the task.
     */
    public TaskEnum getTaskType() {
        return taskType;
    }

    /**
     * Snoozes the task until the specified date and time.
     *
     * @param snoozeUntil The date and time until when to snooze the task.
     */
    @Override
    public SnoozedWrapper snooze(LocalDateTime snoozeUntil) {
        assert snoozeUntil != null : "Snooze time cannot be null";
        return new SnoozedWrapper(this, snoozeUntil);
    }

    @Override
    public boolean isSnoozed() {
        return false; // Tasks are not snoozed by default
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + getDescription();
    }
}
