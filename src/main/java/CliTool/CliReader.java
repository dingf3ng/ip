package clitool;

import chatmessage.ReceivedMessage;

public class CliReader {
    private java.util.Scanner scanner = new java.util.Scanner(System.in);
    
    public ReceivedMessage read() {
        try {
            return new ReceivedMessage(scanner.nextLine());
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
            return new ReceivedMessage("Error");
        }
    }
}
