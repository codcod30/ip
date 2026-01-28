package oguricap.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import oguricap.exception.DukeException;

/**
 * Represents an event task with a description, start date, and end date.
 */
public class Event extends Task {

    private LocalDate fromDate;
    private LocalDate toDate;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs an Event task.
     *
     * @param description The description of the task.
     * @param from The start date in yyyy-MM-dd format.
     * @param to The end date in yyyy-MM-dd format.
     * @throws DukeException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.fromDate = LocalDate.parse(from); // expects yyyy-MM-dd
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15)");
        }
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date as a LocalDate object.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date as a LocalDate.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Returns the string representation of the event for file storage.
     *
     * @return The formatted string for file storage.
     */
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
