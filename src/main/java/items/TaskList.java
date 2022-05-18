package items;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private int id;
    private String name;

    /***
     * Constructor
     */
    public TaskList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return getId() + ") name: " + name;
    }

}
