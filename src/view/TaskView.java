package view;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import model.*;

/**
 * create box view for tasks to be displayed
 */
public class TaskView extends JPanel {
    private ArrayList<Task> tasks;

    /**
     *
     * @param Tasks return tasks provided to this method.
     *              add button
     */
    public TaskView(ArrayList<Task> Tasks){
        this.tasks = Tasks;

        //this.add(new JLabel("To-Do"));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for(int i=0; i < tasks.size();i++){
            this.add(new TaskButton(tasks.get(i)));
        }

        this.revalidate();
    }

    /**
     * method not being used
     * @param t for whatever index the task is.
     */
    public void updateTaskView(Task t){
        System.out.println("updateTaskView: " + tasks.size());
        System.out.println(tasks.size());
        tasks.add(this.tasks.size()-1, t);
        System.out.println(this.tasks);
        repaint();
    }




}
