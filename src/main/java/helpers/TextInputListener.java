package helpers;

import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static constants.StringConstants.*;

public class TextInputListener implements ActionListener {
    private JTextField jTextField;
    private JFrame frame;
    private TaskListManager listManager;
    private TaskManager taskManager;

    public TextInputListener(JFrame frame, JTextField jTextField, TaskManager taskManager, TaskListManager listManager) {
        super();
        this.frame = frame;
        this.jTextField = jTextField;
        this.listManager = listManager;
        this.taskManager = taskManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (frame.getTitle().equals(createListDescriptor)) {
            String listName = jTextField.getText();
            listManager.createList(listName);

        } else if (frame.getTitle().equals(deleteTaskDescriptor)) {
            int taskId = parseIndexStringInput(jTextField.getText());
            if (taskManager.checkIfTaskExistsByIndex(taskId)) taskManager.deleteTask(taskId);

        } else if (frame.getTitle().equals(deleteListAndTasksDescriptor)) {
            int listId = parseIndexStringInput(jTextField.getText());
            if (listManager.checkIfListExistsByIndex(listId)) listManager.deleteListAndItsTasks(listId, taskManager);
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