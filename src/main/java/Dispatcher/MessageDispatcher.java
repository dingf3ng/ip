package Dispatcher;

import ChatMessage.Sendable;
import ChatMessage.GoodByeMessage;
import ChatMessage.GreetingMessage;
import ChatMessage.AddedTaskMessage;
import ChatMessage.TaskListMessage;
import ChatMessage.MarkTaskMessage;
import ChatMessage.UnmarkTaskMessage;
import ChatMessage.ToSendMessage;
import CliTool.*;
import TaskManager.TaskManager;
import Task.Task;

public class MessageDispatcher {
    private TaskManager taskManager;

    public MessageDispatcher() {
        this.taskManager = new TaskManager();
    }

    public void sendOut(Sendable message) {
        CliWriter cliWriter = new CliWriter();
        cliWriter.write(message);
    }

    private void printSplitLine() {
        System.out.println("\n--------------------------------------------------");
    }

    public void sayGreeting() {
        //printSplitLine();
        sendOut(new GreetingMessage());
    }

    public void sayGoodbye() {
        sendOut(new GoodByeMessage());
    }

    public void addTask(String taskName) {
        taskManager.addTask(taskName);
        Task[] tasks = taskManager.getTasks();
        Task addedTask = tasks[tasks.length - 1];
        sendOut(new AddedTaskMessage(addedTask));
    }

    public void listTasks() {
        Task[] tasks = taskManager.getTasks();
        sendOut(new TaskListMessage(tasks));
    }

    public void markTask(int taskNumber) {
        Task markedTask = taskManager.markTask(taskNumber - 1);
        if (markedTask != null) {
            sendOut(new MarkTaskMessage(markedTask));
        } else {
            sendOut(new ToSendMessage("Invalid task number."));
        }
    }

    public void unmarkTask(int taskNumber) {
        Task unmarkedTask = taskManager.unmarkTask(taskNumber - 1);
        if (unmarkedTask != null) {
            sendOut(new UnmarkTaskMessage(unmarkedTask));
        } else {
            sendOut(new ToSendMessage("Invalid task number."));
        }
    }

}
