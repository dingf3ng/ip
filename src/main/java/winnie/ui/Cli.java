package winnie.ui;

import winnie.chatmessage.ReceivedMessage;
import winnie.chatmessage.Sendable;
import winnie.clitool.CliReader;
import winnie.clitool.CliWriter;
import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.chatmessage.GoodByeMessage;
import winnie.chatmessage.GreetingMessage;
import winnie.chatmessage.TaskListMessage;
import winnie.chatmessage.TaskAddedMessage;
import winnie.chatmessage.MarkTaskMessage;
import winnie.chatmessage.UnmarkTaskMessage;
import winnie.chatmessage.DeleteTaskMessage;
import winnie.chatmessage.ErrorMessage;
import winnie.chatmessage.FoundTasksMessage;

/**
 * CLI class for handling input and output.
 */
public class Cli implements Ui {
    private CliWriter writer;
    private CliReader reader;

    public Cli() {
        this.writer = new CliWriter();
        this.reader = new CliReader();
    }

    @Override
    public void showWelcome() {
        String logo = """
                    %:.     %:-
                    --%::::::%%
                    %::::::::-
                    -:%:#%::::%
                    -:::::::::::
                    :-:::-%::::%%
                    *+%+-----%++*.
                +++++*+++++%+++++++
                ::::++*+++++++++++*%:
                -:::%***++++++++++%:-
                -:::***+%%%%%%#-:%=
                %:::--:::::::::::%:
                    ---::::::::::::=
                    %%::%:::::::::%
                    -:::-:::::::%
                    =-:::-----=-:%
                    -:::   %-:::
                    -::#   %-::%
                    %-::%   %-:::%
                    %%%
                """;
        System.out.println("Hello from\n" + logo);
        sendMessage(new GreetingMessage());
    }

    @Override
    public void showGoodbye() {
        sendMessage(new GoodByeMessage());
    }

    @Override
    public void showTaskList(TaskList tasks) {
        sendMessage(new TaskListMessage(tasks));
    }

    @Override
    public void showTaskAdded(Task task, int taskCount) {
        sendMessage(new TaskAddedMessage(task, taskCount));
    }

    @Override
    public void showTaskMarked(Task task) {
        sendMessage(new MarkTaskMessage(task));
    }

    @Override
    public void showTaskUnmarked(Task task) {
        sendMessage(new UnmarkTaskMessage(task));
    }

    @Override
    public void showTaskDeleted(Task task, int taskCount) {
        sendMessage(new DeleteTaskMessage(task, taskCount));
    }

    @Override
    public void showError(String errorMessage) {
        sendMessage(new ErrorMessage(errorMessage));
    }

    @Override
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    @Override
    public void showFoundTasks(TaskList foundTasks) {
        sendMessage(new FoundTasksMessage(foundTasks));
    }

    @Override
    public String readCommand() {
        System.out.print("> ");
        ReceivedMessage userInput = reader.read();
        return userInput.getMessageContent().trim();
    }

    @Override
    public void sendMessage(Sendable message) {
        writer.write(message);
    }
}
