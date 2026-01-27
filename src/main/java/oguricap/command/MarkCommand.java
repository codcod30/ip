package oguricap.command;

import oguricap.TaskList;
import oguricap.Ui;
import oguricap.Storage;
import oguricap.exception.DukeException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) { this.index = index; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (!tasks.isValidIndex(index)) {
            throw new DukeException("Task number out of range. Please enter a valid number.");
        }
        tasks.get(index).markAsDone();
        storage.save(tasks.getTasks());
        ui.showMarkedTask(tasks.get(index));
    }
}
