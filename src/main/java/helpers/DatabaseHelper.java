package helpers;
import items.Task;
import items.TaskList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {

    Connection databaseConnection;

    public DatabaseHelper(String url, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getAllLists() {
        String statement = "SELECT * FROM lists";
        ArrayList<String> output = new ArrayList<>();
        try (Statement stmt = databaseConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String row = "id: " + id + ", name: " + name;
                output.add(row);
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> getAllTasks() {
        String statement = "SELECT * FROM tasks";
        ArrayList<String> output = new ArrayList<>();
        try (Statement stmt = databaseConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int list_id = rs.getInt("list_id");
                String row = "id: " + id + ", name: " + name + ", description: " + description + ", list_id:  " + list_id;
                output.add(row);
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void enterListIntoDB(String name) throws SQLException {
        String statement = "INSERT INTO lists (name) VALUES ('" + name + "');";
        try(Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("A list with that name already exists.");
        }
    }

    public void enterTaskIntoDB(String name, String description) throws SQLException {
        String statement = "INSERT INTO tasks (name, description) VALUES ('" + name + "', '" + description + "');";
        try(Statement stmt = databaseConnection.createStatement()) {
            stmt.executeUpdate(statement);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("A task with that name already exists.");
        }
    }

    public boolean checkIdValidity(int id) {
        String statement = "SELECT * FROM lists WHERE find_in_set('" + id + "', id)";
        try(Statement stmt = databaseConnection.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateTaskForeignKey(String name, int taskListId) { // TODO change name getter to id getter
        // TODO get the task, then create a new statement where we update the tasks list_id
    }
}
