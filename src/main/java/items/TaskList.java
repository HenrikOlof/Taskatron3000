package items;

import java.util.ArrayList;

public class TaskList {
    private String name;
    private ArrayList<Task> tasks;

    /***
     * Constructor
     */
    public TaskList(String name) {
        setName(name);
        tasks = new ArrayList<>();
    }

    /***
     * Methods
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /***
     * Getters and setters
     */
    public void setName(String newName){
        name = newName;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTaskByIndex(int index) {
        try {
            return tasks.get(index);
        } catch (Exception e) {
            System.out.println("No task with index " + index + " was found for list " + getName());
            return null;
        }
    }

    public String toString() {
        // TODO
        return getName();
    }

}
