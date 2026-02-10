package oguricap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import oguricap.command.Command;
import oguricap.command.MarkCommand;
import oguricap.exception.DukeException;

class ParserTest {

    @Test
    void parse_markCommand() throws DukeException {
        Command c = Parser.parse("mark 1");
        assertTrue(c instanceof MarkCommand);
    }

    @Test
    void parse_invalidCommand_throwsException() {
        assertThrows(DukeException.class, () -> Parser.parse("unknown"));
    }

    @Test
    void parse_deadlineMissingBy_throwsException() {
        assertThrows(DukeException.class, () ->
                Parser.parse("deadline read book"));
    }
}
