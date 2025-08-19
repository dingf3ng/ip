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
            } else {
                messageDispatcher.addTask(command);
            }
        }
    }
}
