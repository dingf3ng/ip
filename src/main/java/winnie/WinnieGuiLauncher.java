package winnie;

import javafx.application.Application;
import javafx.stage.Stage;
import winnie.storage.Storage;
import winnie.tasklist.TaskList;
import winnie.ui.Gui;

/**
 * Main GUI launcher for the Winnie application.
 */
public class WinnieGuiLauncher extends Application {
    private Storage storage;
    private TaskList tasks;
    private Gui gui;
    private static String filePath;

    /**
     * Initializes the application data.
     */
    @Override
    public void init() {
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        gui = new Gui();
        gui.setData(tasks, storage);
        gui.start(primaryStage);
    }

    /**
     * Main entry point for the GUI application.
     */
    public static void main(String[] args) {
        filePath = "./data/winnie.txt";
        launch(args);
    }
}
