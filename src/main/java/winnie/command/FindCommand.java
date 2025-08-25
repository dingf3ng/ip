package winnie.command;

import winnie.storage.Storage;
import winnie.task.Task;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

/**
 * Command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        Task[] allTasks = tasks.getTasks();
        
        for (Task task : allTasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        
        ui.showFoundTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}