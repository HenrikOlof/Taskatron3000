package managers;

import helpers.DatabaseHelper;
import items.Task;
import items.TaskList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
        TaskList list = new TaskList(name);
        lists.add(list);
        try {
            dbHelper.enterListIntoDB(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createAndAddTask(int taskListId, String name, String description) throws SQLException{
        dbHelper.enterTaskIntoDB(name, description);
        // TODO
        dbHelper.updateTaskForeignKey(name, taskListId);
    }

    public void addTask(Task task, TaskList taskList) {
        taskList.addTask(task); // TODO set the list_id for the task to the lists id
    }

    public void removeTask(Task task, TaskList taskList) {
        taskList.removeTask(task);
    }

    public TaskList getListByIndex(int index) {
        try {
            return lists.get(index);
        } catch (Exception e) {
            System.out.println("No task found with that index number.");
            return null;
        }
    }

    public void deleteList(TaskList taskList) {
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            taskList.getTasks().remove(i);
        }
        lists.remove(taskList);
    }

    public void printAllLists() {
        ArrayList<String> allListsDetails = dbHelper.getAllLists();
        if (allListsDetails.size() == 0) {
            System.out.println("The table 'lists' seems to be empty.");
        } else {
            for (String list : allListsDetails) {
                System.out.println(list);
            }
        }
    }
}
