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
        setUpButtonLabelAndCommand("View All Lists and Tasks", displayAll);
        setUpButtonLabelAndCommand("Create a New List", createList);
        setUpButtonLabelAndCommand("Create a Task and add it to a List", createAndAddTask);
        setUpButtonLabelAndCommand("Give a Task a new Name and Description", updateTask);
        setUpButtonLabelAndCommand("Delete a Task", deleteTask);
        setUpButtonLabelAndCommand("Delete a List (and its Tasks)", deleteListAndTasks);
        setUpButtonLabelAndCommand("Move a Task to a new List", moveTask);
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
