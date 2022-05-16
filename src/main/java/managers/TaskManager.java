package managers;

import helpers.DatabaseHelper;
import items.Task;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private DatabaseHelper dbHelper;

    /***
     * Constructor
     */
    public TaskManager(DatabaseHelper dbHelper) {
        tasks = new ArrayList<>();
        this.dbHelper = dbHelper;
    }

    /***
     * Methods
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public void createTask(String name, String description) throws SQLException {
        dbHelper.enterTaskIntoDB(name, description);
    }

    public void setTaskName(Task task, String name) {
        task.setName(name);
    }

    public void setTaskDescription(Task task, String description) {
        task.setDescription(description);
    }

    public void printAllTasks() {
        ArrayList<String> allTasksDetails = dbHelper.getAllTasks();
        if (allTasksDetails.size() == 0) {
            System.out.println("The table 'tasks' seems to be empty.");
        } else {
            for (String list : allTasksDetails) {
                System.out.println(list);
            }
        }
    }

}
