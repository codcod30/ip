package oguricap.command;

import oguricap.TaskList;
import oguricap.Ui;
import oguricap.Storage;
import oguricap.exception.DukeException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
