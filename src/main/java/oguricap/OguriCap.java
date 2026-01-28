package oguricap;

import oguricap.command.Command;
import oguricap.exception.DukeException;

/**
 * The main class for the chatbot application.
 * Handles initialization and runs the main program loop.
 */
public class OguriCap {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public OguriCap(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main interaction loop for the chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to start the chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new OguriCap("data/oguri_cap_tasks.txt").run();
    }
}
