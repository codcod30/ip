package oguricap;

import java.util.Scanner;
import oguricap.task.Task;

import java.util.ArrayList;

<<<<<<< HEAD
/**
 * Handles user interface interactions, including displaying messages and reading input.
 */
=======
>>>>>>> branch-Level-9
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private final String line = "    ____________________________________________________________";
    private final String spacing = "     ";

    /**
     * Displays a horizontal line for formatting.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println(spacing + "Hello! I'm Oguri Cap");
        System.out.println(spacing + "All right, what's first on today's agenda?");
        System.out.println(line);
    }

    /**
     * Displays an error message when loading saved tasks fails.
     */
    public void showLoadingError() {
        System.out.println(spacing + "Warning: Could not load saved tasks.");
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command entered by the user as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(spacing + message);
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void showAddTask(Task task, int size) {
        System.out.println(spacing + "Got it. I've added this task:");
        System.out.println(spacing + "  " + task);
        System.out.println(spacing + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming the deletion of a task.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void showDeletedTask(Task task, int size) {
        System.out.println(spacing + "Noted. I've removed this task:");
        System.out.println(spacing + "  " + task);
        System.out.println(spacing + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMarkedTask(Task task) {
        System.out.println(spacing + "Nice! I've marked this task as done:");
        System.out.println(spacing + "  " + task);
    }

    /**
     * Displays a message confirming that a task has been unmarked (set to not done).
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println(spacing + "OK, I've marked this task as not done yet:");
        System.out.println(spacing + "  " + task);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(spacing + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(spacing + (i + 1) + ". " + tasks.get(i));
        }
    }

<<<<<<< HEAD
    /**
     * Displays the exit message to the user.
     */
=======
>>>>>>> branch-Level-9
    public void showFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(spacing + "No matching tasks found.");
            return;
        }

        System.out.println(spacing + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(spacing + (i + 1) + ". " + tasks.get(i));
        }
    }

    public void showExit() {
        System.out.println(spacing + "Bye. I'm ready to hit the sack.");
    }
}