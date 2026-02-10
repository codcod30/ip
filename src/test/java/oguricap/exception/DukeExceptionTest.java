package oguricap.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {

    @Test
    public void testExceptionMessage() {
        DukeException e = new DukeException("Error!");
        assertEquals("Error!", e.getMessage());
    }
}
