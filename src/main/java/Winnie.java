import Dispatcher.MessageDispatcher;
import CliTool.CliReader;
import ChatMessage.ReceivedMessage;
import Exception.WinnieException;
import Exception.UnknownCommandException;
import Exception.MissingTimeException;

public class Winnie {
    
    public static void main(String[] args) {
        String logo = """                             
            %:.     %:-           
            --%::::::%%           
            %::::::::-           
            -:%:#%::::%          
            -:::::::::::          
            :-:::-%::::%%         
            *+%+-----%++*.        
        +++++*+++++%+++++++      
        ::::++*+++++++++++*%:     
        -:::%***++++++++++%:-     
        -:::***+%%%%%%#-:%=      
        %:::--:::::::::::%:      
            ---::::::::::::=       
            %%::%:::::::::%        
            -:::-:::::::%         
            =-:::-----=-:%         
            -:::   %-:::          
            -::#   %-::%          
            %-::%   %-:::%         
            %%%                   
        """;
        System.out.println("Hello from\n" + logo);
        MessageDispatcher messageDispatcher = new MessageDispatcher();
        messageDispatcher.sayGreeting();
        
        CliReader reader = new CliReader();
        while (true) {
            ReceivedMessage userInput = reader.read();
            String command = userInput.getMessageContent().trim();
            
            try {
                if (command.equals("bye")) {
                    messageDispatcher.sayGoodbye();
                    break;
                } else if (command.equals("list")) {
                    messageDispatcher.listTasks();
                } else if (command.equals("mark") || command.equals("unmark")) {
                    throw new WinnieException("Please specify which task number to " + command + ". Example: " + command + " 1");
                } else if (command.startsWith("mark ")) {
                    String numberStr = command.substring(5).trim();
                    if (numberStr.isEmpty()) {
                        throw new WinnieException("Please specify which task number to mark. Example: mark 1");
                    }
                    try {
                        int taskNumber = Integer.parseInt(numberStr);
                        messageDispatcher.markTask(taskNumber);
                    } catch (NumberFormatException e) {
                        throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
                    }
                } else if (command.startsWith("unmark ")) {
                    String numberStr = command.substring(7).trim();
                    if (numberStr.isEmpty()) {
                        throw new WinnieException("Please specify which task number to unmark. Example: unmark 1");
                    }
                    try {
                        int taskNumber = Integer.parseInt(numberStr);
                        messageDispatcher.unmarkTask(taskNumber);
                    } catch (NumberFormatException e) {
                        throw new WinnieException("Task number must be a valid number. You entered: " + numberStr);
                    }
                } else if (command.equals("todo")) {
                    throw new WinnieException("The description of a todo cannot be empty.");
                } else if (command.startsWith("todo ")) {
                    String description = command.substring(5).trim();
                    messageDispatcher.addTodo(description);
                } else if (command.equals("deadline")) {
                    throw new MissingTimeException("deadline", "description and deadline");
                } else if (command.startsWith("deadline ")) {
                    String rest = command.substring(9).trim();
                    String[] parts = rest.split(" /by ", 2);
                    if (parts.length == 2) {
                        messageDispatcher.addDeadline(parts[0].trim(), parts[1].trim());
                    } else {
                        throw new MissingTimeException("deadline", "deadline time");
                    }
                } else if (command.equals("event")) {
                    throw new MissingTimeException("event", "description and time");
                } else if (command.startsWith("event ")) {
                    String rest = command.substring(6).trim();
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
                } else {
                    throw new UnknownCommandException(command);
                }
            } catch (WinnieException e) {
                messageDispatcher.handleError(e);
            }
        }
    }
}
