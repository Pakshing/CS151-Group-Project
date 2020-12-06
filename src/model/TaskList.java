package model;
import java.util.ArrayList;

/**
 * Creates the arraylist for our normal/regular tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
/**
 * initialize the arraylist for our normal tasks
 */
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    /**
     *
     * @return the tasks in our arraylist of normal tasks.
     */
    public ArrayList<Task> getWholeTasks(){
        return this.tasks;
    }



}
