import helpers.DatabaseHelper;
import helpers.UserInteractionHelper;
import items.Task;
import items.TaskList;
import managers.TaskListManager;
import managers.TaskManager;

import java.util.Scanner;

public class Taskatron {
    // Wow, plaintext! So secure!
    static String databaseUrl = "jdbc:mysql://localhost:3306/taskatron?connectionTimeZone=UTC&autoReconnect=true&useSSL=false";
    static String databaseUsername = "root";
    static String databasePassword = "root";

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int choice;
        TaskListManager listManager = new TaskListManager();
        TaskManager taskManager = new TaskManager();
        UserInteractionHelper uiHelper = new UserInteractionHelper(input, listManager);
        DatabaseHelper databaseHelper = new DatabaseHelper(databaseUrl, databaseUsername, databasePassword);

        /**
         First implementation, no GUI or database. No local saving.
         */
        System.out.println("Welcome to TASKATRON 3000! For all your tasking needs. \n");
        while (true) {
            System.out.println("Please select from the options below:");
            System.out.println("1. View all Lists and their Tasks.");
            System.out.println("2. Create a new List.");
            System.out.println("3. Create and add Task to a List.");
            System.out.println("4. Update name and/or description of a Task within a List.");
            System.out.println("5. Delete Task from List.");
            System.out.println("6. Delete a List (including Tasks).");
            System.out.println("7. Move Task from one List to another.");
            System.out.println("0. Exit.");

            choice = input.nextInt();
            input.nextLine();

            String[] nameAndDesc;
            String name = "";
            TaskList taskList;
            Task task;

            switch (choice) {
                case 0:
                    System.out.println("Thank you for using Taskatron3000! Goodbye.");
                    System.exit(0);
                case 1: // View all Lists and Tasks
                    System.out.println("Printing all Lists and their Tasks:");
                    for (TaskList list : listManager.getAllLists()) {
                        System.out.println("List: " + list.toString());
                        for (Task t : list.getTasks()) System.out.println("Task: " + t.toString());
                    }
                    System.out.println();
                    break;
                case 2: // Create a new List
                    System.out.println("Please enter the name of the new List:");
                    name = input.nextLine();
                    listManager.createList(name);
                    break;
                case 3: // Create and add Task to a List
                    taskList = uiHelper.getListFromUserInput();
                    if (taskList == null) break;
                    nameAndDesc = uiHelper.getNameAndDescriptionFromUser();
                    listManager.createAndAddTask(taskList, nameAndDesc[0], nameAndDesc[1]);
                    break;
                case 4: // Update name and/or description of a Task within a List
                    taskList = uiHelper.getListFromUserInput();
                    task = uiHelper.getTaskFromUserInput(taskList);
                    nameAndDesc = uiHelper.getNameAndDescriptionFromUser();
                    taskManager.setTaskName(task, nameAndDesc[0]);
                    taskManager.setTaskDescription(task, nameAndDesc[1]);
                    break;
                case 5: // Delete Task from List
                    taskList = uiHelper.getListFromUserInput();
                    task = uiHelper.getTaskFromUserInput(taskList);
                    taskList.getTasks().remove(task);
                    break;
                case 6: // Delete List and its tasks
                    taskList = uiHelper.getListFromUserInput();
                    listManager.deleteList(taskList);
                    break;
                case 7: // Move Task from one List to another
                    TaskList listOne = uiHelper.getListFromUserInput();
                    task = uiHelper.getTaskFromUserInput(listOne);
                    TaskList listTwo = uiHelper.getListFromUserInput();
                    listManager.addTask(task, listTwo);
                    listManager.removeTask(task, listTwo);
                    break;
                default:
                    break;
            }
        }
    }
}
