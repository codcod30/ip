package oguricap.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import oguricap.exception.DukeException;

public class Event extends Task {

    private LocalDate fromDate;
    private LocalDate toDate;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.fromDate = LocalDate.parse(from); // expects yyyy-MM-dd
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15)");
        }
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromDate + " | " + toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + fromDate.format(DISPLAY_FORMAT) +
                " to: " + toDate.format(DISPLAY_FORMAT) + ")";
    }
}
