package chatmessage;

import task.Task;

public class TaskListMessage implements Sendable {
    private final Task[] tasks;

    public TaskListMessage(Task[] tasks) {
        this.tasks = tasks;
    }

    @Override
    public String getMessageContent() {
        if (tasks.length == 0) {
            return "No tasks found.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            sb.append("\n     ")
                    .append((i + 1))
                        .append(".")
                            .append(tasks[i].toString());
        }
        return sb.toString();
    }
}