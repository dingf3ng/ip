package ChatMessage;

import Task.Task;

public class UnmarkTaskMessage implements Sendable {
    private final Task task;

    public UnmarkTaskMessage(Task task) {
        this.task = task;
    }

    @Override
    public String getMessageContent() {
        return "OK, I've marked this task as not done yet:\n  " + task.toString();
    }
}