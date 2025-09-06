package winnie.task;

/**
 * Represents a task.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected TaskEnum taskType;

    /**
     * Creates a task.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task.
     */
    public Task(String description, TaskEnum taskType) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
        this.description = description;
        this.isDone = false;
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
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
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

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }
}
