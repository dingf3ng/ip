package Dispatcher;

import ChatMessage.Sendable;
import ChatMessage.GoodByeMessage;
import ChatMessage.GreetingMessage;
import ChatMessage.TaskAddedMessage;
import ChatMessage.TaskListMessage;
import ChatMessage.MarkTaskMessage;
import ChatMessage.UnmarkTaskMessage;
import ChatMessage.ToSendMessage;
import CliTool.*;
import TaskManager.TaskManager;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;

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

    public void addTodo(String description) {
        Todo todo = new Todo(description);
        taskManager.addTask(todo);
        sendOut(new TaskAddedMessage(todo, taskManager.getTaskCount()));
    }

    public void addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        taskManager.addTask(deadline);
        sendOut(new TaskAddedMessage(deadline, taskManager.getTaskCount()));
    }

    public void addEvent(String description, String from, String to) {
        Event event = new Event(description, from, to);
        taskManager.addTask(event);
        sendOut(new TaskAddedMessage(event, taskManager.getTaskCount()));
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
