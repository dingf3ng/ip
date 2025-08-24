package dispatcher;

import chatmessage.DeleteTaskMessage;
import chatmessage.ErrorMessage;
import chatmessage.GoodByeMessage;
import chatmessage.GreetingMessage;
import chatmessage.MarkTaskMessage;
import chatmessage.Sendable;
import chatmessage.TaskAddedMessage;
import chatmessage.TaskListMessage;
import chatmessage.UnmarkTaskMessage;
import clitool.CliWriter;
import exception.EmptyDescriptionException;
import exception.InvalidTaskNumberException;
import exception.WinnieException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import taskmanager.TaskManager;

public class MessageDispatcher {
    private TaskManager taskManager;

    public MessageDispatcher() {
        this.taskManager = new TaskManager();
    }

    private void sendOut(Sendable message) {
        CliWriter cliWriter = new CliWriter();
        cliWriter.write(message);
    }

    public void sayGreeting() {
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
            throw new InvalidTaskNumberException(
                    String.valueOf(taskNumber), taskManager.getTaskCount());
        }
        Task markedTask = taskManager.markTask(taskNumber - 1);
        sendOut(new MarkTaskMessage(markedTask));
    }

    public void unmarkTask(int taskNumber) throws WinnieException {
        if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
            throw new InvalidTaskNumberException(
                    String.valueOf(taskNumber), taskManager.getTaskCount());
        }
        Task unmarkedTask = taskManager.unmarkTask(taskNumber - 1);
        sendOut(new UnmarkTaskMessage(unmarkedTask));
    }

    public void deleteTask(int taskNumber) throws WinnieException {
        if (taskNumber < 1 || taskNumber > taskManager.getTaskCount()) {
            throw new InvalidTaskNumberException(
                    String.valueOf(taskNumber), taskManager.getTaskCount());
        }
        Task deletedTask = taskManager.deleteTask(taskNumber - 1);
        sendOut(new DeleteTaskMessage(deletedTask, taskManager.getTaskCount()));
    }

    public void handleError(WinnieException e) {
        sendOut(new ErrorMessage(e.getMessage()));
    }

}
