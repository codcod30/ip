package oguricap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oguricap.task.Task;
import oguricap.task.Todo;



class StorageTest {

    private static final String TEST_FILE = "data/test.txt";

    @BeforeEach
    void cleanup() throws Exception {
        Files.deleteIfExists(Path.of(TEST_FILE));
    }

    @Test
    void saveAndLoad_tasksPersisted() throws Exception {
        Storage storage = new Storage(TEST_FILE);

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("test"));

        storage.save(tasks);
        ArrayList<Task> loaded = storage.load();

        assertEquals(1, loaded.size());
        assertEquals("test", loaded.get(0).getDescription());
    }
}
