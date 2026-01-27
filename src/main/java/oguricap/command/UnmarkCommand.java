package oguricap.command;

import oguricap.TaskList;
import oguricap.Ui;
import oguricap.Storage;
import oguricap.exception.DukeException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) { this.index = index; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (!tasks.isValidIndex(index)) {
            throw new DukeException("Task number out of range. Please enter a valid number.");
        }

        tasks.get(index).markNotDone();
        storage.save(tasks.getTasks());
        ui.showUnmarkedTask(tasks.get(index));
    }
}
