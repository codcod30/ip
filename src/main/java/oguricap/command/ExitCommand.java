package oguricap.command;

import oguricap.TaskList;
import oguricap.Ui;
import oguricap.Storage;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true since this is an exit command.
     */
    @Override
    public boolean isExit() { return true; }
}
