package model;

import view.TaskView;

import java.util.ArrayList;
import view.*;

public class TaskList {
    //private String name;
    private ArrayList<Task> tasks;



    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public int getSize(){
        return tasks.size();
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public ArrayList<Task> getWholeTasks(){
        return this.tasks;
    }

    public void updateList(ArrayList<Task> list){
        System.out.println("Size of list: " + list.size());
        this.tasks = list;
    }


}
