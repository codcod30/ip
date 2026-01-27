package oguricap;

import oguricap.command.*;
import oguricap.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
