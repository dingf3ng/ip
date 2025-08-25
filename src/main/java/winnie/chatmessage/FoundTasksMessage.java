package winnie.chatmessage;

import winnie.task.Task;
import winnie.tasklist.TaskList;

/**
 * Message to display found tasks matching a search keyword.
 */
public class FoundTasksMessage implements Sendable {
    private TaskList foundTasks;

    public FoundTasksMessage(TaskList foundTasks) {
        this.foundTasks = foundTasks;
    }

    @Override
    public String getMessageContent() {
        if (foundTasks.getTaskCount() == 0) {
            return "No matching tasks found.";
        }

        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
        Task[] tasks = foundTasks.getTasks();
        
        for (int i = 0; i < tasks.length; i++) {
            message.append(String.format(" %d.%s\n", i + 1, tasks[i].toString()));
        }
        
        return message.toString().trim();
    }
}