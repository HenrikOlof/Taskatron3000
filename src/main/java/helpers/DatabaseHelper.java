package helpers;

import com.mysql.cj.util.StringUtils;
import items.Task;
import items.TaskList;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseHelper {

    Connection databaseConnection;

    public DatabaseHelper() {
    }

    public void connectToDatabase(String url, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<TaskList> getAllLists() {
        String statement = "SELECT * FROM lists";
        ArrayList<TaskList> output = new ArrayList<>();
        try (Statement stmt = databaseConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                TaskList taskList = new TaskList(id, name);
                output.add(taskList);
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Task> getAllTasks() {
        String statement = "SELECT * FROM tasks";
        return extractTasksFromDatabaseAsArrayList(statement);
    }

    public ArrayList<Task> getAllTasksInList(int taskListId) {
        String statement = "SELECT * FROM tasks WHERE list_id = " + taskListId + ";";
        return extractTasksFromDatabaseAsArrayList(statement);
    }

    public void enterListIntoDB(String name) throws SQLException {
        String statement = "INSERT INTO lists (name) VALUES ('" + name + "');";
        try(Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("A list with that name already exists.");
        }
    }

    public int enterTaskIntoDB(String name, String description) throws SQLException {
        String statement = "INSERT INTO tasks (name, description) VALUES ('" + name + "', '" + description + "');";
        try(Statement stmt = databaseConnection.createStatement()) {
            Integer taskId = 0;
            int number = stmt.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
            if (number > 0) {
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()) {
                    taskId = genKeys.getObject(1, Integer.class);
                }
            }
            return taskId;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("A task with that name already exists.");
            return 0;
        }
    }

    public boolean checkListIdValidity(int id) {
        String statement = "SELECT * FROM lists WHERE find_in_set('" + id + "', id)";
        try(Statement stmt = databaseConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setTaskForeignKey(int taskId, int taskListId) {
        String statement = "UPDATE tasks SET list_id = " + taskListId +
                " WHERE id = " + taskId + ";";
        try(Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTaskName(int taskId, String name) {
        String statement = "UPDATE tasks SET name = '" + name +
                "' WHERE id = " + taskId + ";";
        try(Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTaskDescription(int taskId, String description) {
        String statement = "UPDATE tasks SET description = '" + description +
                "' WHERE id = " + taskId + ";";
        try(Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteList(int taskListId) {
        String statement = "DELETE FROM lists WHERE id = " + taskListId + ";";
        try(Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int taskId) {
        String statement = "DELETE FROM tasks WHERE id = " + taskId + ";";
        try(Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Help methods

    private ArrayList<Task> extractTasksFromDatabaseAsArrayList(String statement) {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Statement stmt = databaseConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int list_id = rs.getInt("list_id");
                Task task = new Task(id, name, description, list_id);
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
