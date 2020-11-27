package view;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import model.*;
public class TaskView extends JPanel {
    private ArrayList<Task> tasks;

    public TaskView(ArrayList<Task> tasks){
        this.tasks = tasks;

        //this.add(new JLabel("To-Do"));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for(int i=0; i < tasks.size();i++){
            this.add(new TaskButton(tasks.get(i)));
        }

        this.revalidate();
    }

    public void updateTaskView(ArrayList<Task> tasks){
        this.tasks = tasks;
        System.out.println("updateTaskView: " + tasks.size());
//        for(int i=0; i < tasks.size();i++){
//            this.add(new TaskButton(tasks.get(i)));
//        }
        repaint();
    }

//    public void addTask(Task task){
//        tasks.add(task);
//        this.add(new TaskButton(task));
//        this.revalidate();;
//    }



}
