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
                    messageDispatcher.addTask(command);
                }
            } else if (command.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(command.substring(7).trim());
                    messageDispatcher.unmarkTask(taskNumber);
                } catch (NumberFormatException e) {
                    messageDispatcher.addTask(command);
                }
            } else {
                messageDispatcher.addTask(command);
            }
        }
    }
}
