package oguricap.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testMarkAndUnmark() {
        Task t = new Todo("Test task");
        assertFalse(t.isDone());
        t.markAsDone();
        assertTrue(t.isDone());
        t.markNotDone();
        assertFalse(t.isDone());
    }

    @Test
    public void testToString() {
        Task t = new Todo("Test task");
        String str = t.toString();
        assertTrue(str.contains("Test task"));
    }
}
