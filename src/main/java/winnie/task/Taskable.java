package winnie.task;

/**
 * An interface representing a task with basic functionalities.
 */
public interface Taskable {

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription();

    /**
     * Gets the task type of the task.
     *
     * @return The task type of the task.
     */
    public TaskEnum getTaskType();

    /**
     * Checks if the task is currently snoozed.
     *
     * @return True if the task is snoozed, false otherwise.
     */
    public boolean isSnoozed();

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone();

}
