package helpers;

import items.ActionFrame;
import items.TaskList;
import managers.TaskListManager;
import managers.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUserInterfaceHelper{
    ActionFrame frame;

    public GUserInterfaceHelper(TaskManager taskMan, TaskListManager taskListMan) {
        setUpFrame(taskMan, taskListMan);
        setUpGreetingAndInfoTexts();
        setUpButtons();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setUpFrame(TaskManager taskMan, TaskListManager taskListMan) {
        frame = new ActionFrame("TASKATRON 3000", taskMan, taskListMan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        BoxLayout boxlayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.getContentPane().setLayout(boxlayout);
    }

    private void setUpGreetingAndInfoTexts() {
        JLabel greeting = new JLabel("Welcome to TASKATRON 3000! For all your tasking needs.");
        frame.add(greeting);
    }

    private void setUpButtons() {
        setUpViewAllButton();
        setUpCreateListButton();
        // setUpCreateAndAddTaskButton();
        // setUpUpdateTaskButton();
        // setUpDeleteTaskButton();
        // setUpDeleteListButton();
        // setUpMoveTaskButton();
        // setUpExitButton();
    }

    private void setUpViewAllButton() {
        JButton button = new JButton("View All Lists and Tasks");
        frame.getContentPane().add(button);
        button.addActionListener(frame);
        button.setActionCommand("displayAll");
    }

    private void setUpCreateListButton() {
        JButton button = new JButton("Create a New List // TODO");
        frame.getContentPane().add(button);
        button.addActionListener(frame);
        button.setActionCommand("createList");
    }

}
