package oguricap.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToFileString() {
        Todo t = new Todo("Read book");
        assertEquals("T | 0 | Read book", t.toFileString());
        t.markAsDone();
        assertEquals("T | 1 | Read book", t.toFileString());
    }
}
