package netology.ru;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    Todos todos = new Todos();
    private final String argument1 = "Завтрак";
    private final String argument2 = "Обед";

    @Test
    void addTask() {
        todos.addTask(argument1);
        String expected = "Завтрак";
        String result = todos.getAllTasks();
        assertEquals(expected, result);
    }

    @Test
    void removeTask() {
        todos.addTask(argument1);
        todos.addTask(argument2);
        todos.removeTask(argument2);
        String expected = "Завтрак";
        String result = todos.getAllTasks();
        assertEquals(expected, result);
    }

    @Test
    void getAllTasks() {
        todos.addTask(argument1);
        todos.addTask(argument2);
        String expected = "Завтрак Обед";
        String result = todos.getAllTasks();
        assertEquals(expected, result);
    }

    @Test
    void restoreTask() {
        todos.addTask(argument1);
        todos.addTask(argument2);
        todos.removeTask(argument2);
        todos.restoreTask();
        String expected = "Завтрак Обед";
        String result = todos.getAllTasks();
        assertEquals(expected, result);
    }

}