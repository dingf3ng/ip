package winnie.task;

import java.time.LocalDateTime;

/**
 * An interface representing a listable task
 * that can be shown when printing the task list.
 */
public interface Showable extends Taskable {

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone();

    /**
     * Marks the task as done.
     */
    public Showable markAsDone();

    /**
     * Marks the task as not done.
     */
    public Showable markAsNotDone();

    /**
     * Snoozes the task until the specified date and time.
     *
     * @param snoozeUntil The date and time to snooze the task until.
     * @return The snoozed task.
     */
    public SnoozedWrapper snooze(LocalDateTime snoozeUntil);

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon();

    /**
     * Gets the type icon of the task.
     *
     * @return The type icon of the task.
     */
    public String getTypeIcon();
}
