package winnie.uitool;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import winnie.chatmessage.Readable;
import winnie.chatmessage.ReceivedMessage;

public class GuiReader implements UiReader {

    private VBox dialogContainer;
    private TextField userInput;

    public GuiReader(VBox dialogContainer, TextField userInput) {
        this.dialogContainer = dialogContainer;
        this.userInput = userInput;
    }

    @Override
    public Readable read() {
        String inputText = userInput.getText();
        Label userText = new Label("You: " + inputText);
        dialogContainer.getChildren().add(userText);
        userInput.clear();
        return new ReceivedMessage(inputText);
    }

}
