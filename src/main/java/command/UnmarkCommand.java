package command;

import exception.WinnieException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskNumber < 1 || taskNumber > tasks.getTaskCount()) {
                throw new WinnieException("Invalid task number: " + taskNumber);
            }
            Task unmarkedTask = tasks.unmarkTask(taskNumber - 1);
            ui.showTaskUnmarked(unmarkedTask);
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}