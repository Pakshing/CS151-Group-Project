package model;

public class Task {
    private String title;
    private int priority;
    private boolean completed;

    public Task(String title, int priority) {
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    public Task(String title) {
        this.title = title;
        this.priority = 0;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
