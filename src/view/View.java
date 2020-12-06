package view;

import controller.AddTaskMessage;
import controller.HitMessage;
import controller.Message;
import controller.NewGameMessage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import model.*;

/**
 *  creating view object which uses parts of JFrame
 *  View will have different panels for different types of tasks ie: important or not important
 */
public class View extends JFrame {
    TaskView taskView;
    private JFrame gameFrame;
    private BlockingQueue<Message> queue;
    private ArrayList<Task> tasks;
    private ArrayList<Task> tasksImportant;
    private JPanel TaskPanel;
    private JPanel ImportantTasksPanel;
   // private JList<Task> ImportantTasksPanel;

    /**
     *
     * @param queue message queue execution or miss returned value
     * @param tasks whatever task is supplied to this method
     * @param tasksImportant whichever important task is supplied to this method
     * @return
     */
    public static View init(BlockingQueue<Message> queue, ArrayList<Task> tasks, ArrayList<Task> tasksImportant) {
        // Create object of type view
        return new View(queue,tasks, tasksImportant);
    }

    /**
     * Creating layout and panels with buttons/action listneners.
     * @param queue message queue exection or miss return values.
     * @param tasks the normal tasks supplied to this method.
     * @param tasksImportant the important tasks supplied to this method.
     */
    private View(BlockingQueue<Message> queue, ArrayList<Task> tasks, ArrayList<Task> tasksImportant) {
        this.queue = queue;
        this.tasks = tasks;
        // TODO:
        // you should initalize JFrame and show it,
        // JFrame should be able to add Messages to queue
        // JFrame can be in a separate class or created JFrame with all the elements in this class
        // or you can make View a subclass of JFrame by extending it

        this.setPreferredSize(new Dimension(500,500));
        this.setLayout(new BorderLayout());

        TaskPanel = new JPanel();
        ImportantTasksPanel = new JPanel();

        // Set the BoxLayout to be X_AXIS: from left to right 
        BoxLayout boxlayout = new BoxLayout(TaskPanel, BoxLayout.Y_AXIS);
        TaskPanel.setLayout(boxlayout);
        TaskPanel.setSize(100,200);
        TaskPanel.setBackground(new Color(217, 249, 255));

        BoxLayout boxlayout2 = new BoxLayout(ImportantTasksPanel, BoxLayout.Y_AXIS);
        ImportantTasksPanel.setLayout(boxlayout2);
        ImportantTasksPanel.setSize(100,200);
        ImportantTasksPanel.setBackground(new Color(217, 223, 255));

        JLabel titleLabel = new JLabel("To-Do",SwingConstants.CENTER);
        this.add(titleLabel,BorderLayout.NORTH);

        JPanel mainDisplayPanel = new JPanel(new GridLayout(1,2));
        //mainDisplayPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        mainDisplayPanel.add(TaskPanel);
        mainDisplayPanel.add(ImportantTasksPanel);
        //this.add(ImportantTasksPanel);

       this.add(mainDisplayPanel,BorderLayout.CENTER);




        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Add New Task:"),BorderLayout.NORTH);
        JTextField addNewTaskField = new JTextField(10);
        addNewTaskField.setText("");
        inputPanel.add(addNewTaskField,BorderLayout.NORTH);

        JButton addToRegularButton = new JButton("Add To Regular");
        JButton addToImportantButton = new JButton("Add To Important");
        inputPanel.add(addToImportantButton, BorderLayout.EAST);
        inputPanel.add(addToRegularButton,BorderLayout.WEST);



        //for regular tasks
        addToRegularButton.addActionListener(event -> {
            try {
                String title = addNewTaskField.getText();
                addNewTaskField.setText("");
                this.queue.put(new AddTaskMessage(title)); // <--- adding Add New Task message to the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //for Important tasks

        addToImportantButton.addActionListener(event -> {
            try {
                String title = addNewTaskField.getText();
                addNewTaskField.setText("");
                this.queue.put(new AddTaskMessage(title, 1)); // <--- adding to queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        this.add(inputPanel,BorderLayout.SOUTH);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    /**
     * change the view based on what normal tasks are supplied
     * @param tasks based on normal tasks
     */
    public void change(ArrayList<Task> tasks) {
        // TODO: do all the updates and repaint

        this.tasks = tasks;
        TaskPanel.removeAll();
        TaskPanel.add(new JLabel("Regular",SwingConstants.CENTER),BorderLayout.NORTH);
        for(int i=0; i < tasks.size(); i++){
            String title = ((Task)tasks.get(i)).getTitle();
            TaskPanel.add(new TaskButton(new Task(title)));

        }

        this.revalidate();
        this.repaint();
    }

    /**
     * change the important tasks view based on what is supplied
     * @param tasks based on important tasks
     */
    public void changeImp(ArrayList<Task> tasks) {
        this.tasks = tasks;
        ImportantTasksPanel.removeAll();
        ImportantTasksPanel.add(new JLabel("Important",SwingConstants.CENTER),BorderLayout.NORTH);
        for(int i=0; i < tasks.size(); i++){
            String title = ((Task)tasks.get(i)).getTitle();
            ImportantTasksPanel.add(new TaskButton(new Task(title)));
        }
        //loop to print the important tasks
        /*
        for(int i=0; i < tasksImportant.size(); i++){
            String title = ((Task)tasks.get(i)).getTitle();
            ImportantTasksPanel.add(new JLabel(title));
        }
        */
        this.revalidate();
        this.repaint();
    }


    /**
     * clean up all items on screen, but this isnt being used because we are using revalidate and repaint methods shown above.
     */
    public void dispose() {
        // TODO: clear all the resources
        // for example, gameFrame.dispose();
    }
}
