package oguricap.command;

import oguricap.Storage;
import oguricap.TaskList;
import oguricap.Ui;
import oguricap.exception.DukeException;

/**
 * Represents the "help" command, which shows available commands to the user.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command by displaying a list of available commands.
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving/loading tasks.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        String helpMessage = """
            Here are the available commands:
            - list: List all tasks
            - todo <description>: Add a todo
            - deadline <desc> /by <date>: Add a deadline
            - event <desc> /from <start> /to <end>: Add an event
            - mark <index>: Mark a task as done
            - unmark <index>: Unmark a task
            - delete <index>: Delete a task
            - find <keyword>: Search tasks by keyword
            - help: Show this help message
            - bye: Exit the app
            """;
        ui.showMessage(helpMessage);
    }
}
