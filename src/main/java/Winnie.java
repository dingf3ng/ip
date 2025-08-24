import command.Command;
import exception.WinnieException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import parser.Parser;

public class Winnie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Winnie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (WinnieException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Winnie("./data/winnie.txt").run();
    }
}