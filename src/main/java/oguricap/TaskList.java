package oguricap;

import oguricap.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of tasks managed by the chatbot.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes the task list with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list by its index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

<<<<<<< HEAD
    /**
     * Checks if the given index is valid for the task list.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
=======
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                results.add(task);
            }
        }
        return results;
    }


>>>>>>> branch-A-CodingStandard
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
