package winnie.uitool;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import winnie.chatmessage.Sendable;

public class GuiWriter implements UiWriter {
    private VBox dialogContainer;

    public GuiWriter(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    @Override
    public void write(Sendable message) {
        Label winnieText = new Label("Winnie: " + message.getMessageContent());
        dialogContainer.getChildren().add(winnieText);
        // userInput.clear();
    }
}
