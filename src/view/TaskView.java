package view;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import model.*;
public class TaskView extends JPanel {
    private ArrayList<Task> tasks;

    public TaskView(ArrayList<Task> Tasks){
        this.tasks = Tasks;

        //this.add(new JLabel("To-Do"));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for(int i=0; i < tasks.size();i++){
            this.add(new TaskButton(tasks.get(i)));
        }

        this.revalidate();
    }

    public void updateTaskView(Task t){
        System.out.println("updateTaskView: " + tasks.size());
        System.out.println(tasks.size());
        tasks.add(this.tasks.size()-1, t);
        System.out.println(this.tasks);
        repaint();
    }

//    public void addTask(Task task){
//        tasks.add(task);
//        this.add(new TaskButton(task));
//        this.revalidate();;
//    }



}
