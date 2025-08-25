package winnie.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskEnum taskType;

    public Task(String description, TaskEnum taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getTypeIcon() {
        return taskType.getIcon();
    }

    public TaskEnum getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }
}
