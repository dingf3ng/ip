package exception;

public class InvalidTaskNumberException extends WinnieException {
    public InvalidTaskNumberException(String input, int maxTasks) {
        super("Task number '" + input + "' is invalid. Please enter a number between 1 and " + maxTasks + ".");
    }
}