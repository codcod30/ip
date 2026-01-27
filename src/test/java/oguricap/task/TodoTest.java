package oguricap.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testToFileString() {
        Todo t = new Todo("Read book");
        assertEquals("T | 0 | Read book", t.toFileString());
        t.markAsDone();
        assertEquals("T | 1 | Read book", t.toFileString());
    }
}
