package ChatMessage;

public class GreetingMessage extends ToSendMessage {

    public GreetingMessage() {
        super("Hello! I'm " + Utils.BOT_NAME + "\n     What can I do for you?");
    }

}
