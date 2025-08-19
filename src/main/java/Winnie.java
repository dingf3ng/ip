import Dispatcher.MessageDispatcher;
import CliTool.CliReader;
import ChatMessage.ReceivedMessage;

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
            
            if (command.equals("bye")) {
                messageDispatcher.sayGoodbye();
                break;
            } else if (command.equals("list")) {
                messageDispatcher.listTasks();
            } else if (command.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(command.substring(5).trim());
                    messageDispatcher.markTask(taskNumber);
                } catch (NumberFormatException e) {
                    messageDispatcher.addTodo(command);
                }
            } else if (command.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(command.substring(7).trim());
                    messageDispatcher.unmarkTask(taskNumber);
                } catch (NumberFormatException e) {
                    messageDispatcher.addTodo(command);
                }
            } else if (command.startsWith("todo ")) {
                String description = command.substring(5).trim();
                messageDispatcher.addTodo(description);
            } else if (command.startsWith("deadline ")) {
                String rest = command.substring(9).trim();
                String[] parts = rest.split(" /by ", 2);
                if (parts.length == 2) {
                    messageDispatcher.addDeadline(parts[0].trim(), parts[1].trim());
                } else {
                    messageDispatcher.addTodo(command);
                }
            } else if (command.startsWith("event ")) {
                String rest = command.substring(6).trim();
                String[] parts = rest.split(" /from ", 2);
                if (parts.length == 2) {
                    String[] timeParts = parts[1].split(" /to ", 2);
                    if (timeParts.length == 2) {
                        messageDispatcher.addEvent(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
                    } else {
                        messageDispatcher.addTodo(command);
                    }
                } else {
                    messageDispatcher.addTodo(command);
                }
            } else {
                messageDispatcher.addTodo(command);
            }
        }
    }
}
