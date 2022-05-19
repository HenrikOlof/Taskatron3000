package items;

import helpers.Methods;
import helpers.MultTextInputListener;
import helpers.TextInputListener;
import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static constants.StringConstants.*;

public class ActionFrame extends JFrame implements ActionListener {
    TaskManager taskManager;
    TaskListManager listManager;
    JFrame frame;
    JButton button;
    Methods janitor;

    public ActionFrame(String title, TaskManager taskManager, TaskListManager listManager) {
        super(title);
        this.taskManager = taskManager;
        this.listManager = listManager;
        janitor = new Methods(taskManager, listManager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        ArrayList<JTextField> inputs;
        JTextArea jTextArea;
        JTextField jTextField;
        switch (action) {
            case displayAll:
                frame = new JFrame(displayAllDescriptor);
                ArrayList<TaskList> lists = listManager.getAllLists();
                ArrayList<Task> tasks = taskManager.getAllTasks();
                jTextArea = new JTextArea(lists.size() + tasks.size() + 1, 40);
                janitor.displayAllListsAndTasksInDatabase(jTextArea);
                janitor.renderTextArea(frame, jTextArea);
                janitor.prepFrame(frame);
                break;

            case createList: // Create a new List
                frame = new JFrame(createListDescriptor);
                JTextField getListId = new JTextField(20);
                JTextArea textArea = new JTextArea("Please enter the name of the new List");
                handleSingleUserInput(textArea, getListId);
                break;

            case createAndAddTask: // Create and add Task to a List
                frame = new JFrame(createAndAddTaskDescriptor);
                inputs = janitor.addJTextFields(3);
                jTextArea = new JTextArea("Please enter the ID number of the List to add a Task to,\n" +
                        " As well as the Name of the new Task and what it entails.\n");
                janitor.displayAllListsInDatabaseInTextArea(jTextArea);
                handleCaseMultipleUserInputs(inputs, jTextArea);
                break;

            case updateTask: // Update name and description of a Task within a List
                frame = new JFrame(editTaskDescriptor);
                inputs = janitor.addJTextFields(3);
                jTextArea = new JTextArea("Please enter the ID of the Task to be changed,\n" +
                        "as well as the new Name and descriptions\n");
                janitor.displayAllTasksInDatabaseInTextArea(jTextArea);
                handleCaseMultipleUserInputs(inputs, jTextArea);
                break;

            case deleteTask: // Delete a Task
                frame = new JFrame(deleteTaskDescriptor);
                jTextField = new JTextField(20);
                jTextArea = new JTextArea("Please enter the ID of the Task to be changed,\n");
                janitor.displayAllTasksInDatabaseInTextArea(jTextArea);
                handleSingleUserInput(jTextArea, jTextField);
                break;

            case deleteListAndTasks: // Delete List and its tasks
                frame = new JFrame(deleteListAndTasksDescriptor);
                jTextField = new JTextField(20);
                jTextArea = new JTextArea("Please enter the ID of the List to delete,\n");
                janitor.displayAllListsInDatabaseInTextArea(jTextArea);
                handleSingleUserInput(jTextArea, jTextField);
                break;

            case moveTask: // Move Task from one List to another
                frame = new JFrame(moveTaskDescriptor);
                inputs = janitor.addJTextFields(2);
                jTextArea = new JTextArea("Enter the ID of the Task to move, and the ID of the List to move it to.\n");
                janitor.displayAllListsAndTasksInDatabase(jTextArea);
                handleCaseMultipleUserInputs(inputs, jTextArea);
                break;

            default:
                break;
        }
    }

    /**
     * Help Methods
     */

    private void handleSingleUserInput(JTextArea textArea, JTextField textField) {
        TextInputListener listNameListener = new TextInputListener(frame, textField, taskManager, listManager);
        button = new JButton("Enter");
        button.addActionListener(listNameListener);
        ArrayList<JTextField> singleTextFieldAsArray = new ArrayList<>();
        singleTextFieldAsArray.add(textField);
        janitor.createPromptAndButtonWithInput(frame, textArea, button, singleTextFieldAsArray);
    }

    private void handleCaseMultipleUserInputs(ArrayList<JTextField> inputs, JTextArea jTextArea) {
        MultTextInputListener multTextInputListener = new MultTextInputListener(frame, inputs, taskManager, listManager);
        button = new JButton("Enter");
        button.addActionListener(multTextInputListener);
        janitor.createPromptAndButtonWithInput(frame, jTextArea, button, inputs);
    }
}
