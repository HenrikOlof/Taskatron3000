package items;

import java.util.ArrayList;

public class TaskList {
    private String name;
    private ArrayList<Task> tasks;

    /***
     * Constructor
     */
    public TaskList(String name) {
        this.name = name;
    }

    /***
     * Methods
     */
    public void addTask(Task task) {
        tasks.add(task);
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

}
