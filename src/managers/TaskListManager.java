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
    public ArrayList<TaskList> getAllLists() {
        return lists;
    }

    public void createList(String name) {
        TaskList list = new TaskList(name);
        lists.add(list);
    }

    public void createAndAddTask(TaskList list, String name, String description) {
        Task task = new Task(name, description);
        list.addTask(task);
    }

    public void removeTaskFromList() {

    }

    public void removeList() {

    }

    public void moveTaskToList() {

    }

    public TaskList getListByIndex(int index) {
        try {
            return lists.get(index);
        } catch (Exception e) {
            System.out.println("No task found with that index number.");
            return null;
        }
    }
}
