package items;

import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static constants.StringConstants.*;

public class ActionFrame extends JFrame implements ActionListener {
    TaskManager taskManager;
    TaskListManager listManager;
    JFrame frame;
    JButton button;
    TextInputListener textInputListener;
    String inputPassword;

    public ActionFrame(String title, TaskManager taskManager, TaskListManager listManager) {
        super(title);
        this.taskManager = taskManager;
        this.listManager = listManager;
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
                displayAllListsAndTasksInDatabase(jTextArea);
                renderTextArea(frame, jTextArea);
                prepFrame(frame);
                break;

            case createList: // Create a new List
                frame = new JFrame(createListDescriptor);
                JTextField getListId = new JTextField(20);
                JTextArea textArea = new JTextArea("Please enter the name of the new List");
                handleSingleUserInput(textArea, getListId);
                break;

            case createAndAddTask: // Create and add Task to a List
                frame = new JFrame(createAndAddTaskDescriptor);
                inputs = addJTextFields(3);
                jTextArea = new JTextArea("Please enter the ID number of the List to add a Task to,\n" +
                        " As well as the Name of the new Task and what it entails.\n");
                displayAllListsInDatabaseInTextArea(jTextArea);
                handleCaseMultipleUserInputs(inputs, jTextArea);
                break;

            case updateTask: // Update name and description of a Task within a List
                frame = new JFrame(editTaskDescriptor);
                inputs = addJTextFields(3);
                jTextArea = new JTextArea("Please enter the ID of the Task to be changed,\n" +
                        "as well as the new Name and descriptions\n");
                displayAllTasksInDatabaseInTextArea(jTextArea);
                handleCaseMultipleUserInputs(inputs, jTextArea);
                break;

            case deleteTask: // Delete a Task
                frame = new JFrame(deleteTaskDescriptor);
                jTextField = new JTextField(20);
                jTextArea = new JTextArea("Please enter the ID of the Task to be changed,\n");
                displayAllTasksInDatabaseInTextArea(jTextArea);
                handleSingleUserInput(jTextArea, jTextField);
                break;

            case deleteListAndTasks: // Delete List and its tasks
                frame = new JFrame(deleteListAndTasksDescriptor);
                jTextField = new JTextField(20);
                jTextArea = new JTextArea("Please enter the ID of the List to delete,\n");
                displayAllListsInDatabaseInTextArea(jTextArea);
                handleSingleUserInput(jTextArea, jTextField);
                break;

            case moveTask: // Move Task from one List to another
                frame = new JFrame(moveTaskDescriptor);
                inputs = addJTextFields(2);
                jTextArea = new JTextArea("Enter the ID of the Task to move, and the ID of the List to move it to.\n");
                displayAllListsAndTasksInDatabase(jTextArea);
                handleCaseMultipleUserInputs(inputs, jTextArea);
                break;

            default:
                break;
        }
    }

    public String getPassword() {
        return inputPassword;
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
        createPromptAndButtonWithInput(frame, textArea, button, singleTextFieldAsArray);
    }

    private void handleCaseMultipleUserInputs(ArrayList<JTextField> inputs, JTextArea jTextArea) {
        MultTextInputListener multTextInputListener = new MultTextInputListener(frame, inputs, taskManager, listManager);
        button = new JButton("Enter");
        button.addActionListener(multTextInputListener);
        createPromptAndButtonWithInput(frame, jTextArea, button, inputs);
    }

    private void displayAllListsInDatabaseInTextArea(JTextArea jTextArea) {
        for (TaskList list : listManager.getAllLists()) {
            jTextArea.append(list.toString() + "\n");
        }
    }

    private void displayAllTasksInDatabaseInTextArea(JTextArea jTextArea) {
        for (Task task : taskManager.getAllTasks()) {
            jTextArea.append(task.toString() + "\n");
        }
    }

    private void displayAllListsAndTasksInDatabase(JTextArea jTextArea) {
        for (TaskList list : listManager.getAllLists()) {
            jTextArea.append(list.toString() + "\n");
            for (Task task : taskManager.getAllTasks()) {
                if (task.getTaskListId() == list.getId()) {
                    jTextArea.append("    " + task + "\n");
                }
            }
        }
    }

    private ArrayList<JTextField> addJTextFields(int i) {
        ArrayList<JTextField> inputs = new ArrayList<>();
        for (int j = 0; j<i; j++)
            inputs.add(new JTextField(20));
        return inputs;
    }

    private JPanel renderTextArea(JFrame frame, JTextArea textArea) {
        textArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(textArea);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scroller);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        return panel;
    }

    private void createPromptAndButtonWithInput(JFrame frame, JTextArea textArea, JButton button, ArrayList<JTextField> inputs) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = renderTextArea(frame, textArea);
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        for (JTextField input : inputs) inputpanel.add(input);
        inputpanel.add(button);
        panel.add(inputpanel);
        prepFrame(frame);
    }

    private void prepFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Custom ActionListener help classes
     */

    private static class MultTextInputListener implements ActionListener {
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

    private static class TextInputListener implements ActionListener {
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
}
