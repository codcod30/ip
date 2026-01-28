package oguricap.command;

import oguricap.TaskList;
import oguricap.Ui;
import oguricap.Storage;
import oguricap.exception.DukeException;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving/loading tasks.
     * @throws DukeException If there is an error during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether this command is an exit command.
     *
     * @return true if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
