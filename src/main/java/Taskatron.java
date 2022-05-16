import helpers.DatabaseHelper;
import helpers.UserInteractionHelper;
import items.Task;
import items.TaskList;
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
        UserInteractionHelper uiHelper = new UserInteractionHelper(input, listManager);

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
            int taskListId;
            Task task;

            switch (choice) {
                case 0:
                    System.out.println("Thank you for using Taskatron3000! Goodbye.");
                    System.exit(0);
                case 1: // View all Lists and Tasks
                    System.out.println("Printing all Lists and their Tasks:");
                    listManager.printAllLists();
                    taskManager.printAllTasks();
                    System.out.println();
                    break;
                case 2: // Create a new List
                    System.out.println("Please enter the name of the new List:");
                    name = input.nextLine();
                    listManager.createList(name);
                    break;
                case 3: // Create and add Task to a List
                    taskListId = uiHelper.getListFromUserInput();
                    if (!databaseHelper.checkIdValidity(taskListId)) break;
                    nameAndDesc = uiHelper.getNameAndDescriptionFromUser();
                    listManager.createAndAddTask(taskListId, nameAndDesc[0], nameAndDesc[1]);
                    break;
                /**case 4: // Update name and/or description of a Task within a List
                    taskListId = uiHelper.getListFromUserInput();
                    task = uiHelper.getTaskFromUserInput(taskListId);
                    nameAndDesc = uiHelper.getNameAndDescriptionFromUser();
                    taskManager.setTaskName(task, nameAndDesc[0]);
                    taskManager.setTaskDescription(task, nameAndDesc[1]);
                    break;
                case 5: // Delete Task from List
                    taskListId = uiHelper.getListFromUserInput();
                    task = uiHelper.getTaskFromUserInput(taskListId);
                    taskListId.getTasks().remove(task);
                    break;
                case 6: // Delete List and its tasks
                    taskListId = uiHelper.getListFromUserInput();
                    listManager.deleteList(taskListId);
                    break;
                case 7: // Move Task from one List to another
                    TaskList listOne = uiHelper.getListFromUserInput();
                    task = uiHelper.getTaskFromUserInput(listOne);
                    TaskList listTwo = uiHelper.getListFromUserInput();
                    listManager.addTask(task, listTwo);
                    listManager.removeTask(task, listTwo);
                    break;*/
                default:
                    break;
            }
        }
    }
}
