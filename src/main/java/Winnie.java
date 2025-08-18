import Dispatcher.MessageDispatcher;

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
        messageDispatcher.sayGoodbye();
    }
}
