package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Habit implements Serializable {
    private String title;
    private ArrayList<Integer> daysOfCompleted;

    public Habit(String title) {
        this.title = title;
        daysOfCompleted = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Integer> getDaysOfCompleted() {
        return daysOfCompleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDaysOfCompleted(ArrayList<Integer> daysOfCompleted) {
        this.daysOfCompleted = daysOfCompleted;
    }

    @Override
    public String toString() {
        String res = title + ": " + daysOfCompleted.size();
        return res;
    }
}
