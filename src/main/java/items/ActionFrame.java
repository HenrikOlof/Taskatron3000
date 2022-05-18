package items;

import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ActionFrame extends JFrame implements ActionListener {
    public static final String displayAll = "Displaying all Lists and their Tasks";
    public static final String createList = "Create a List";
    public static final String getListTag = "";

    TaskManager taskManager;
    TaskListManager listManager;
    public ActionFrame(String title, TaskManager taskManager, TaskListManager listManager){
        super(title);
        this.taskManager = taskManager;
        this.listManager = listManager;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        JFrame frame;
        JButton button;
        TextInputListener textInputListener;
        JTextField input = new JTextField(20);
        switch (action) {
            case "displayAll":
                frame = new JFrame(displayAll);
                ArrayList<TaskList> lists = listManager.getAllLists();
                ArrayList<Task> tasks = taskManager.getAllTasks();
                JTextArea allEntries = new JTextArea(lists.size() + tasks.size(), 40);
                allEntries.setEditable(false);
                for (TaskList list : lists) {
                    allEntries.append(list.toString() + "\n");
                    for (Task task : tasks) {
                        if (task.getTaskListId() == list.getId()) {
                            allEntries.append("    " + task + "\n");
                        }
                    }
                }
                renderTextArea(frame, allEntries);
                prepFrame(frame);
                break;
            case "createList": // Create a new List
                frame = new JFrame(createList);
                JTextArea textArea = new JTextArea("Please enter the name of the new List");
                button = new JButton("Enter");
                TextInputListener listNameListener = new TextInputListener(frame, input, createList, taskManager, listManager);
                button.addActionListener(listNameListener);
                createPromptAndButtonWithInput(frame, textArea, button, input);
                break;
            /**case "createAndAddTask": // Create and add Task to a List
                frame = new JFrame("Create a Task and add it to a List");
                // TODO get from user which list
                JTextArea pickListArea = new JTextArea("Please enter the ID number of the List to add a Task to\n");
                for (TaskList list : listManager.getAllLists()) {
                    pickListArea.append(list.toString() + "\n");
                }
                textInputListener = new TextInputListener(frame, input, getListTag, taskManager, listManager);
                button = new JButton("Enter");
                button.addActionListener(textInputListener);
                createPromptAndButtonWithInput(frame, pickListArea, button, input);
                // Do something with the return value

                // TODO get a name for the task from the user
                JTextArea taskNameArea = new JTextArea("What is the Task called?");
                createPromptAndButtonWithInput();

                // TODO get a description for the task from the user
                JTextArea taskDesciptionArea = new JTextArea("What does the Task entail?");

                createPromptAndButtonWithInput();
                taskListManager.createAndAddTaskToList(taskManager, taskListId, nameAndDesc[0], nameAndDesc[1]);
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

    private void createPromptAndButtonWithInput(JFrame frame, JTextArea textArea, JButton button, JTextField input) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = renderTextArea(frame, textArea);
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        inputpanel.add(input);
        inputpanel.add(button);
        panel.add(inputpanel);
        prepFrame(frame);
        input.requestFocus();
    }

    private void prepFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private static class TextInputListener implements ActionListener {
        private String tag;
        private String userText;
        private JTextField jTextField;
        private JFrame frame;
        private TaskListManager listManager;
        private TaskManager taskManager;

        public TextInputListener(JFrame frame, JTextField jTextField, String tag, TaskManager taskManager, TaskListManager listManager) {
            super();
            this.frame = frame;
            this.jTextField = jTextField;
            this.tag = tag;
            this.listManager = listManager;
            this.taskManager = taskManager;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            userText = jTextField.getText();
            if (tag.equals(createList)) {
                listManager.createList(userText);
            } else if (tag.equals("")) {

            }
            frame.dispose();
        }

        public String getUserInput() {
            return userText;
        }

    }


}
