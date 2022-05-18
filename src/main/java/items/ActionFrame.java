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

public class ActionFrame extends JFrame implements ActionListener {
    public static final String displayAll = "Displaying all Lists and their Tasks\n";
    public static final String createList = "Create a List";
    public static final String createAndAddTask = "Create a Task and add it to a List";

    TaskManager taskManager;
    TaskListManager listManager;
    JFrame frame;
    JButton button;
    TextInputListener textInputListener;

    public ActionFrame(String title, TaskManager taskManager, TaskListManager listManager) {
        super(title);
        this.taskManager = taskManager;
        this.listManager = listManager;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch (action) {
            case "displayAll":
                frame = new JFrame(displayAll);
                ArrayList<TaskList> lists = listManager.getAllLists();
                ArrayList<Task> tasks = taskManager.getAllTasks();
                JTextArea allEntries = new JTextArea(lists.size() + tasks.size(), 40);
                for (TaskList list : lists) {
                    allEntries.append(list.toString() + "\n");
                    for (Task task : tasks) {
                        if (task.getTaskListId() == list.getId()) {
                            allEntries.append("    " + task + "\n");
                        }
                    }
                }
                allEntries.setEditable(false);
                renderTextArea(frame, allEntries);
                prepFrame(frame);
                break;
            case "createList": // Create a new List
                frame = new JFrame(createList);
                ArrayList<JTextField> singleTextField = new ArrayList<>();
                JTextField input = new JTextField(20);
                singleTextField.add(input);
                JTextArea textArea = new JTextArea("Please enter the name of the new List");
                button = new JButton("Enter");
                TextInputListener listNameListener = new TextInputListener(frame, input, taskManager, listManager);
                button.addActionListener(listNameListener);
                createPromptAndButtonWithInput(frame, textArea, button, singleTextField);
                break;
            case "createAndAddTask": // Create and add Task to a List
                frame = new JFrame(createAndAddTask);
                ArrayList<JTextField> inputs = new ArrayList<>();
                inputs.add(new JTextField(20));
                inputs.add(new JTextField(20));
                inputs.add(new JTextField(20));
                JTextArea pickListArea = new JTextArea("Please enter the ID number of the List to add a Task to,\n" +
                        " As well as the Name of the new Task and what it entails.");
                for (TaskList list : listManager.getAllLists()) {
                    pickListArea.append(list.toString() + "\n");
                }
                MultTextInputListener multTextInput = new MultTextInputListener(frame, inputs, taskManager, listManager);
                button = new JButton("Enter");
                button.addActionListener(multTextInput);
                createPromptAndButtonWithInput(frame, pickListArea, button, inputs);
                break;
            /**case "updateTask": // Update name and description of a Task within a List
             taskListId = uiHelper.getListFromUserInput();
             taskId = uiHelper.getTaskFromUserInput(taskListId);
             nameAndDesc = uiHelper.getNameAndDescriptionFromUser();
             taskManager.setTaskName(taskId, nameAndDesc[0]);
             taskManager.setTaskDescription(taskId, nameAndDesc[1]);
             break;
             case "deleteTask": // Delete Task from List
             taskListId = uiHelper.getListFromUserInput();
             taskId = uiHelper.getTaskFromUserInput(taskListId);
             taskManager.deleteTask(taskId);
             break;
             case "deleteListAndTasks": // Delete List and its tasks
             taskListId = uiHelper.getListFromUserInput();
             listManager.deleteListAndItsTasks(taskListId, taskManager);
             break;
             case "moveTask": // Move Task from one List to another
             int listOne = uiHelper.getListFromUserInput();
             taskId = uiHelper.getTaskFromUserInput(listOne);
             int listTwo = uiHelper.getListFromUserInput();
             listManager.removeTaskFromList(taskId);
             listManager.addTaskToList(listTwo, taskId);
             break;*/
            default:
                break;
        }
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

    private static class MultTextInputListener implements ActionListener {
        private ArrayList<JTextField> textFields = new ArrayList<>();
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
            if (frame.getTitle().equals(createAndAddTask)) {
                String listIndexAsString = textFields.get(0).getText();
                int taskListId = parseIndexStringInput(listIndexAsString);
                String taskName = textFields.get(1).getText();
                String taskDescription = textFields.get(2).getText();
                try {
                    listManager.createAndAddTaskToList(taskManager, taskListId, taskName, taskDescription);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
            }
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
            if (frame.getTitle().equals(createList)) {
                String listName = jTextField.getText();
                listManager.createList(listName);
            }
            frame.dispose();
        }
    }
}
