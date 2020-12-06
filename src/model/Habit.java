package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creating a habit class with:
 * Creating a habit with name of title and arrayList of days that the habit was completed on.
 */
public class Habit implements Serializable {
    private String title;
    private ArrayList<Integer> daysOfCompleted;
    public Habit(String title) {
        this.title = title;
        daysOfCompleted = new ArrayList<>();
    }

    /**
     * overriding the toString java default method to return the size of daysOfCompleted Array.
     * @return variable that holds title and size of habit
     */
    @Override
    public String toString() {
        String res = title + ": " + daysOfCompleted.size();
        return res;
    }

    /**
     *
     * @return name of habit
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return arrayList of days that the habit was completed on
     */
    public ArrayList<Integer> getDaysOfCompleted() {
        return daysOfCompleted;
    }

    /**
     *
     * @param title name of habit
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param daysOfCompleted set this objects instance's daysOfCompleted to its own value
     */
    public void setDaysOfCompleted(ArrayList<Integer> daysOfCompleted) {
        this.daysOfCompleted = daysOfCompleted;
    }
}
