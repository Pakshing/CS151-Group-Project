package controller;
import model.*;
public class AddTaskMessage implements Message {
    private Task task;

    public AddTaskMessage(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}

class AddToRegularMessage implements Message{

}
