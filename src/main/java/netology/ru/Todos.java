package netology.ru;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {

    private List<String> tasks = new ArrayList<>();
    private LinkedList<String> archiveTypes = new LinkedList<>();
    private LinkedList<String> archiveTasks = new LinkedList<>();

    public void addTask(String task) {

        if (tasks.size() < 7) {
            tasks.add(task);
            archiveTypes.add("ADD");
            archiveTasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
        archiveTypes.add("REMOVE");
        archiveTasks.add(task);
    }

    public void restoreTask() {
        if (archiveTypes.getLast().equals("REMOVE")) {
            addTask(archiveTasks.getLast());

        } else if (archiveTypes.getLast().equals("ADD")) {
            removeTask(archiveTasks.getLast());

        }
        archiveTypes.removeLast();
        archiveTasks.removeLast();
        archiveTypes.removeLast();
        archiveTasks.removeLast();
    }

    public String getAllTasks() {
        Collections.sort(tasks);
        String delim = " ";
        String allTasks = String.join(delim, tasks);
        return allTasks;
    }

}
