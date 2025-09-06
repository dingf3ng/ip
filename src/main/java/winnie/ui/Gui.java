package winnie.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import winnie.storage.Storage;
import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.uitool.GuiReader;
import winnie.uitool.GuiWriter;
import winnie.chatmessage.GoodByeMessage;
import winnie.chatmessage.GreetingMessage;
import winnie.chatmessage.TaskListMessage;
import winnie.chatmessage.TaskAddedMessage;
import winnie.chatmessage.MarkTaskMessage;
import winnie.chatmessage.UnmarkTaskMessage;
import winnie.chatmessage.DeleteTaskMessage;
import winnie.chatmessage.ErrorMessage;
import winnie.chatmessage.FoundTasksMessage;
import winnie.chatmessage.Readable;
import winnie.command.Command;
import winnie.command.VoidCommand;
import winnie.exception.WinnieException;
import winnie.parser.Parser;

public class Gui extends Application implements Ui {

    private GuiWriter guiWriter;
    private GuiReader guiReader;

    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList tasks;
    private Storage storage;

    public Gui() {
        // Initialize later in start method when JavaFX components are created
    }

    public void setData(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public void start(Stage stage) {

        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Initialize GUI components now that they exist
        this.guiWriter = new GuiWriter(this.dialogContainer);
        this.guiReader = new GuiReader(this.dialogContainer, this.userInput);

        sendButton.setOnMouseClicked((event) -> {
            handleCommand();
        });

        userInput.setOnAction((event) -> {
            handleCommand();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Show welcome message
        showWelcome();
    }

    private void handleCommand() {
        Command command = readCommand();
        command.execute(tasks, this, storage);
        if (command.isExit()) {
            javafx.application.Platform.exit();
        }
    }

    @Override
    public void showTaskList(TaskList tasks) {
        guiWriter.write(new TaskListMessage(tasks));
    }

    @Override
    public void showTaskAdded(Task task, int taskCount) {
        guiWriter.write(new TaskAddedMessage(task, taskCount));
    }

    @Override
    public void showTaskMarked(Task task) {
        guiWriter.write(new MarkTaskMessage(task));
    }

    @Override
    public void showTaskUnmarked(Task task) {
        guiWriter.write(new UnmarkTaskMessage(task));
    }

    @Override
    public void showTaskDeleted(Task task, int taskCount) {
        guiWriter.write(new DeleteTaskMessage(task, taskCount));
    }

    @Override
    public void showError(String errorMessage) {
        guiWriter.write(new ErrorMessage(errorMessage));
    }

    @Override
    public void showFoundTasks(TaskList foundTasks) {
        guiWriter.write(new FoundTasksMessage(foundTasks));
    }

    @Override
    public void showGoodbye() {
        guiWriter.write(new GoodByeMessage());
    }

    @Override
    public Command readCommand() {
        Readable userInput = guiReader.read();
        try {
            return Parser.parse(userInput.getMessageContent().trim());
        } catch (WinnieException e) {
            showError(e.getMessage());
        }
        return new VoidCommand();
    }

    @Override
    public void showWelcome() {
        guiWriter.write(new GreetingMessage());
    }

    @Override
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }
}
