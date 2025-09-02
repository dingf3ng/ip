package winnie.ui;

import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.chatmessage.GoodByeMessage;
import winnie.chatmessage.GreetingMessage;
import winnie.chatmessage.TaskListMessage;
import winnie.chatmessage.TaskAddedMessage;
import winnie.chatmessage.MarkTaskMessage;
import winnie.chatmessage.Sendable;
import winnie.chatmessage.UnmarkTaskMessage;
import winnie.chatmessage.DeleteTaskMessage;
import winnie.chatmessage.ErrorMessage;
import winnie.chatmessage.FoundTasksMessage;

public class Gui implements Ui {

    @Override
    public void showTaskList(TaskList tasks) {
        TaskListMessage message = new TaskListMessage(tasks);

    }

    @Override
    public void showTaskAdded(Task task, int taskCount) {
        TaskAddedMessage message = new TaskAddedMessage(task, taskCount);
    }

    @Override
    public void showTaskMarked(Task task) {
        MarkTaskMessage message = new MarkTaskMessage(task);
        lastResponse = message.getMessageContent();
    }

    @Override
    public void showTaskUnmarked(Task task) {
        UnmarkTaskMessage message = new UnmarkTaskMessage(task);
        lastResponse = message.getMessageContent();
    }

    @Override
    public void showTaskDeleted(Task task, int taskCount) {
        DeleteTaskMessage message = new DeleteTaskMessage(task, taskCount);
        lastResponse = message.getMessageContent();
    }

    @Override
    public void showError(String errorMessage) {
        ErrorMessage message = new ErrorMessage(errorMessage);
        lastResponse = message.getMessageContent();
    }

    @Override
    public void showFoundTasks(TaskList foundTasks) {
        FoundTasksMessage message = new FoundTasksMessage(foundTasks);
    }

    @Override
    public void showGoodbye() {
        GoodByeMessage message = new GoodByeMessage();
        lastResponse = message.getMessageContent();
    }

    @Override
    public String readCommand() {
        return "";
    }

    public String getLastResponse() {
        return lastResponse;
    }

    @Override
    public void showWelcome() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showWelcome'");
    }

    @Override
    public void showLoadingError() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showLoadingError'");
    }

    @Override
    public void sendMessage(Sendable message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }
}
