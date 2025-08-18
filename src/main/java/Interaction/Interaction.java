package Interaction;

import ChatMessage.GoodByeMessage;
import ChatMessage.Sendable;

public class Interaction {

    public Sendable interact(Readable message) {
        // Dummy implementation
        return new GoodByeMessage();
    }
}
