package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HabitTest
{
    @Test
    public void testGetTitle()
    {
        String habitTitle = "morning alarm";
        Habit habit = new Habit(habitTitle);
        assertEquals(habitTitle, habit.getTitle());
        String habitTitle2 = "";
        Habit habit2 = new Habit(habitTitle2);
        assertNotEquals(habitTitle, habitTitle2);
    }
    @Test
    public void testSetTitle()
    {
        String habitTitle = "go to class";
        Habit habit = new Habit(habitTitle);
        habit.setTitle(habitTitle);
        assertEquals(habitTitle, habit.getTitle());
    }
    @Test
    public void testGetDaysCompleted()
    {
        Habit habit = new Habit("attend practice");
        ArrayList<Integer> daysCompleted = new ArrayList<>();
        daysCompleted.add(7);
        habit.setDaysOfCompleted(daysCompleted);
        assertEquals(daysCompleted, habit.getDaysOfCompleted());
    }
    @Test
    public void testSetDaysCompleted()
    {
        Habit habit = new Habit("study");
        ArrayList<Integer> daysCompleted = new ArrayList<>();
        daysCompleted.add(1);
        habit.setDaysOfCompleted(daysCompleted);
        assertEquals(daysCompleted, habit.getDaysOfCompleted());
    }
}