package Dispatcher;

import ChatMessage.Sendable;
import ChatMessage.GoodByeMessage;
import ChatMessage.GreetingMessage;
import ChatMessage.TaskAddedMessage;
import ChatMessage.TaskListMessage;
import ChatMessage.MarkTaskMessage;
import ChatMessage.UnmarkTaskMessage;
import ChatMessage.ErrorMessage;
import CliTool.*;
import TaskManager.TaskManager;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import Exception.WinnieException;
import Exception.EmptyDescriptionException;
import Exception.InvalidTaskNumberException;

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

    public void addTodo(String description) throws WinnieException {
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        Todo todo = new Todo(description.trim());
        taskManager.addTask(todo);
        sendOut(new TaskAddedMessage(todo, taskManager.getTaskCount()));
    }

    public void addDeadline(String description, String by) throws WinnieException {
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (by.trim().isEmpty()) {
            throw new EmptyDescriptionException("deadline time");
        }
        Deadline deadline = new Deadline(description.trim(), by.trim());
        taskManager.addTask(deadline);
        sendOut(new TaskAddedMessage(deadline, taskManager.getTaskCount()));
    }

    public void addEvent(String description, String from, String to) throws WinnieException {
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        if (from.trim().isEmpty() || to.trim().isEmpty()) {
            throw new EmptyDescriptionException("event time");
        }
        Event event = new Event(description.trim(), from.trim(), to.trim());
        taskManager.addTask(event);
        sendOut(new TaskAddedMessage(event, taskManager.getTaskCount()));
    }

    public void listTasks() {
        Task[] tasks = taskManager.getTasks();
        sendOut(new TaskListMessage(tasks));
    }

    public void markTask(int taskNumber) throws WinnieException {
        if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
            throw new InvalidTaskNumberException(String.valueOf(taskNumber), taskManager.getTaskCount());
        }
        Task markedTask = taskManager.markTask(taskNumber - 1);
        sendOut(new MarkTaskMessage(markedTask));
    }

    public void unmarkTask(int taskNumber) throws WinnieException {
        if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
            throw new InvalidTaskNumberException(String.valueOf(taskNumber), taskManager.getTaskCount());
        }
        Task unmarkedTask = taskManager.unmarkTask(taskNumber - 1);
        sendOut(new UnmarkTaskMessage(unmarkedTask));
    }

    public void handleError(WinnieException e) {
        sendOut(new ErrorMessage(e.getMessage()));
    }

}
