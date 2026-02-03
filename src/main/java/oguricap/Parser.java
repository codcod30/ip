package oguricap;

import oguricap.command.AddCommand;
import oguricap.command.Command;
import oguricap.command.DeleteCommand;
import oguricap.command.ExitCommand;
import oguricap.command.FindCommand;
import oguricap.command.ListCommand;
import oguricap.command.MarkCommand;
import oguricap.command.UnmarkCommand;
import oguricap.exception.DukeException;
import oguricap.task.Deadline;
import oguricap.task.Event;
import oguricap.task.Todo;

/**
 * Parses user input strings and converts them into executable Command objects.
 */
public class Parser {

    /**
     * Parses the given input string and returns the corresponding Command object.
     *
     * @param input Full user input string.
     * @return The Command object representing the user's command.
     * @throws DukeException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String cmd = parts[0];

        switch (cmd) {
        case "bye": return new ExitCommand();
        case "list": return new ListCommand();
        case "mark":
            checkArgument(parts, "mark");
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number for mark: " + parts[1]);
            }
        case "unmark":
            checkArgument(parts, "mark");
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number for mark: " + parts[1]);
            }
        case "delete":
            checkArgument(parts, "mark");
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number for mark: " + parts[1]);
            }
        case "todo":
            checkArgument(parts, "todo");
            return new AddCommand(new Todo(parts[1]));
        case "deadline":
            checkArgument(parts, "deadline");
            String[] dlParts = parts[1].split("/by", 2);
            if (dlParts.length < 2) {
                throw new DukeException("Deadline must have /by");
            }
            return new AddCommand(new Deadline(dlParts[0].trim(), dlParts[1].trim()));
        case "event":
            checkArgument(parts, "event");
            String[] evParts = parts[1].split("/from", 2);
            if (evParts.length < 2) {
                throw new DukeException("Event must have /from");
            }
            String[] toParts = evParts[1].split("/to", 2);
            if (toParts.length < 2) {
                throw new DukeException("Event must have /to");
            }
            return new AddCommand(new Event(evParts[0].trim(), toParts[0].trim(), toParts[1].trim()));
        case "find":
            checkArgument(parts, "find");
            return new FindCommand(parts[1]);
        default:
            throw new DukeException("Hmm...Unknown command: " + cmd);
        }
    }

    /**
     * Checks if the command has the required argument.
     *
     * @param parts Input parts split by space.
     * @param cmd Command name for error message.
     * @throws DukeException If the argument is missing or blank.
     */
    private static void checkArgument(String[] parts, String cmd) throws DukeException {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new DukeException("The " + cmd + " command requires an argument.");
        }
    }
}
