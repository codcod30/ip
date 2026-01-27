package oguricap.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DukeExceptionTest {

    @Test
    public void testExceptionMessage() {
        DukeException e = new DukeException("Error!");
        assertEquals("Error!", e.getMessage());
    }
}
