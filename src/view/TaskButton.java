package view;

import javax.swing.*;
import model.Task;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * creating the code behind TaskButton which is a JButton component
 */
public class TaskButton extends JButton {
    private Task task;

    /**
     *
     * @param task creating button with parameter of whatever type of task it is given
     *             convert task to string name
     *             add the action listener for button being clicked
     */
    public TaskButton(Task task){
        this.task = task;
        setText(task.toString());
        addActionListener(new TaskButtonListener());
    }

    /**
     * print clicked if taskbutton is clicked
     * if clicked, have different color then if not clicked. slightly gray.
     */
    private class TaskButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Clicked!");

            TaskButton btn = (TaskButton) actionEvent.getSource();
            if(task.isCompleted()){
                task.setCompleted(false);
                btn.setForeground(Color.BLACK);
                btn.setText(task.toString());
            }else{
                task.setCompleted(true);
                btn.setForeground(Color.GRAY);
                btn.setText(task.toString());
            }
        }
    }
}
