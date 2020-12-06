package view;

import model.Habit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HabitButton extends JButton {
    private Habit habit;
    private HabitView view;

    public HabitButton(Habit habit){
        this.habit = habit;
        setText(habit.getTitle());
        //this.addActionListener(new TaskButtonListener());
    }

    public HabitButton(Habit habit, HabitView view){
      this.habit = habit;
        //this.addActionListener(new TaskButtonListener());
    }

    private class TaskButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Clicked!");

            HabitButton btn = (HabitButton) actionEvent.getSource();
            //to do something
            //view.changeMainDisplay(habit);

        }
    }

}
