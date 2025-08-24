package winnie.clitool;

import winnie.chatmessage.Sendable;

public class CliWriter {
    public void write(Sendable message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + message.getMessageContent());
        System.out.println("    ____________________________________________________________");
    }
}
