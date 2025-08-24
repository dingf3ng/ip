package winnie.chatmessage;

import winnie.task.Task;

public class DeleteTaskMessage implements Sendable {
    private final Task task;
    private final int taskCount;

    public DeleteTaskMessage(Task task, int taskCount) {
        this.task = task;
        this.taskCount = taskCount;
    }

    @Override
    public String getMessageContent() {
        return "Noted. I've removed this task:\n     " + task.toString() + 
               "\n     Now you have " + taskCount + " tasks in the list.";
    }
}