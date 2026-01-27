package oguricap.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import oguricap.exception.DukeException;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation() throws DukeException {
        Deadline d = new Deadline("Return book", "2019-12-02");
        assertEquals(LocalDate.of(2019, 12, 2), d.getByDate());
    }

    @Test
    public void testToFileStringAndToString() throws DukeException {
        Deadline d = new Deadline("Return book", "2019-12-02");
        String fileStr = d.toFileString();
        assertTrue(fileStr.contains("D | 0 | Return book | 2019-12-02"));
        String str = d.toString();
        assertTrue(str.contains("Return book") && str.contains("Dec 2 2019"));
    }
}
