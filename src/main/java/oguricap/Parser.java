package oguricap;

import oguricap.command.Command;
import oguricap.command.AddCommand;
import oguricap.command.DeleteCommand;
import oguricap.command.ExitCommand;
import oguricap.command.ListCommand;
import oguricap.command.MarkCommand;
import oguricap.command.UnmarkCommand;

import oguricap.task.Deadline;
import oguricap.task.Event;
import oguricap.task.Todo;

import oguricap.exception.DukeException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String cmd = parts[0];

        switch (cmd) {
        case "bye": return new ExitCommand();
        case "list": return new ListCommand();
        case "mark":
            checkArgument(parts, "mark");
            return new MarkCommand(Integer.parseInt(parts[1]) - 1);
        case "unmark":
            checkArgument(parts, "unmark");
            return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);
        case "delete":
            checkArgument(parts, "delete");
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
        case "todo":
            checkArgument(parts, "todo");
            return new AddCommand(new Todo(parts[1]));
        case "deadline":
            checkArgument(parts, "deadline");
            String[] dlParts = parts[1].split("/by", 2);
            if (dlParts.length < 2) throw new DukeException("Deadline must have /by");
            return new AddCommand(new Deadline(dlParts[0].trim(), dlParts[1].trim()));
        case "event":
            checkArgument(parts, "event");
            String[] evParts = parts[1].split("/from", 2);
            if (evParts.length < 2) throw new DukeException("Event must have /from");
            String[] toParts = evParts[1].split("/to", 2);
            if (toParts.length < 2) throw new DukeException("Event must have /to");
            return new AddCommand(new Event(evParts[0].trim(), toParts[0].trim(), toParts[1].trim()));
        default:
            throw new DukeException("Hmm...Unknown command: " + cmd);
        }
    }

    private static void checkArgument(String[] parts, String cmd) throws DukeException {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new DukeException("The " + cmd + " command requires an argument.");
        }
    }
}
