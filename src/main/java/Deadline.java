import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate byDate;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Use yyyy-MM-dd");
        }
    }

    public LocalDate getByDate() {
        return byDate;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
