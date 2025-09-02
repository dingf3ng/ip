package winnie;

import winnie.command.Command;
import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.Cli;

/**
 * Main entry point for the Winnie application.
 */
public class WinnieLauncher {
    private Storage storage;
    private TaskList tasks;
    private Cli ui;

    /**
     * Constructor for the Winnie application.
     *
     * @param filePath Path to the file where tasks are stored.
     */
    public WinnieLauncher(String filePath) {
        ui = new Cli();
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
            Command command = ui.readCommand();
            command.execute(tasks, ui, storage);
            if (command.isExit()) {
                break;
            }
        }
    }

    /**
     * Main entry point for the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new WinnieLauncher("./data/winnie.txt").run();
    }
}
