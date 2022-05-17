package items;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private String description;
    private int taskListId;

    /***
     * Constructors
     */
    public Task(int id, String name, String description, int taskListId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.taskListId = taskListId;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTaskListId() {
        return taskListId;
    }

    public String toString() {
        return getId() + ") name: " + getName() + ", Description: " + getDescription() +
                ". Belongs to list " + getTaskListId();
    }
}
