package view;

import model.Habit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Custom button for habit object
 */
public class HabitButton extends JButton {
    private Habit habit;
    private HabitView view;

    public HabitButton(Habit habit){
        this.habit = habit;
        setText(habit.getTitle());
    }
}
