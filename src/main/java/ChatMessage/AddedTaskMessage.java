package ChatMessage;

import Task.Task;

public class AddedTaskMessage implements Sendable {
    private final Task task;

    public AddedTaskMessage(Task task) {
        this.task = task;
    }

    @Override
    public String getMessageContent() {
        return "added: " + task.getDescription();
    }
}