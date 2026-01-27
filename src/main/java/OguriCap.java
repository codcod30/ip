import java.util.Scanner;

public class OguriCap {

    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage("data/oguri_cap_tasks.txt");
        TaskList tasks;

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError("Warning: Could not load saved tasks.");
            tasks = new TaskList();
        }

        ui.showWelcome("Oguri Cap");
        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine().trim();
            try {
                Parser.processInput(input, tasks, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
