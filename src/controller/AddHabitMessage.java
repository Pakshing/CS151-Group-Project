package controller;

/**
 * this class just holds data for the queue/message to deliver to the controller in order to add a habit message
 */
public class AddHabitMessage implements Message {
    private String title;

    /**
     *
     * @param title return this value
     */
    public AddHabitMessage(String title) {
        this.title = title;
    }

    /**
     * getter for title
     * @return title variable
     */
    public String getTitle() {
        return title;
    }
}
