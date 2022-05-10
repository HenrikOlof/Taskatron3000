package helpers;

import items.Task;
import items.TaskList;
import managers.TaskListManager;

import java.util.Scanner;

public class UserInteractionHelper {
    Scanner input;
    TaskListManager listManager;

    public UserInteractionHelper(Scanner input, TaskListManager listManager) {
        this.input = input;
        this.listManager = listManager;
    }

    public String[] getNameAndDescriptionFromUser() {
        System.out.println("Please enter the name of the Task:");
        String name = input.nextLine();
        System.out.println("Please type the description of the Task:");
        String description = input.nextLine();
        String[] nameAndDesc = {name, description};
        return nameAndDesc;
    }

    public TaskList getListFromUserInput() {
        System.out.println("Which List?");
        for (TaskList list : listManager.getAllLists()) {
            System.out.println(listManager.getAllLists().indexOf(list) + ": " + list.toString());
        }
        int listIndex = input.nextInt();
        return listManager.getListByIndex(listIndex);
    }

    public Task getTaskFromUserInput(TaskList taskList) {
        System.out.println("Which Task?");
        for (Task task : taskList.getTasks()) {
            System.out.println(taskList.getTasks().indexOf(task) + ": " + task.toString());
        }
        int taskIndex = input.nextInt();
        return taskList.getTasks().get(taskIndex);
    }

}
