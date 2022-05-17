import helpers.DatabaseHelper;
import helpers.GUserInterfaceHelper;
import helpers.UserInteractionHelper;
import managers.TaskListManager;
import managers.TaskManager;

import java.util.Scanner;

public class Taskatron {
    // Wow, plaintext! So secure!
    static String databaseUrl = "jdbc:mysql://127.0.0.1:3306/taskatron?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC&useSSL=false";
    static String databaseUsername = "root";
    static String databasePassword = "root";

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        int choice;
        DatabaseHelper databaseHelper = new DatabaseHelper(databaseUrl, databaseUsername, databasePassword);
        TaskListManager listManager = new TaskListManager(databaseHelper);
        TaskManager taskManager = new TaskManager(databaseHelper);
        UserInteractionHelper uiHelper = new UserInteractionHelper(input, listManager, taskManager);
        GUserInterfaceHelper interfaceHelper = new GUserInterfaceHelper(taskManager, listManager);

    }
}
