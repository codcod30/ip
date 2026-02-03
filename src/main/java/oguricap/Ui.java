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

    /** Adds a line to the output buffer (for CLI & GUI) */
    private void append(String message) {
        outputBuffer.append(message).append("\n");
        System.out.println(message); // keep CLI output
    }

    public void showLine() {
        append(line);
    }

    public void showWelcome() {
        append(spacing + "Hello! I'm Oguri Cap");
        append(spacing + "All right, what's first on today's agenda?");
    }

    public void showLoadingError() {
        append(spacing + "Warning: Could not load saved tasks.");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        append(spacing + message);
    }

    public void showAddTask(Task task, int size) {
        append(spacing + "Got it. I've added this task:");
        append(spacing + "  " + task);
        append(spacing + "Now you have " + size + " tasks in the list.");
    }

    public void showDeletedTask(Task task, int size) {
        append(spacing + "Noted. I've removed this task:");
        append(spacing + "  " + task);
        append(spacing + "Now you have " + size + " tasks in the list.");
    }

    public void showMarkedTask(Task task) {
        append(spacing + "Nice! I've marked this task as done:");
        append(spacing + "  " + task);
    }

    public void showUnmarkedTask(Task task) {
        append(spacing + "OK, I've marked this task as not done yet:");
        append(spacing + "  " + task);
    }

    public void showTaskList(TaskList tasks) {
        append(spacing + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            append(spacing + (i + 1) + ". " + tasks.get(i));
        }
    }

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

    public void showExit() {
        append(spacing + "Bye. I'm ready to hit the sack.");
    }
}
