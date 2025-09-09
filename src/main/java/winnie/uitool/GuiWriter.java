package winnie.uitool;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import winnie.chatmessage.Sendable;
import winnie.ui.DialogBox;

public class GuiWriter implements UiWriter {
    private VBox dialogContainer;
    private Image dukeImage;

    public GuiWriter(VBox dialogContainer, Image dukeImage) {
        this.dialogContainer = dialogContainer;
        this.dukeImage = dukeImage;
    }

    @Override
    public void write(Sendable message) {
        DialogBox winnieDialog = DialogBox.getDukeDialog(message.getMessageContent(), dukeImage);
        dialogContainer.getChildren().add(winnieDialog);
    }
}
