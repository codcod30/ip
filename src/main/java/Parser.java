public class Parser {

    public static void processInput(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ", 2);
        String cmd = parts[0];

        switch (cmd) {
        case "bye":
            ui.showMessage("Bye. I'm ready to hit the sack.");
            System.exit(0);
            break;

        case "list":
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.get(i));
            }
            break;

        case "todo":
            handleTodo(parts, tasks, ui, storage);
            break;

        case "deadline":
            handleDeadline(parts, tasks, ui, storage);
            break;

        case "event":
            handleEvent(parts, tasks, ui, storage);
            break;

        case "mark":
            handleMark(parts, tasks, ui, storage);
            break;

        case "unmark":
            handleUnmark(parts, tasks, ui, storage);
            break;

        case "delete":
            handleDelete(parts, tasks, ui, storage);
            break;

        default:
            throw new DukeException("Ah, sorry. That's not a command.");
        }
    }

    public static void handleTodo(String[] parts, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new DukeException("Hm... Add a description to the todo.");
        }

        Todo todo = new Todo(parts[1].trim());
        tasks.add(todo);
        storage.save(tasks.getTasks());

        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + todo);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String[] parts, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (parts.length < 2 || parts[1].isBlank()) {
            ui.showError("Hm... Add a description to the deadline.");
            return;
        }
        String[] deadlineParts = parts[1].split("/by", 2);
        if (deadlineParts.length < 2) {
            ui.showError("Hm... Deadline must have /by.");
            return;
        }
        Deadline deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        tasks.add(deadline);
        storage.save(tasks.getTasks());
        ui.showTask("Got it. I've added this task:", deadline);
        ui.showTaskCount(tasks.size());
    }

    private static void handleEvent(String[] parts, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (parts.length < 2 || parts[1].isBlank()) {
            ui.showError("Hm... Add a description to the event.");
            return;
        }
        String[] fromSplit = parts[1].split("/from", 2);
        if (fromSplit.length < 2) {
            ui.showError("Hm... Event must have /from.");
            return;
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            ui.showError("Hm... Event must have /to.");
            return;
        }
        Event event = new Event(fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim());
        tasks.add(event);
        storage.save(tasks.getTasks());
        ui.showTask("Got it. I've added this task:", event);
        ui.showTaskCount(tasks.size());
    }

    private static void handleMark(String[] parts, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = parseIndex(parts, tasks.size());
        tasks.get(index).markAsDone();
        storage.save(tasks.getTasks());
        ui.showTask("Nice! I've marked this task as done:", tasks.get(index));
    }

    private static void handleUnmark(String[] parts, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = parseIndex(parts, tasks.size());
        tasks.get(index).markNotDone();
        storage.save(tasks.getTasks());
        ui.showTask("OK, I've marked this task as not done yet:", tasks.get(index));
    }

    private static void handleDelete(String[] parts, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = parseIndex(parts, tasks.size());
        Task removed = tasks.remove(index);
        storage.save(tasks.getTasks());
        ui.showTask("Noted. I've removed this task:", removed);
        ui.showTaskCount(tasks.size());
    }

    private static int parseIndex(String[] parts, int size) throws DukeException {
        if (parts.length < 2) throw new DukeException("Please specify a task number.");
        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number.");
        }
        if (index < 0 || index >= size) throw new DukeException("Task number out of range.");
        return index;
    }
}
