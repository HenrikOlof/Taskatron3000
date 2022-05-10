import helpers.UserInteractionHelper;
import items.Task;
import items.TaskList;
import managers.TaskListManager;

import java.util.Scanner;

public class Taskatron {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        UserInteractionHelper uiHelper = new UserInteractionHelper(input);
        int choice;
        TaskListManager listManager = new TaskListManager();

        /**
         First implementation, no GUI or database. No local saving.
         */
        while (true) {
            System.out.println("Welcome to TASKATRON 3000! For all your tasking needs.");
            System.out.println("Please select from the options below:");
            System.out.println("1. View all Lists.");
            System.out.println("2. Create a new List");
            System.out.println("3. Create and add Task to a List.");
            System.out.println("4. Update name and/or description of a Task within a List.");
            System.out.println("5. Delete Task from List.");
            System.out.println("6. Delete a List (including Tasks).");
            System.out.println("7. Move Task from one List to another.");
            System.out.println("0. Exit.");

            choice = input.nextInt();

            String name = "";
            String description = "";

            switch (choice) {
                case 1: // View all Lists and Tasks
                    for (TaskList list : listManager.getAllLists()) {
                        System.out.println(list.toString());
                        for (Task task : list.getTasks()) System.out.println(task.toString());
                    }
                    break;
                case 2: // Create a new List
                    System.out.println("Please enter the name of the new List:");
                    name = input.nextLine();
                    listManager.createList(name);
                    break;
                case 3: // Create and add Task to a List
                    System.out.println("Which List needs a new Task?");

                    for (TaskList list : listManager.getAllLists()) {
                        System.out.println(listManager.getAllLists().indexOf(list) + ": " + list.toString());
                    }

                    int listIndex = input.nextInt();
                    TaskList list = listManager.getListByIndex(listIndex);
                    if (list == null) break;
                    // String[] nameAndDesc = getNameAndDescriptionFromUser();
                    System.out.println("Please enter the name of the new Task:");
                    name = input.nextLine();
                    System.out.println("Please type the description of the new Task:");

                    listManager.createAndAddTask(list, name, description);
                    break;
                case 4: // Update name and/or description of a Task within a List
                    // show all lists?
                    // input - select list
                    // show all tasks
                    // input - select task
                    // String[] nameAndDesc = getNameAndDescriptionFromUser();
                    // taskManager.setTaskName(nameAndDesc[0]);
                    // taskManager.setTaskDescription(nameAndDesc[1]);
                    break;
                case 5: // Delete Task from List
                    // TODO
                    break;
                case 6: // Delete List and its tasks
                    // TODO
                    break;
                case 7: // Move Task from List A to List B
                    // TODO
                    break;
                default:
                    break;
            }
        }
    }
}
