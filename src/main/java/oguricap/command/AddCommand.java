package oguricap.command;

import oguricap.Storage;
import oguricap.TaskList;
import oguricap.Ui;
import oguricap.exception.DukeException;
import oguricap.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list,
     * saving the updated list to storage, and displaying a confirmation message.
     *
     * @param tasks   The task list to add the task to.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showAddTask(task, tasks.size());
    }
}
