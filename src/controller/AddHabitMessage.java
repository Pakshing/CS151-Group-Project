package controller;

public class AddHabitMessage implements Message {
    private String title;

    public AddHabitMessage(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
