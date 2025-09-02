import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import winnie.command.Command;
import winnie.exception.WinnieException;
import winnie.parser.Parser;
import winnie.storage.Storage;
import winnie.tasklist.TaskList;

public class Main extends Application {

    private Storage storage;
    private TaskList tasks;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        storage = new Storage("./data/winnie.txt");
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            tasks = new TaskList();
        }

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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        Label welcomeText = new Label(getWelcomeMessage());
        dialogContainer.getChildren().add(welcomeText);
    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label("You: " + input);
        Label winnieText = new Label("Winnie: " + getResponse(input));
        dialogContainer.getChildren().addAll(userText, winnieText);
        userInput.clear();
    }

    private String getWelcomeMessage() {
        return "Hello! I'm Winnie, your personal task manager. What can I do for you?";
    }

    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            GuiUi guiUi = new GuiUi();
            command.execute(tasks, guiUi, storage);
            return guiUi.getLastResponse();
        } catch (WinnieException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }

    private class GuiUi extends winnie.ui.Ui {
        private String lastResponse = "";

        @Override
        public void showTaskList(winnie.tasklist.TaskList tasks) {
            if (tasks.getTaskCount() == 0) {
                lastResponse = "No tasks in your list.";
            } else {
                StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.getTaskCount(); i++) {
                    sb.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
                }
                lastResponse = sb.toString().trim();
            }
        }

        @Override
        public void showTaskAdded(winnie.task.Task task, int taskCount) {
            lastResponse = "Got it. I've added this task:\n  " + task + 
                          "\nNow you have " + taskCount + " tasks in the list.";
        }

        @Override
        public void showTaskMarked(winnie.task.Task task) {
            lastResponse = "Nice! I've marked this task as done:\n  " + task;
        }

        @Override
        public void showTaskUnmarked(winnie.task.Task task) {
            lastResponse = "OK, I've marked this task as not done yet:\n  " + task;
        }

        @Override
        public void showTaskDeleted(winnie.task.Task task, int taskCount) {
            lastResponse = "Noted. I've removed this task:\n  " + task + 
                          "\nNow you have " + taskCount + " tasks in the list.";
        }

        @Override
        public void showError(String errorMessage) {
            lastResponse = errorMessage;
        }

        @Override
        public void showFoundTasks(winnie.tasklist.TaskList foundTasks) {
            if (foundTasks.getTaskCount() == 0) {
                lastResponse = "No matching tasks found.";
            } else {
                StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
                for (int i = 0; i < foundTasks.getTaskCount(); i++) {
                    sb.append((i + 1)).append(". ").append(foundTasks.getTask(i)).append("\n");
                }
                lastResponse = sb.toString().trim();
            }
        }

        @Override
        public void showGoodbye() {
            lastResponse = "Bye. Hope to see you again soon!";
        }

        @Override
        public String readCommand() {
            return "";
        }

        public String getLastResponse() {
            return lastResponse;
        }
    }
}