package winnie.chatmessage;

public class GoodByeMessage implements Sendable {

    private final String goodbyeMessage = "Bye. Hope to see you again soon!";

    public GoodByeMessage() { }

    @Override
    public String getMessageContent() {
        return goodbyeMessage;
    }
}
