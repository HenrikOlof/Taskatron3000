package items;

import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
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
        switch (action) {
            case "displayAll":
                System.out.println("Printing all Lists and their Tasks:");
                taskListManager.printAllLists();
                taskManager.printAllTasks();
                System.out.println();
                break;
            case "createList": // Create a new List
                JFrame frame = new JFrame("Create a List");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                JTextArea textArea = new JTextArea("Please enter the name of the new List");
                textArea.setEditable(false);
                JScrollPane scroller = new JScrollPane(textArea);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JTextField input = new JTextField(20);
                JButton button = new JButton("Enter");
                DefaultCaret caret = (DefaultCaret) textArea.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
                inputpanel.add(input);
                inputpanel.add(button);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setResizable(false);
                input.requestFocus();
                // TODO get the value written in the field when "Enter" happens
                // TODO make the dialog box close when we hit enter
                // TODO only create the new list if we hit enter
                // ^ these 3 are all going into the behavior of the "addActionListener" thing
                // TODO how to capture an ActionPerformed inside an ActionPerformed?
                String name = "";
                taskListManager.createList(name);
                break;
            /**case "createAndAddTask": // Create and add Task to a List
                taskListId = uiHelper.getListFromUserInput();
                if (!databaseHelper.checkListIdValidity(taskListId)) break;
                nameAndDesc = uiHelper.getNameAndDescriptionFromUser();
                listManager.createAndAddTaskToList(taskManager, taskListId, nameAndDesc[0], nameAndDesc[1]);
                break;
            case "updateTask": // Update name and description of a Task within a List
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
}
