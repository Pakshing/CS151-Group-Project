package controller;
public class AddTaskMessage implements Message {
    private String title;
    private int priorityTask = 0;

    public AddTaskMessage(String title) {
        this.title = title;
    }

    public AddTaskMessage(String title, int priorityTask)
    {
        this.title = title;
        this.priorityTask = priorityTask;
    }

    public String getTitle() {
        return title;
    }

    public int getImportant() {return priorityTask;}
}

