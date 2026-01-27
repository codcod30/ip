package oguricap;

import oguricap.task.*;
import oguricap.exception.DukeException;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private static final String DATA_DIR = "data";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            File file = new File(filePath);
            if (!file.exists()) return tasks;

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

    private Task parseTaskFromFile(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            Todo todo = new Todo(parts[2]);
            if (parts[1].equals("1")) todo.markAsDone();
            return todo;
        case "D":
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (parts[1].equals("1")) deadline.markAsDone();
            return deadline;
        case "E":
            Event event = new Event(parts[2], parts[3], parts[4]);
            if (parts[1].equals("1")) event.markAsDone();
            return event;
        default:
            throw new DukeException("Corrupted task data.");
        }
    }

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
