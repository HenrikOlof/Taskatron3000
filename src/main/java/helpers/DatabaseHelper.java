package helpers;
import java.sql.*;

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
}
