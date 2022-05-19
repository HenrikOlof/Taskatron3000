import helpers.DatabaseHelper;
import helpers.GUserInterfaceHelper;
import managers.TaskListManager;
import managers.TaskManager;

import java.util.Scanner;

import static constants.StringConstants.*;

public class Taskatron {
    static String databasePassword = "";

    public static void main(String args[]) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        TaskListManager listManager = new TaskListManager(databaseHelper);
        TaskManager taskManager = new TaskManager(databaseHelper);
        GUserInterfaceHelper interfaceHelper = new GUserInterfaceHelper(taskManager, listManager);
        databaseHelper.connectToDatabase(databaseUrl, databaseUsername, databasePassword);
        interfaceHelper.setUpGui();
    }
}
