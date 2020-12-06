package model;
/**
    this class creates the outline for task objects that all of our arraylists consist of.
    These are the main objects our entire program is working with.
    Variables like title hold name, priority check for important tasks, completed checks if the task is done
 */
public class Task {
    private String title;
    private int priority;
    private boolean completed;

    /**
     *
     * @param title name of task
     * @param priority decide if important task or not
     */
    public Task(String title, int priority) {
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    /**
     *
     * @param title name of task
     */
    public Task(String title) {
        this.title = title;
        this.priority = 0;
        this.completed = false;
    }

    /**
     *
     * @return the name of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return true of false if task is completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     *
     * @param completed setting the task to completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     *
     * @return name of task to a string value
     */
    @Override
    public String toString() {
        return this.title;
    }
}
