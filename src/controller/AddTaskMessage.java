package controller;
import model.*;
public class AddTaskMessage implements Message {
    private String title;

    public AddTaskMessage(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}

class AddToRegularMessage implements Message{

}
