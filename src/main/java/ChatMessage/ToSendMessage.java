package ChatMessage;

public class ToSendMessage implements Sendable {

    private final String content;

    public ToSendMessage(String content) {
        this.content = content;
    }

    @Override
    public String getMessageContent() {
        return content;
    }
}
