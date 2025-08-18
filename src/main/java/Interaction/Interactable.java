package Interaction;

import ChatMessage.Sendable;

public interface Interactable {

    Sendable interact(Readable message);

}
