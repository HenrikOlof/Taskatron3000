package managers;

import items.Task;
import items.TaskList;

import java.util.ArrayList;

public class TaskListManager {
    private ArrayList<TaskList> lists;

    /***
     * Constructor
     */
    public TaskListManager() {

    }

    /***
     * Methods
     */
    public ArrayList<TaskList> viewAllTaskLists() {
        return lists;
    }

    public void createAndAddTask(TaskList list, String name, String description) {
        Task task = new Task(name, description);
        list.addTask(task);
    }

    public void removeTask() {

    }

    public void removeList() {

    }

    public void moveTask() {

    }
}
