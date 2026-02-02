package oguricap.command;

import oguricap.Storage;
import oguricap.TaskList;
import oguricap.Ui;
import oguricap.exception.DukeException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) { this.index = index; }

    /**
     * Executes the mark command by marking the task as done in the task list,
     * saving the updated list to storage, and displaying a confirmation message.
     *
     * @param tasks   The task list containing the task to be marked.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @throws DukeException If there is an error during execution.
     */
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
