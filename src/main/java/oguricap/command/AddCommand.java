package oguricap.command;

import oguricap.task.Task;
import oguricap.TaskList;
import oguricap.Ui;
import oguricap.Storage;
import oguricap.exception.DukeException;

import java.util.ArrayList;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) { this.task = task; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showAddTask(task, tasks.size());
    }
}
