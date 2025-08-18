package Interaction;

import ChatMessage.GreetingMessage;
import ChatMessage.Sendable;

public class InitialInteraction implements Interactable {

    @Override
    public Sendable interact(Readable message) {
        // Dummy implementation
        return new GreetingMessage();
    }
}
