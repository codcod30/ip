package oguricap;

import oguricap.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addAndGetTask() {
        TaskList list = new TaskList();
        list.add(new Todo("study"));

        assertEquals("study", list.get(0).getDescription());
    }

    @Test
    void removeTask_removesCorrectTask() {
        TaskList list = new TaskList();
        list.add(new Todo("A"));
        list.add(new Todo("B"));

        list.remove(0);
        assertEquals("B", list.get(0).getDescription());
    }
}
