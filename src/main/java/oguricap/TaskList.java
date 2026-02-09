package oguricap;

import java.util.ArrayList;
import java.util.stream.Collectors;

import oguricap.task.Task;

/**
 * Represents a list of tasks managed by the chatbot.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        assert tasks != null : "Task list should never be null";
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
        assert task != null : "Task list should never be null";
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
        assert index >= 0 : "Index should be greater than 0";
        assert index < tasks.size() : "Index must be within list size";
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
        assert tasks != null : "Task list should never be null";
        return this.tasks;
    }

    /**
     * Finds and returns tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.toString().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Checks if the given index is valid for the task list.
     *
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
