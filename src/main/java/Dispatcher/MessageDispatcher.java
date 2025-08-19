package Dispatcher;

import ChatMessage.Sendable;
import ChatMessage.GoodByeMessage;
import ChatMessage.GreetingMessage;
import ChatMessage.AddedTaskMessage;
import ChatMessage.TaskListMessage;
import ChatMessage.Readable;
import CliTool.*;
import Interaction.InitialInteraction;
import Interaction.Interactable;
import TaskManager.TaskManager;

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
        sendOut(new AddedTaskMessage(taskName));
    }

    public void listTasks() {
        String[] tasks = taskManager.getTasks();
        sendOut(new TaskListMessage(tasks));
    }

    public Interactable process(Readable message) {
        //TODO: Dummy implementation
        return new InitialInteraction();
    }

}
