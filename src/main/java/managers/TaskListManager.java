package managers;

import helpers.DatabaseHelper;
import items.Task;
import items.TaskList;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskListManager {
    private ArrayList<TaskList> lists;
    private DatabaseHelper dbHelper;

    /***
     * Constructor
     */
    public TaskListManager(DatabaseHelper dbHelper) {
        lists = new ArrayList<>();
        this.dbHelper = dbHelper;
    }

    /***
     * Methods
     */
    public ArrayList<TaskList> getAllLists() {
        return lists;
    }

    public void createList(String name) {
        try {
            dbHelper.enterListIntoDB(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAndAddTaskToList(TaskManager taskMan, int taskListId, String name, String description) throws SQLException{
        int taskId = taskMan.createTask(name, description);
        if (taskId == 0) {
            System.out.println("Something went wrong; task generated with id key 0.");
        } else {
            dbHelper.setTaskForeignKey(taskId, taskListId);
        }
    }

    public void removeTaskFromList(int taskId) {
        dbHelper.setTaskForeignKey(taskId, 0);
    }

    public TaskList getListByIndex(int index) {
        try {
            return lists.get(index);
        } catch (Exception e) {
            System.out.println("No task found with that index number.");
            return null;
        }
    }

    public void deleteListAndItsTasks(int taskListId) {
        ArrayList<Task> tasks = dbHelper.getAllTasksInList(taskListId);
        for (Task task : tasks) {
            dbHelper.deleteTask(task.getId());
        }
        dbHelper.deleteList(taskListId);
    }

    public void printAllLists() {
        ArrayList<TaskList> allListsDetails = dbHelper.getAllLists();
        if (allListsDetails.size() == 0) {
            System.out.println("The table 'lists' seems to be empty.");
        } else {
            for (TaskList list : allListsDetails) {
                System.out.println(list);
            }
        }
    }
}
