import Dispatcher.CommandDispatcher;
import Dispatcher.MessageDispatcher;
import CliTool.CliReader;
import ChatMessage.ReceivedMessage;
import Exception.WinnieException;
import Exception.UnknownCommandException;

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


        MessageDispatcher outputMessageDispatcher = 
                new MessageDispatcher();
        CommandDispatcher inputMessageDispatcher = 
                new CommandDispatcher(outputMessageDispatcher);

        outputMessageDispatcher.sayGreeting();

        CliReader reader = new CliReader();
        while (true) {
            ReceivedMessage userInput = reader.read();
            String commandInput = userInput.getMessageContent().trim();
            Command command = Command.fromString(commandInput);

            try {
                switch (command) {
                    case BYE:
                        outputMessageDispatcher.sayGoodbye();
                        return;
                    case LIST:
                        outputMessageDispatcher.listTasks();
                        break;
                    case MARK:
                        inputMessageDispatcher.handleMarkCommand(commandInput);
                        break;
                    case UNMARK:
                        inputMessageDispatcher.handleUnmarkCommand(commandInput);
                        break;
                    case DELETE:
                        inputMessageDispatcher.handleDeleteCommand(commandInput);
                        break;
                    case TODO:
                        inputMessageDispatcher.handleTodoCommand(commandInput);
                        break;
                    case DEADLINE:
                        inputMessageDispatcher.handleDeadlineCommand(commandInput);
                        break;
                    case EVENT:
                        inputMessageDispatcher.handleEventCommand(commandInput);
                        break;
                    case UNKNOWN:
                    default:
                        throw new UnknownCommandException(commandInput);
                }
            } catch (WinnieException e) {
                outputMessageDispatcher.handleError(e);
            }
        }
    }
}
