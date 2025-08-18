package CliTool;

import ChatMessage.Sendable;

public class CliWriter {
    public void write(Sendable message) {
        System.out.println(message.getMessageContent());
    }
}
