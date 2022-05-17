package items;

import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionFrame extends JFrame implements ActionListener {
    TaskManager taskManager;
    TaskListManager taskListManager;
    public ActionFrame(String title, TaskManager taskManager, TaskListManager listManager){
        super(title);
        this.taskManager = taskManager;
        this.taskListManager = listManager;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        // TODO move the case into here.
        switch (action) {
            case "displayAll":
                System.out.println("Printing all Lists and their Tasks:");
                taskListManager.printAllLists();
                taskManager.printAllTasks();
                System.out.println();
                break;
            /**
             * case 2: // Create a new List
             *                     System.out.println("Please enter the name of the new List:");
             *                     name = input.nextLine();
             *                     listManager.createList(name);
             *                     break;
             *                 case 3: // Create and add Task to a List
             *                     taskListId = uiHelper.getListFromUserInput();
             *                     if (!databaseHelper.checkListIdValidity(taskListId)) break;
             *                     nameAndDesc = uiHelper.getNameAndDescriptionFromUser();
             *                     listManager.createAndAddTaskToList(taskManager, taskListId, nameAndDesc[0], nameAndDesc[1]);
             *                     break;
             *                 case 4: // Update name and description of a Task within a List
             *                     taskListId = uiHelper.getListFromUserInput();
             *                     taskId = uiHelper.getTaskFromUserInput(taskListId);
             *                     nameAndDesc = uiHelper.getNameAndDescriptionFromUser();
             *                     taskManager.setTaskName(taskId, nameAndDesc[0]);
             *                     taskManager.setTaskDescription(taskId, nameAndDesc[1]);
             *                     break;
             *                 case 5: // Delete Task from List
             *                     taskListId = uiHelper.getListFromUserInput();
             *                     taskId = uiHelper.getTaskFromUserInput(taskListId);
             *                     taskManager.deleteTask(taskId);
             *                     break;
             *                 case 6: // Delete List and its tasks
             *                     taskListId = uiHelper.getListFromUserInput();
             *                     listManager.deleteListAndItsTasks(taskListId, taskManager);
             *                     break;
             *                 case 7: // Move Task from one List to another
             *                     int listOne = uiHelper.getListFromUserInput();
             *                     taskId = uiHelper.getTaskFromUserInput(listOne);
             *                     int listTwo = uiHelper.getListFromUserInput();
             *                     listManager.removeTaskFromList(taskId);
             *                     listManager.addTaskToList(listTwo, taskId);
              */
            default:
                break;
        }
    }
}
