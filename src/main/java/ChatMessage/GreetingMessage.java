package ChatMessage;

public class GreetingMessage extends ToSendMessage {

    public GreetingMessage() {
        super("Hello! I'm " + Utils.BOT_NAME + "\nWhat can I do for you?");
    }

}
