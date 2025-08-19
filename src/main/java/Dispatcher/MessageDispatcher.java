package Dispatcher;

import ChatMessage.Sendable;
import ChatMessage.GoodByeMessage;
import ChatMessage.GreetingMessage;
import ChatMessage.AddedTaskMessage;
import ChatMessage.TaskListMessage;
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

}
