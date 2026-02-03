package oguricap;

import java.util.ArrayList;
import java.util.Scanner;

import oguricap.task.Task;

/**
 * Handles user interface interactions, including displaying messages and reading input.
 * Supports both CLI (System.out) and GUI (via getLastOutput).
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private final String line = "    ____________________________________________________________";
    private final String spacing = "     ";
    private StringBuilder outputBuffer = new StringBuilder();

    public Ui() {}

    /** Clears the output buffer before generating a new response */
    public void clearOutput() {
        outputBuffer.setLength(0);
    }

    /** Returns the last output as a string for GUI */
    public String getLastOutput() {
        return outputBuffer.toString().trim();
    }

    /**
     * Adds a line to the output buffer (for CLI & GUI)
     */
    private void append(String message) {
        outputBuffer.append(message).append("\n");
        System.out.println(message); // keep CLI output
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        append(line);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        append(spacing + "Hello! I'm Oguri Cap");
        append(spacing + "All right, what's first on today's agenda?");
    }

    /**
     * Displays a loading error message.
     */
    public void showLoadingError() {
        append(spacing + "Warning: Could not load saved tasks.");
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        append(spacing + message);
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void showAddTask(Task task, int size) {
        append(spacing + "Got it. I've added this task:");
        append(spacing + "  " + task);
        append(spacing + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void showDeletedTask(Task task, int size) {
        append(spacing + "Noted. I've removed this task:");
        append(spacing + "  " + task);
        append(spacing + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTask(Task task) {
        append(spacing + "Nice! I've marked this task as done:");
        append(spacing + "  " + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        append(spacing + "OK, I've marked this task as not done yet:");
        append(spacing + "  " + task);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The TaskList containing all tasks.
     */
    public void showTaskList(TaskList tasks) {
        append(spacing + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            append(spacing + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays the list of found tasks matching a search query.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            append(spacing + "No matching tasks found.");
            return;
        }

        append(spacing + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            append(spacing + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays the exit message.
     */
    public void showExit() {
        append(spacing + "Bye. I'm ready to hit the sack.");
    }
}
