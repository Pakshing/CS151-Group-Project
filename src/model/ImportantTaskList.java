package model;

import java.util.ArrayList;

/**
 * hold the array list for our important tasks.
 * have the getter for important tasks as well.
 */
public class ImportantTaskList
{
    private ArrayList<Task> tasksImportant;
    public ImportantTaskList(){
        this.tasksImportant = new ArrayList<>();
    }

    /**
     * not being used by program
     */
    public void addTask(Task task){
        this.tasksImportant.add(task);
    }

    /**
     *
     * @return size of tasks important list array list
     */
    public int getSize(){
        return tasksImportant.size();
    }

    /**
     * this method is not being used.
     */
    public Task getTask(int index){
        return tasksImportant.get(index);
    }

    /**
     *
     * @return important tasks from the arraylist
     */
    public ArrayList<Task> getTasksImportant(){
        return this.tasksImportant;
    }

}
