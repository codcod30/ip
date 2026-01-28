package oguricap.command;

import oguricap.TaskList;
import oguricap.Ui;
import oguricap.Storage;
import oguricap.exception.DukeException;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) { this.index = index; }

    /**
     * Executes the unmark command by marking the task as not done in the task list,
     * saving the updated list to storage, and displaying a confirmation message.
     *
     * @param tasks   The task list containing the task to be unmarked.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @throws DukeException If there is an error during execution.
     */
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
