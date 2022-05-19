package helpers;

import items.Task;
import items.TaskList;
import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.ArrayList;

public class Methods {
    TaskManager taskManager;
    TaskListManager listManager;
    public Methods(TaskManager taskManager, TaskListManager listManager) {
        this.taskManager = taskManager;
        this.listManager = listManager;
    }
    public JPanel renderTextArea(JFrame frame, JTextArea textArea) {
        textArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(textArea);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scroller);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        return panel;
    }

    public void createPromptAndButtonWithInput(JFrame frame, JTextArea textArea, JButton button, ArrayList<JTextField> inputs) {
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

    public void displayAllListsInDatabaseInTextArea(JTextArea jTextArea) {
        for (TaskList list : listManager.getAllLists()) {
            jTextArea.append(list.toString() + "\n");
        }
    }

    public void displayAllTasksInDatabaseInTextArea(JTextArea jTextArea) {
        for (Task task : taskManager.getAllTasks()) {
            jTextArea.append(task.toString() + "\n");
        }
    }

    public void displayAllListsAndTasksInDatabase(JTextArea jTextArea) {
        for (TaskList list : listManager.getAllLists()) {
            jTextArea.append(list.toString() + "\n");
            for (Task task : taskManager.getAllTasks()) {
                if (task.getTaskListId() == list.getId()) {
                    jTextArea.append("    " + task + "\n");
                }
            }
        }
    }

    public ArrayList<JTextField> addJTextFields(int i) {
        ArrayList<JTextField> inputs = new ArrayList<>();
        for (int j = 0; j<i; j++)
            inputs.add(new JTextField(20));
        return inputs;
    }

    public void prepFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
