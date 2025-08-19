package ChatMessage;

public class TaskListMessage implements Sendable {
    private final String[] tasks;

    public TaskListMessage(String[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public String getMessageContent() {
        if (tasks.length == 0) {
            return "No tasks found.";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            sb.append((i + 1)).append(". ").append(tasks[i]);
            if (i < tasks.length - 1) {
                sb.append("\n     ");
            }
        }
        return sb.toString();
    }
}