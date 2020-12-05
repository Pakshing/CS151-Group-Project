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

public class View extends JFrame {
    TaskView taskView;
    private JFrame gameFrame;
    private BlockingQueue<Message> queue;
    private ArrayList<Task> tasks;
    private ArrayList<Task> tasksImportant;
    private JPanel TaskPanel;
    private JList<Task> ImportantTasksPanel;


    public static View init(BlockingQueue<Message> queue, ArrayList<Task> tasks, ArrayList<Task> tasksImportant) {
        // Create object of type view
        return new View(queue,tasks, tasksImportant);
    }


    private View(BlockingQueue<Message> queue, ArrayList<Task> tasks, ArrayList<Task> tasksImportant) {
        this.queue = queue;
        this.tasks = tasks;
        // TODO:
        // you should initalize JFrame and show it,
        // JFrame should be able to add Messages to queue
        // JFrame can be in a separate class or created JFrame with all the elements in this class
        // or you can make View a subclass of JFrame by extending it

        this.setPreferredSize(new Dimension(500,500));

        this.add(new JLabel("To-Do",SwingConstants.CENTER),BorderLayout.NORTH);
        //TaskView taskView = new TaskView(tasks);
        TaskPanel = new JPanel();

        // Set the BoxLayout to be X_AXIS: from left to right 
        BoxLayout boxlayout = new BoxLayout(TaskPanel, BoxLayout.Y_AXIS);
        TaskPanel.setLayout(boxlayout);



        JButton addToRegularButton = new JButton("Add To Regular");
        JButton addToImportantButton = new JButton("Add To Important");
        this.add(TaskPanel);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Add New Task:"),BorderLayout.NORTH);
        JTextField addNewTaskField = new JTextField(10);
        addNewTaskField.setText("");
        inputPanel.add(addNewTaskField,BorderLayout.NORTH);
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


    public void paint(Graphics g){
        super.paint(g);

        System.out.println("paint: tasks size: " + this.tasks.size());
        Graphics2D g2 = (Graphics2D) g;


    }


    public void change(ArrayList<Task> tasks) {
        // TODO: do all the updates and repaint

        this.tasks = tasks;
        TaskPanel.removeAll();
        for(int i=0; i < tasks.size(); i++){
            String title = ((Task)tasks.get(i)).getTitle();
            TaskPanel.add(new TaskButton(new Task(title)));
            //TaskPanel.add(new JLabel(title));
        }

        this.revalidate();
        this.repaint();
    }

    public void changeImp(ArrayList<Task> tasks) {
        this.tasks = tasks;
        TaskPanel.removeAll();
        for(int i=0; i < tasks.size(); i++){
            String title = ((Task)tasks.get(i)).getTitle();
            TaskPanel.add(new JLabel(title));
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



    public void dispose() {
        // TODO: clear all the resources
        // for example, gameFrame.dispose();
    }
}
