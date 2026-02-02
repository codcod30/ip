package oguricap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import oguricap.exception.DukeException;
import oguricap.task.Deadline;
import oguricap.task.Event;
import oguricap.task.Task;
import oguricap.task.Todo;

/**
 * Handles loading and saving tasks to and from the disk.
 */
public class Storage {

    private static final String DATA_DIR = "data";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by filePath.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws DukeException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseTaskFromFile(line));
            }
            scanner.close();
        } catch (IOException e) {
            throw new DukeException("Failed to load tasks from disk.");
        }
        return tasks;
    }

    /**
     * Parses a line from the file into a Task object.
     *
     * @param line The line from the file representing a task.
     * @return The corresponding Task object.
     * @throws DukeException If the task data is corrupted.
     */
    private Task parseTaskFromFile(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            Todo todo = new Todo(parts[2]);
            if (parts[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (parts[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            Event event = new Event(parts[2], parts[3], parts[4]);
            if (parts[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        default:
            throw new DukeException("Corrupted task data.");
        }
    }

    /**
     * Saves the list of tasks to the file specified by filePath.
     *
     * @param tasks The ArrayList of Task objects to be saved.
     * @throws DukeException If there is an error saving.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks.");
        }
    }
}
