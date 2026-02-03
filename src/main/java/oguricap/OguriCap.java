package oguricap;

import oguricap.command.Command;
import oguricap.exception.DukeException;

/**
 * Core logic of the chatbot.
 */
public class OguriCap {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes OguriCap with the specified file path for storage.
     */
    public OguriCap(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /** Returns the response string for GUI */
    public String getResponse(String input) {
        ui.clearOutput();
        if (tasks == null || ui == null) return "";

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return ui.getLastOutput();
    }

    // helper method to check if input is exit
    public boolean isExitCommand(String input) {
        return input.trim().equals("bye");
    }

    /** Returns the welcome message for GUI */
    public String getWelcomeMessage() {
        ui.clearOutput();
        ui.showWelcome();
        return ui.getLastOutput();
    }
}
