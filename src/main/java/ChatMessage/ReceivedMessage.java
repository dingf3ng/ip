package chatmessage;

public class ReceivedMessage implements Readable {

    private final String content;

    public ReceivedMessage(String content) {
        this.content = content;
    }

    @Override
    public String getMessageContent() {
        return content;
    }
}
