package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest
{
    @Test
    public void testGetTitle()
    {
        String taskTitle = "complete project";
        Task task = new Task(taskTitle);
        assertEquals(taskTitle, task.getTitle());
        String taskTitle2 = "";
        Task task2 = new Task(taskTitle2);
        assertNotEquals(taskTitle2, task.getTitle());
    }
    @Test
    public void testSetCompleted()
    {
        Task task1 = new Task("finish homework");
        task1.setCompleted(true);
        assertTrue(task1.isCompleted());
        Task task2 = new Task("finish studying");
        task2.setCompleted(false);
        assertFalse(task2.isCompleted());
    }
    @Test
    public void testIsCompleted()
    {
        Task task1 = new Task("complete assignments");
        task1.setCompleted(true);
        assertTrue(task1.isCompleted());
        Task task2 = new Task("attend zoom");
        task2.setCompleted(false);
        assertFalse(task2.isCompleted());
    }
}