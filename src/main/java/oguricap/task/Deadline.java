package oguricap.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import oguricap.exception.DukeException;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {

    private LocalDate byDate;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the task.
     * @param by The due date in yyyy-MM-dd format.
     * @throws DukeException If the date format is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15)");
        }
    }

    /**
     * Gets the due date of the deadline.
     *
     * @return The due date as a LocalDate.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Returns the string representation of the deadline for file storage.
     *
     * @return The formatted string for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DISPLAY_FORMAT) + ")";
    }
}