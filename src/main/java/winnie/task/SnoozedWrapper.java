package winnie.task;

import java.time.LocalDateTime;

/**
 * A mixin class that adds snooze functionality to a Listable task.
 */
public class SnoozedWrapper implements Taskable {

    private Showable originalTask;
    private LocalDateTime snoozeUntil;

    public SnoozedWrapper(Showable originalTask, LocalDateTime snoozeUntil) {
        this.originalTask = originalTask;
        this.snoozeUntil = snoozeUntil;
    }

    @Override
    public String getDescription() {
        return originalTask.getDescription();
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public TaskEnum getTaskType() {
        return originalTask.getTaskType();
    }

    public Showable unsnooze() {
        return originalTask;
    }

    @Override
    public boolean isSnoozed() {
        return snoozeUntil != null && LocalDateTime.now().isBefore(snoozeUntil);
    }

    public LocalDateTime getSnoozeUntil() {
        return snoozeUntil;
    }

    @Override
    public String toString() {
        return originalTask.toString() + " [Snoozed]";
    }

}
