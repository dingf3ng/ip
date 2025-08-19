package Exception;

public class EmptyDescriptionException extends WinnieException {
    public EmptyDescriptionException(String taskType) {
        super("Oops! The description of a " + taskType + " cannot be empty.");
    }
}