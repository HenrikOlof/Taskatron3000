package managers;

import items.Task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    /***
     * Constructor
     */
    public TaskManager() {

    }

    /***
     * Methods
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

}
