package constants;

public class StringConstants {
    /***
     * Credentials
     */
    public static String databaseUrl = "jdbc:mysql://remotemysql.com:3306/PwjAkVU3ae?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC&useSSL=false";
    public static String databaseUsername = "PwjAkVU3ae";
    public static String databasePassword = "HpKtZ4mH2o";

    /***
     * Short one-time strings
     */
    public static final String name = "TASKATRON 3000";
    public static final String greeting = "Welcome to " + name + "! For all your tasking needs.";

    /***
     * Names used to identify affinity in classes and methods
     */
    public static final String displayAll = "displayAll";
    public static final String createList = "createList";
    public static final String createAndAddTask = "createAndAddTask";
    public static final String updateTask = "updateTask";
    public static final String deleteTask = "deleteTask";
    public static final String deleteListAndTasks = "deleteListAndTasks";
    public static final String moveTask = "moveTask";

    /***
     * Descriptors that show up in the GUI
     */
    public static final String displayAllDescriptor = "Displaying all Lists and their Tasks\n";
    public static final String createListDescriptor = "Create a List";
    public static final String createAndAddTaskDescriptor = "Create a Task and add it to a List";
    public static final String editTaskDescriptor = "Update the Name and Description of a Task";
    public static final String deleteTaskDescriptor = "Delete a Task";
    public static final String deleteListAndTasksDescriptor = "Delete a List and all its Tasks";
    public static final String moveTaskDescriptor = "Move a Task to a new List";
}
