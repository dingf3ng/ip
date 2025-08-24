package command;

import storage.Storage;
import task.Todo;
import tasklist.TaskList;
import ui.Ui;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Todo todo = new Todo(description);
            tasks.addTask(todo);
            ui.showTaskAdded(todo, tasks.getTaskCount());
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