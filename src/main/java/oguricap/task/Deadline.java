package oguricap.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import oguricap.exception.DukeException;

public class Deadline extends Task {

    private LocalDate byDate;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15)");
        }
    }

    public LocalDate getByDate() {
        return byDate;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DISPLAY_FORMAT) + ")";
    }
}