package winnie.exception;

public class InvalidDateTimeException extends WinnieException {
    public InvalidDateTimeException(String message) {
        super("Invalid date/time format: " + message + ". Please use yyyy-mm-dd or yyyy-mm-dd HHmm format.");
    }
}