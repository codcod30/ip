package oguricap.command;

import oguricap.Storage;
import oguricap.TaskList;
import oguricap.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The task list to display.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving/loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
