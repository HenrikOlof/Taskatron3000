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

    public void deleteTask(int taskId) {
        dbHelper.deleteTask(taskId);
    }

    public boolean checkIfTaskExistsByIndex(int index) {
        ArrayList<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            if (task.getId() == index) return true;
        }
        return false;
    }
}
