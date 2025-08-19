package ChatMessage;

public class GreetingMessage implements Sendable {

    private static final String GREETING = "Hello! I'm " + Utils.BOT_NAME + 
            "\n     What can I do for you?";

    @Override
    public String getMessageContent() {
        return GREETING;
    }
}
