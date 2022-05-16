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
    public Task(String name, String description, int taskListId) {
        setName(name);
        setDescription(description);
        setTaskList(taskListId);
    }

    /***
     * Getters and setters
     */
    public void setName(String newName) {
        name = newName;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public void setTaskList(int listId) {
        taskListId = listId;
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        String task = "Name: " + getName() + ", Description: " + getDescription();
        return task;
    }
}
