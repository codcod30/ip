package oguricap.command;

import oguricap.Storage;
import oguricap.TaskList;
import oguricap.Ui;
import oguricap.exception.DukeException;
import oguricap.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task from the task list,
     * saving the updated list to storage, and displaying a confirmation message.
     *
     * @param tasks   The task list to remove the task from.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @throws DukeException If there is an error during execution.
     */
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
