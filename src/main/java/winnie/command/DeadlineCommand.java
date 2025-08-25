package winnie.command;

import winnie.storage.Storage;
import winnie.task.Deadline;
import winnie.tasklist.TaskList;
import winnie.ui.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Deadline deadline = new Deadline(description, by);
            tasks.addTask(deadline);
            ui.showTaskAdded(deadline, tasks.getTaskCount());
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
