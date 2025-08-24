package winnie.ui;

import winnie.chatmessage.ReceivedMessage;
import winnie.chatmessage.Sendable;
import winnie.clitool.CliReader;
import winnie.clitool.CliWriter;
import winnie.task.Task;
import winnie.chatmessage.GoodByeMessage;
import winnie.chatmessage.GreetingMessage;
import winnie.chatmessage.TaskListMessage;
import winnie.chatmessage.TaskAddedMessage;
import winnie.chatmessage.MarkTaskMessage;
import winnie.chatmessage.UnmarkTaskMessage;
import winnie.chatmessage.DeleteTaskMessage;
import winnie.chatmessage.ErrorMessage;

public class Ui {
    private CliWriter writer;
    private CliReader reader;

    public Ui() {
        this.writer = new CliWriter();
        this.reader = new CliReader();
    }

    public void showWelcome() {
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
        sendMessage(new GreetingMessage());
    }

    public void showGoodbye() {
        sendMessage(new GoodByeMessage());
    }

    public void showTaskList(Task[] tasks) {
        sendMessage(new TaskListMessage(tasks));
    }

    public void showTaskAdded(Task task, int taskCount) {
        sendMessage(new TaskAddedMessage(task, taskCount));
    }

    public void showTaskMarked(Task task) {
        sendMessage(new MarkTaskMessage(task));
    }

    public void showTaskUnmarked(Task task) {
        sendMessage(new UnmarkTaskMessage(task));
    }

    public void showTaskDeleted(Task task, int taskCount) {
        sendMessage(new DeleteTaskMessage(task, taskCount));
    }

    public void showError(String errorMessage) {
        sendMessage(new ErrorMessage(errorMessage));
    }

    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    public String readCommand() {
        System.out.print("> ");
        ReceivedMessage userInput = reader.read();
        return userInput.getMessageContent().trim();
    }

    private void sendMessage(Sendable message) {
        writer.write(message);
    }
}