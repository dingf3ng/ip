package Exception;

public class UnknownCommandException extends WinnieException {
    public UnknownCommandException(String command) {
        super("Sorry, I don't understand what '" + command + "' means. Try 'list', 'todo', 'deadline', 'event', 'mark', 'unmark', or 'bye'.");
    }
}