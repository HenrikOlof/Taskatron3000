package helpers;

import items.ActionFrame;
import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static constants.StringConstants.*;

public class GUserInterfaceHelper{
    ActionFrame frame;
    TaskManager taskMan;
    TaskListManager taskListMan;
    public GUserInterfaceHelper(TaskManager taskMan, TaskListManager taskListMan) {
        this.taskMan = taskMan;
        this.taskListMan = taskListMan;
    }

    public void setUpGui() {
        setUpFrame(taskMan, taskListMan);
        setUpGreetingAndInfoTexts();
        setUpButtons();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setUpFrame(TaskManager taskMan, TaskListManager taskListMan) {
        frame = new ActionFrame(name, taskMan, taskListMan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,300);
        BoxLayout boxlayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.getContentPane().setLayout(boxlayout);
    }

    private void setUpGreetingAndInfoTexts() {
        JLabel greetingPanel = new JLabel(greeting);
        frame.add(greetingPanel);
    }

    private void setUpButtons() {
        // TODO move all these magic strings into StringConstants
        setUpButtonLabelAndCommand("View All Lists and Tasks", displayAll);
        setUpButtonLabelAndCommand("Create a New List", createList);
        setUpButtonLabelAndCommand("Select a List and add a New Task to it", createAndAddTask);
        setUpButtonLabelAndCommand("Select a Task and give it a new Name and Description", updateTask);
        setUpButtonLabelAndCommand("Select a Task and delete it", deleteTask);
        setUpButtonLabelAndCommand("Select a List and delete it along with its Tasks", deleteListAndTasks);
        setUpButtonLabelAndCommand("Select a Task and move it to a new List", moveTask);
        setUpExitButton();
    }

    private void setUpButtonLabelAndCommand(String label, String command) {
        JButton button = new JButton(label);
        frame.getContentPane().add(button);
        button.addActionListener(frame);
        button.setActionCommand(command);
    }

    private void setUpExitButton() {
        JButton button = new JButton("EXIT");
        frame.getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });

    }
}
