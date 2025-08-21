package Dispatcher;

import Exception.MissingTimeException;
import Exception.WinnieException;

public class CommandDispatcher {
    private MessageDispatcher messageDispatcher;

    public CommandDispatcher(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    public void handleMarkCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("mark")) {
            throw new WinnieException("Please specify which task number to mark. Example: mark 1");
        }
        String numberStr = commandInput.substring(5).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to mark. Example: mark 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            messageDispatcher.markTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    public void handleUnmarkCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("unmark")) {
            throw new WinnieException("Please specify which task number to unmark. Example: unmark 1");
        }
        String numberStr = commandInput.substring(7).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to unmark. Example: unmark 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            messageDispatcher.unmarkTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    public void handleDeleteCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("delete")) {
            throw new WinnieException("Please specify which task number to delete. Example: delete 1");
        }
        String numberStr = commandInput.substring(7).trim();
        if (numberStr.isEmpty()) {
            throw new WinnieException("Please specify which task number to delete. Example: delete 1");
        }
        try {
            int taskNumber = Integer.parseInt(numberStr);
            messageDispatcher.deleteTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
        }
    }

    public void handleTodoCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("todo")) {
            throw new WinnieException("The description of a todo cannot be empty.");
        }
        String description = commandInput.substring(5).trim();
        messageDispatcher.addTodo(description);
    }

    public void handleDeadlineCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("deadline")) {
            throw new MissingTimeException("deadline", "description and deadline");
        }
        String rest = commandInput.substring(9).trim();
        String[] parts = rest.split(" /by ", 2);
        if (parts.length == 2) {
            messageDispatcher.addDeadline(parts[0].trim(), parts[1].trim());
        } else {
            throw new MissingTimeException("deadline", "deadline time");
        }
    }

    public void handleEventCommand(String commandInput) throws WinnieException {
        if (commandInput.trim().equals("event")) {
            throw new MissingTimeException("event", "description and time");
        }
        String rest = commandInput.substring(6).trim();
        String[] parts = rest.split(" /from ", 2);
        if (parts.length == 2) {
            String[] timeParts = parts[1].split(" /to ", 2);
            if (timeParts.length == 2) {
                messageDispatcher.addEvent(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
            } else {
                throw new MissingTimeException("event", "end time");
            }
        } else {
            throw new MissingTimeException("event", "start time");
        }
    }
}
