package winnie.chatmessage;

import winnie.task.Task;

public class TaskAddedMessage implements Sendable {
    private final Task task;
    private final int taskCount;

    public TaskAddedMessage(Task task, int taskCount) {
        this.task = task;
        this.taskCount = taskCount;
    }

    @Override
    public String getMessageContent() {
        return "Got it. I've added this task:\n     " + task.toString() + 
               "\n     Now you have " + taskCount + " tasks in the list.";
    }
}