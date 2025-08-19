package ChatMessage;

public class AddedTaskMessage implements Sendable {
    private final String taskName;

    public AddedTaskMessage(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String getMessageContent() {
        return "added: " + taskName;
    }
}