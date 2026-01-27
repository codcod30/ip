package oguricap.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import oguricap.exception.DukeException;

public class EventTest {

    @Test
    public void testEventCreation() throws DukeException {
        Event e = new Event("Project meeting", "2019-08-06", "2019-08-06");
        assertEquals(LocalDate.of(2019, 8, 6), e.getFromDate());
        assertEquals(LocalDate.of(2019, 8, 6), e.getToDate());
    }

    @Test
    public void testToFileStringAndToString() throws DukeException {
        Event e = new Event("Project meeting", "2019-08-06", "2019-08-06");
        String fileStr = e.toFileString();
        assertTrue(fileStr.contains("E | 0 | Project meeting | 2019-08-06 | 2019-08-06"));
        String str = e.toString();
        assertTrue(str.contains("Project meeting") && str.contains("Aug 6 2019"));
    }
}
