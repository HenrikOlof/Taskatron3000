import helpers.UserInteractionHelper;
import items.Task;
import items.TaskList;
import managers.TaskListManager;
import managers.TaskManager;

import java.util.Scanner;

public class Taskatron {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int choice;
        TaskListManager listManager = new TaskListManager();
        TaskManager taskManager = new TaskManager();
        UserInteractionHelper uiHelper = new UserInteractionHelper(input, listManager);

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

            String[] nameAndDesc;
            String name = "";
            String description = "";
            TaskList taskList;
            Task task;

            switch (choice) {
                case 1: // View all Lists and Tasks
                    for (TaskList list : listManager.getAllLists()) {
                        System.out.println(list.toString());
                        for (Task t : list.getTasks()) System.out.println(t.toString());
                    }
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
                    // TODO
                    // show all lists?
                    // input - select list
                    taskList = uiHelper.getListFromUserInput();
                    // foreach delete tasks
                    // delete the list
                    break;
                case 7: // Move Task from List A to List B
                    // TODO
                    // show all lists?
                    // input - select list A
                    // show all tasks
                    // input - select task
                    // show all lists
                    // input - select list B
                    // remove task from list A
                    // add task to list B
                    break;
                default:
                    break;
            }
        }
    }
}
