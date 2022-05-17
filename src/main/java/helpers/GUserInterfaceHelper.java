package helpers;

import items.ActionFrame;
import items.TaskList;
import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUserInterfaceHelper{
    ActionFrame frame;
    JButton button;

    public GUserInterfaceHelper(TaskManager taskMan, TaskListManager taskListMan) {
        setUpFrame(taskMan, taskListMan);
        setUpButtons();
        frame.setVisible(true);
    }

    private void setUpFrame(TaskManager taskMan, TaskListManager taskListMan) {
        frame = new ActionFrame("My First GUI", taskMan, taskListMan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        // setUpGreetingAndInfoTexts();
    }

    private void setUpButtons() {
        setUpViewAllButton();
        // setUpCreateListButton();
        // setUpCreateAndAddTaskButton();
        // setUpUpdateTaskButton();
        // setUpDeleteTaskButton();
        // setUpDeleteListButton();
        // setUpMoveTaskButton();
        // setUpExitButton();
    }

    private void setUpViewAllButton() {
        button = new JButton("P R E S S");
        frame.getContentPane().add(button);
        button.addActionListener(frame);
        button.setActionCommand("displayAll");
    }

}
