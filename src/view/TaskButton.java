package view;

import javax.swing.*;
import model.Task;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskButton extends JButton {
    private Task task;

    public TaskButton(Task task){
        this.task = task;
        setText(task.toString());
        addActionListener(new TaskButtonListener());
    }

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
