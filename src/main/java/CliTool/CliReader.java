package CliTool;

import ChatMessage.ReceivedMessage;

public class CliReader {
    public ReceivedMessage read() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        try {
            return new ReceivedMessage(scanner.nextLine());
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
            return new ReceivedMessage("Error");
        } finally {
            if (scanner != null) { // Close the scanner if it was opened
                scanner.close();
            }
        }
    }
}
