import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDate fromDate;
    private LocalDate toDate;

    public Event(String description, String from, String to) throws DukeException{
        super(description);
        try {
            this.fromDate = LocalDate.parse(from);
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Use yyyy-MM-dd for /from and /to");
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
        return "E | " + (isDone ? "1" : "0") + " | " + description +
                " | " + fromDate.toString() + " | " + toDate.toString();
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
