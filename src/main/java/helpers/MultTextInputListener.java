package helpers;

import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static constants.StringConstants.*;

public class MultTextInputListener implements ActionListener {
    private ArrayList<JTextField> textFields;
    private JFrame frame;
    private TaskListManager listManager;
    private TaskManager taskManager;

    public MultTextInputListener(JFrame frame, ArrayList<JTextField> textFields, TaskManager taskManager, TaskListManager listManager) {
        super();
        this.frame = frame;
        this.textFields = textFields;
        this.listManager = listManager;
        this.taskManager = taskManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (frame.getTitle().equals(createAndAddTaskDescriptor)) {
            String listIndexAsString = textFields.get(0).getText();
            int taskListId = parseIndexStringInput(listIndexAsString);
            String taskName = textFields.get(1).getText();
            String taskDescription = textFields.get(2).getText();
            if (listManager.checkIfListExistsByIndex(taskListId)) {
                try {
                    listManager.createAndAddTaskToList(taskManager, taskListId, taskName, taskDescription);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (frame.getTitle().equals(editTaskDescriptor)) {
            String listIndexAsString = textFields.get(0).getText();
            int taskId = parseIndexStringInput(listIndexAsString);
            String taskName = textFields.get(1).getText();
            String taskDescription = textFields.get(2).getText();
            if (taskManager.checkIfTaskExistsByIndex(taskId)) {
                taskManager.setTaskName(taskId, taskName);
                taskManager.setTaskDescription(taskId, taskDescription);
            }

        } else if (frame.getTitle().equals(moveTaskDescriptor)) {
            String taskIndexAsString = textFields.get(0).getText();
            int taskId = parseIndexStringInput(taskIndexAsString);
            String listIndexAsString = textFields.get(1).getText();
            int listId = parseIndexStringInput(listIndexAsString);
            if (listManager.checkIfListExistsByIndex(listId) && taskManager.checkIfTaskExistsByIndex(taskId)) {
                listManager.removeTaskFromList(taskId);
                listManager.addTaskToList(listId, taskId);
            }
        }
        frame.dispose();
    }

    private int parseIndexStringInput(String listIndexAsString) {
        try {
            return Integer.parseInt(listIndexAsString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}