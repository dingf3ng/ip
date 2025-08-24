package command;

import exception.WinnieException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskNumber < 1 || taskNumber > tasks.getTaskCount()) {
                throw new WinnieException("Invalid task number: " + taskNumber);
            }
            Task markedTask = tasks.markTask(taskNumber - 1);
            ui.showTaskMarked(markedTask);
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