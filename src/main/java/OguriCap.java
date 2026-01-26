import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;
import java.nio.file.*;

public class OguriCap {

    static String line = "    ____________________________________________________________"; // 4 spaces for line indentation
    static String spacing = "     "; // 5 spaces for indentation for bot responses

    static final String DATA_DIR = "data";
    static final String DATA_FILE = Paths.get(DATA_DIR, "oguri_cap_tasks.txt").toString();

    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        String chatBotName = "Oguri Cap";

        System.out.println(line);
        System.out.println(spacing + "Hello! I'm " + chatBotName);
        System.out.println(spacing + "All right, what's first on today's agenda?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        try {
            loadTasks();
        } catch (DukeException e) {
            System.out.println(spacing + "Warning: Could not load saved tasks.");
        }

        try {
            while (true) {
                String input = scanner.nextLine().trim();

                try {
                    processInput(input);
                } catch (DukeException e) {
                    System.out.println(line);
                    System.out.println(spacing + e.getMessage());
                    System.out.println(line);
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static void processInput(String input) throws DukeException {
        String[] taskParts = input.split(" ", 2);
        String taskCmd = taskParts[0];

        if (input.equals("bye")) {
            System.out.println(line);
            System.out.println(spacing + "Bye. I'm ready to hit the sack.");
            System.out.println(line);
            System.exit(0);
        }

        if (input.equals("list")) {
            System.out.println(line);
            System.out.println(spacing + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(spacing + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(line);
            return;
        }

        if (input.startsWith("mark ")) {
            handleMark(taskParts);
            return;
        }

        if (input.startsWith("unmark ")) {
            handleUnmark(taskParts);
            return;
        }

        if (input.startsWith("delete ")) {
            handleDelete(taskParts);
            return;
        }

        switch (taskCmd) {
        case "todo":
            handleTodo(taskParts);
            break;
        case "deadline":
            handleDeadline(taskParts);
            break;
        case "event":
            handleEvent(taskParts);
            break;
        default:
            throw new DukeException("Ah,sorry. That's not a command.");
        }
    }

    private static void handleDelete(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please specify a task number to delete.");
        }
        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number.");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Ooo... Task number out of range.");
        }
        Task removedTask = tasks.remove(index);

        try {
            saveTasks();
        } catch (DukeException e) {
            System.out.println(spacing + "Warning: Could not save tasks.");
        }

        System.out.println(line);
        System.out.println(spacing + "Noted. I've removed this task:");
        System.out.println(spacing + "  " + removedTask);
        System.out.println(spacing + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    private static void handleTodo(String[] parts) {
        if (parts.length < 2 || parts[1].isBlank()) {
            System.out.println(line);
            System.out.println(spacing + "Hm... Add a description to the todo.");
            System.out.println(line);
            return;
        }
        Todo todo = new Todo(parts[1].trim());
        addTask(todo);
    }

    private static void handleDeadline(String[] parts) {
        if (parts.length < 2 || parts[1].isBlank()) {
            System.out.println(line);
            System.out.println(spacing + "Hm... Add a description to the deadline.");
            System.out.println(line);
            return;
        }
        String[] deadlineParts = parts[1].split("/by", 2);
        if (deadlineParts.length < 2) {
            System.out.println(line);
            System.out.println(spacing + "Hm... Deadline must have /by.");
            System.out.println(line);
            return;
        }
        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        try {
            Deadline deadline = new Deadline(description, by);
            addTask(deadline);
        } catch (DukeException e) {
            System.out.println(line);
            System.out.println(spacing + e.getMessage());
            System.out.println(line);
        }
    }

    private static void handleEvent(String[] parts) {
        if (parts.length < 2 || parts[1].isBlank()) {
            System.out.println(line);
            System.out.println(spacing + "Hm... Add a description to the event.");
            System.out.println(line);
            return;
        }
        String[] fromSplit = parts[1].split("/from", 2);
        if  (fromSplit.length < 2) {
            System.out.println(line);
            System.out.println(spacing + "Hm... Event must have /from.");
            System.out.println(line);
            return;
        }
        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            System.out.println(line);
            System.out.println(spacing + "Hm... Event must have /to.");
            System.out.println(line);
            return;
        }
        String from = toSplit[0].trim();
        String to = toSplit[1].trim();
        try {
            Event event = new Event(description, from, to);
            addTask(event);
        } catch (DukeException e) {
            System.out.println(line);
            System.out.println(spacing + e.getMessage());
            System.out.println(line);
        }
    }

    private static void handleMark(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Oh... Please specify a task number to mark.");
        }

        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Oh... Please provide a valid task number.");
        }

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Oh... Task number out of range.");
        }

        tasks.get(index).markAsDone();

        try {
            saveTasks();
        } catch (DukeException e) {
            System.out.println(spacing + "Warning: Could not save tasks.");
        }

        System.out.println(line);
        System.out.println(spacing + "Nice! I've marked this task as done:");
        System.out.println(spacing + "  " + tasks.get(index));
        System.out.println(line);
    }

    private static void handleUnmark(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Oh... Please specify a task number to unmark.");
        }

        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Oh... Please provide a valid task number.");
        }

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Oh... Task number out of range.");
        }

        tasks.get(index).markNotDone();

        try {
            saveTasks();
        } catch (DukeException e) {
            System.out.println(spacing + "Warning: Could not save tasks.");
        }

        System.out.println(line);
        System.out.println(spacing + "OK, I've marked this task as not done yet:");
        System.out.println(spacing + "  " + tasks.get(index));
        System.out.println(line);
    }

    private static void addTask(Task task) {
        tasks.add(task);
        try {
            saveTasks();
        } catch (DukeException e) {
            System.out.println(spacing + "Warning: Could not save tasks.");
        }
        System.out.println(line);
        System.out.println(spacing + "Got it. I've added this task:");
        System.out.println(spacing + "  " + task);
        System.out.println(spacing + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    private static void ensureDataDirectoryExists() throws IOException {
        Files.createDirectories(Paths.get(DATA_DIR));
    }

    private static void loadTasks() throws DukeException {
        try {
            ensureDataDirectoryExists();
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                try {
                    Task task = parseTaskFromFile(line);
                    tasks.add(task);
                } catch (DukeException e) {
                    System.out.println(spacing + "Warning: Skipping corrupted task line.");
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Failed to load tasks from disk.");
        }
    }

    private static Task parseTaskFromFile(String line) throws DukeException {
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

    private static void saveTasks() throws DukeException {
        try {
            ensureDataDirectoryExists();

            FileWriter fw = new FileWriter(DATA_FILE);
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            throw new DukeException("Failed to save tasks: " + e.getMessage());
        }
    }
}
