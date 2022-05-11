package managers;

import items.Task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    /***
     * Constructor
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /***
     * Methods
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public void setTaskName(Task task, String name) {
        task.setName(name);
    }

    public void setTaskDescription(Task task, String description) {
        task.setDescription(description);
    }

}
