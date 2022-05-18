package managers;

import helpers.DatabaseHelper;
import items.Task;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskManager {
    private DatabaseHelper dbHelper;

    /***
     * Constructor
     */
    public TaskManager(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /***
     * Methods
     */
    public ArrayList<Task> getAllTasks() {
        return dbHelper.getAllTasks();
    }

    public int createTask(String name, String description) throws SQLException {
        return dbHelper.enterTaskIntoDB(name, description);
    }

    public void setTaskName(int taskId, String name) {
        dbHelper.setTaskName(taskId, name);
    }

    public void setTaskDescription(int taskId, String description) {
        dbHelper.setTaskDescription(taskId, description);
    }

    public void printAllTasks() {
        ArrayList<Task> allTasks = dbHelper.getAllTasks();
        if (allTasks.size() == 0) {
            System.out.println("The table 'tasks' seems to be empty.");
        } else {
            for (Task task : allTasks) {
                System.out.println(task);
            }
        }
    }

    public void printTasksForList(int taskListId) {
        for (Task task : dbHelper.getAllTasksInList(taskListId)) {
            System.out.println(task);
        }
    }

    public void deleteTask(int taskId) {
        dbHelper.deleteTask(taskId);
    }
}
