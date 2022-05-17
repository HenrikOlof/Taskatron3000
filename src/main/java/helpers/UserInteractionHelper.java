package helpers;

import items.Task;
import items.TaskList;
import managers.TaskListManager;
import managers.TaskManager;

import java.util.Scanner;

public class UserInteractionHelper {
    Scanner input;
    TaskListManager listManager;
    TaskManager taskManager;

    public UserInteractionHelper(Scanner input, TaskListManager listManager, TaskManager taskManager) {
        this.input = input;
        this.listManager = listManager;
        this.taskManager = taskManager;
    }

    public String[] getNameAndDescriptionFromUser() {
        System.out.println("Please enter the name of the Task:");
        String name = input.nextLine();
        System.out.println("Please type the description of the Task:");
        String description = input.nextLine();
        String[] nameAndDesc = {name, description};
        return nameAndDesc;
    }

    public int getListFromUserInput() {
        System.out.println("Which List?");
        listManager.printAllLists();
        int listIndex = input.nextInt();
        input.nextLine();
        return listIndex;
    }

    public int getTaskFromUserInput(int taskListId) {
        System.out.println("Which Task?");
        taskManager.printTasksForList(taskListId);
        int taskIndex = input.nextInt();
        input.nextLine();
        return taskIndex;
    }

}
