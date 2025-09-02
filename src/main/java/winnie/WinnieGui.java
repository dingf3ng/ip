package winnie;

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

import winnie.task.Task;
import winnie.ui.Gui;
import winnie.ui.Ui;

public class WinnieGui extends Application {

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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        Label welcomeText = new Label(new GreetingMessage().getMessageContent());
        dialogContainer.getChildren().add(welcomeText);
    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label("You: " + input);
        Label winnieText = new Label("Winnie: " + getResponse(input));
        dialogContainer.getChildren().addAll(userText, winnieText);
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            Gui gui = new Gui();
            command.execute(tasks, gui, storage);
            return gui.getLastResponse();
        } catch (WinnieException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
