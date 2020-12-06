package controller;

/**
 * this class just holds data for the queue/message to deliver to the controller in order to add a task
 */
public class AddTaskMessage implements Message {
    private String title;
    private int priorityTask = 0;

    /**
     * @param title the title of task that is added
     */
    public AddTaskMessage(String title) {
        this.title = title;
    }
    /**
     * @param title the title of task that is added
     * @param priorityTask is a variable to decide whether the task is important or not
     */
    public AddTaskMessage(String title, int priorityTask)
    {
        this.title = title;
        this.priorityTask = priorityTask;
    }

    /**
     *
     * @return using this method to turn the title into a string to pass into another method.
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return return 1 if the task is important
     */
    public int getImportant() {return priorityTask;}
}
