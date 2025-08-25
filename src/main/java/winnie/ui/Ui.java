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

/**
 * User interface class for handling input and output.
 */
public class Ui {
    private CliWriter writer;
    private CliReader reader;

    public Ui() {
        this.writer = new CliWriter();
        this.reader = new CliReader();
    }

    /**
     * Shows a welcome message to the user.
     */
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

    /**
     * Shows a goodbye message to the user.
     */
    public void showGoodbye() {
        sendMessage(new GoodByeMessage());
    }

    /**
     * Shows the current task list to the user.
     *
     * @param tasks The array of tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        sendMessage(new TaskListMessage(tasks));
    }

    /**
     * Shows a message when a task is added.
     *
     * @param task      The task that was added.
     * @param taskCount The current number of tasks.
     */
    public void showTaskAdded(Task task, int taskCount) {
        sendMessage(new TaskAddedMessage(task, taskCount));
    }

    /**
     * Shows a message when a task is marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        sendMessage(new MarkTaskMessage(task));
    }

    /**
     * Shows a message when a task is unmarked as done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        sendMessage(new UnmarkTaskMessage(task));
    }

    /**
     * Shows a message when a task is deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The current number of tasks.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        sendMessage(new DeleteTaskMessage(task, taskCount));
    }

    /**
     * Shows an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        sendMessage(new ErrorMessage(errorMessage));
    }

    /**
     * Shows a message when there is an error loading tasks.
     */
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's command.
     */
    public String readCommand() {
        System.out.print("> ");
        ReceivedMessage userInput = reader.read();
        return userInput.getMessageContent().trim();
    }

    /**
     * Sends a message to the user.
     *
     * @param message The message to send.
     */
    private void sendMessage(Sendable message) {
        writer.write(message);
    }
}
