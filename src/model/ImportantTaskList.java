package model;

import java.util.ArrayList;

public class ImportantTaskList
{
    private ArrayList<Task> tasksImportant;
    public ImportantTaskList(){
        this.tasksImportant = new ArrayList<>();
    }

    public void addTask(Task task){
        this.tasksImportant.add(task);
    }

    public int getSize(){
        return tasksImportant.size();
    }

    public Task getTask(int index){
        return tasksImportant.get(index);
    }

    public ArrayList<Task> getTasksImportant(){
        return this.tasksImportant;
    }

}
