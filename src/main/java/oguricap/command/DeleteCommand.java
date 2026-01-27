package oguricap.command;

import oguricap.TaskList;
import oguricap.Ui;
import oguricap.Storage;
import oguricap.exception.DukeException;

import oguricap.task.Task;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) { this.index = index; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (!tasks.isValidIndex(index)) {
            throw new DukeException("Task number out of range. Please enter a valid number.");
        }

        Task removed = tasks.remove(index);
        storage.save(tasks.getTasks());
        ui.showDeletedTask(removed, tasks.size());
    }
}
