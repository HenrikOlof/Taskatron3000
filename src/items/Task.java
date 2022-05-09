package items;

public class Task {
    private String name;
    private String description;

    /***
     * Constructors
     */
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(String name) {
        this.name = name;
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

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }
}
