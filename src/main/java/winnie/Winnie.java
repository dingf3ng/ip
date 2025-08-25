package winnie;

import winnie.command.Command;
import winnie.exception.WinnieException;
import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;
import winnie.parser.Parser;

/**
 * Main entry point for the Winnie application.
 */
public class Winnie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Winnie application.
     *
     * @param filePath Path to the file where tasks are stored.
     */
    public Winnie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the application.
     */
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

    /**
     * Main entry point for the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Winnie("./data/winnie.txt").run();
    }
}
