package parser;

import command.*;
import exception.WinnieException;
import exception.EmptyDescriptionException;
import exception.MissingTimeException;

public class Parser {

    public static Command parse(String fullCommand) throws WinnieException {
        String commandWord = getCommandWord(fullCommand);
        CommandEnum command = CommandEnum.fromString(commandWord);
        
        switch (command) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return parseMarkCommand(fullCommand);
            case UNMARK:
                return parseUnmarkCommand(fullCommand);
            case DELETE:
                return parseDeleteCommand(fullCommand);
            case TODO:
                return parseTodoCommand(fullCommand);
            case DEADLINE:
                return parseDeadlineCommand(fullCommand);
            case EVENT:
                return parseEventCommand(fullCommand);
            case UNKNOWN:
            default:
                return new UnknownCommand(fullCommand);
        }
    }

    private static String getCommandWord(String fullCommand) {
        String[] parts = fullCommand.trim().split("\\s+");
        return parts[0].toLowerCase();
    }

    private static Command parseMarkCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("mark")) {
            throw new WinnieException("Please specify which task number to mark. Example: mark 1");
        }
        String numberStr = commandInput.substring(5).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to mark. Example: mark 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    private static Command parseUnmarkCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("unmark")) {
            throw new WinnieException("Please specify which task number to unmark. Example: unmark 1");
        }
        String numberStr = commandInput.substring(7).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to unmark. Example: unmark 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    private static Command parseDeleteCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("delete")) {
            throw new WinnieException("Please specify which task number to delete. Example: delete 1");
        }
        String numberStr = commandInput.substring(7).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to delete. Example: delete 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    private static Command parseTodoCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("todo")) {
            throw new EmptyDescriptionException("todo");
        }
        String description = commandInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new TodoCommand(description);
    }

    private static Command parseDeadlineCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("deadline")) {
            throw new EmptyDescriptionException("deadline");
        }
        
        String args = commandInput.substring(8).trim();
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        int byIndex = args.indexOf("/by");
        if (byIndex == -1) {
            throw new MissingTimeException("deadline", "/by time");
        }

        String description = args.substring(0, byIndex).trim();
        String by = args.substring(byIndex + 3).trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (by.isEmpty()) {
            throw new MissingTimeException("deadline", "time value");
        }

        return new DeadlineCommand(description, by);
    }

    private static Command parseEventCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("event")) {
            throw new EmptyDescriptionException("event");
        }
        
        String args = commandInput.substring(5).trim();
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }

        int fromIndex = args.indexOf("/from");
        int toIndex = args.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw new MissingTimeException("event", "/from and /to times");
        }

        String description = args.substring(0, fromIndex).trim();
        String from = args.substring(fromIndex + 5, toIndex).trim();
        String to = args.substring(toIndex + 3).trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new MissingTimeException("event", "start and end times");
        }

        return new EventCommand(description, from, to);
    }
}