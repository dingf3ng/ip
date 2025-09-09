package winnie.task;

import java.time.LocalDateTime;

public class DoneWrapper implements Showable {

    private Showable originalTask;

    public DoneWrapper(Showable originalTask) {
        this.originalTask = originalTask;
        this.originalTask.markAsDone();
    }

    @Override
    public String getDescription() {
        return originalTask.getDescription();
    }

    @Override
    public boolean isDone() {
        return true; // Always done
    }

    @Override
    public Showable markAsDone() {
        return this; // Identity, as task is already done
    }

    @Override
    public Showable markAsNotDone() {
        return originalTask;
    }

    @Override
    public String getStatusIcon() {
        return "[X]";
    }

    @Override
    public String getTypeIcon() {
        return originalTask.getTypeIcon();
    }

    @Override
    public TaskEnum getTaskType() {
        return originalTask.getTaskType();
    }

    @Override
    public SnoozedWrapper snooze(LocalDateTime snoozeUntil) {
        return new SnoozedWrapper(this, snoozeUntil);
    }

    @Override
    public boolean isSnoozed() {
        return false; // Done tasks are not snoozed
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + getDescription();
    }

}
