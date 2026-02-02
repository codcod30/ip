package oguricap.command;

import java.util.ArrayList;

import oguricap.Storage;
import oguricap.TaskList;
import oguricap.Ui;
import oguricap.exception.DukeException;
import oguricap.task.Task;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matches = tasks.find(keyword);
        ui.showFoundTasks(matches);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
