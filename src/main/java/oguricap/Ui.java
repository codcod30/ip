package oguricap;

import java.util.Scanner;
import oguricap.task.Task;

import java.util.ArrayList;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private final String line = "    ____________________________________________________________";
    private final String spacing = "     ";

    public void showLine() {
        System.out.println(line);
    }

    public void showWelcome() {
        System.out.println(line);
        System.out.println(spacing + "Hello! I'm Oguri Cap");
        System.out.println(spacing + "All right, what's first on today's agenda?");
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println(spacing + "Warning: Could not load saved tasks.");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        System.out.println(spacing + message);
    }

    public void showAddTask(Task task, int size) {
        System.out.println(spacing + "Got it. I've added this task:");
        System.out.println(spacing + "  " + task);
        System.out.println(spacing + "Now you have " + size + " tasks in the list.");
    }

    public void showDeletedTask(Task task, int size) {
        System.out.println(spacing + "Noted. I've removed this task:");
        System.out.println(spacing + "  " + task);
        System.out.println(spacing + "Now you have " + size + " tasks in the list.");
    }

    public void showMarkedTask(Task task) {
        System.out.println(spacing + "Nice! I've marked this task as done:");
        System.out.println(spacing + "  " + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println(spacing + "OK, I've marked this task as not done yet:");
        System.out.println(spacing + "  " + task);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(spacing + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(spacing + (i + 1) + ". " + tasks.get(i));
        }
    }

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