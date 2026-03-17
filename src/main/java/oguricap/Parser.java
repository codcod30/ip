package oguricap;

import oguricap.command.AddCommand;
import oguricap.command.Command;
import oguricap.command.DeleteCommand;
import oguricap.command.ExitCommand;
import oguricap.command.FindCommand;
import oguricap.command.HelpCommand;
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
        assert input != null : "Input cannot be null";
        assert !input.isBlank() : "Input cannot be blank";
        String[] parts = input.split(" ", 2);
        assert parts.length >= 1 : "There should be at least one part in the input";
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
            checkArgument(parts, "unmark");
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number for unmark: " + parts[1]);
            }
        case "delete":
            checkArgument(parts, "delete");
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number to delete: " + parts[1]);
            }
        case "todo":
            checkArgument(parts, "todo");
            return new AddCommand(new Todo(parts[1]));
        case "deadline":
            return parseDeadline(parts);
        case "event":
            return parseEvent(parts);
        case "find":
            checkArgument(parts, "find");
            return new FindCommand(parts[1]);
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("Hmm...Unknown command: " + input);
        }
    }

    /**
     * Parses a deadline command and returns the corresponding AddCommand with a Deadline task.
     * @param parts Input parts split by space.
     * @return An AddCommand containing the Deadline task.
     * @throws DukeException If the input format is invalid or required fields are missing.
     */
    private static Command parseDeadline(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("The deadline command requires a description and /by DATE.");
        }

        String[] dlParts = parts[1].split("/by", 2);

        if (dlParts.length < 2) {
            throw new DukeException("Deadline must be in format: deadline DESCRIPTION /by DATE");
        }

        String description = dlParts[0].trim();
        String by = dlParts[1].trim();

        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        if (by.isEmpty()) {
            throw new DukeException("The deadline date cannot be empty.");
        }

        return new AddCommand(new Deadline(description, by));
    }

    /**
     * Parses an event command and returns the corresponding AddCommand with an Event task.
     * @param parts Input parts split by space.
     * @return An AddCommand containing the Event task.
     * @throws DukeException If the input format is invalid or required fields are missing.
     */
    private static Command parseEvent(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("The event command requires a description, /from and /to.");
        }

        String[] evParts = parts[1].split("/from", 2);

        if (evParts.length < 2) {
            throw new DukeException("Event must be in format: event DESCRIPTION /from START /to END");
        }

        String description = evParts[0].trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String[] toParts = evParts[1].split("/to", 2);
        if (toParts.length < 2) {
            throw new DukeException("Event must have /to");
        }

        String from = toParts[0].trim();
        String to = toParts[1].trim();

        if (from.isEmpty()) {
            throw new DukeException("The event start date cannot be empty.");
        }

        if (to.isEmpty()) {
            throw new DukeException("The event end date cannot be empty.");
        }

        return new AddCommand(new Event(description, from, to));
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

