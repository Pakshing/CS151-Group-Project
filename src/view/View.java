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


    public static View init(BlockingQueue<Message> queue, ArrayList<Task> tasks) {
        // Create object of type view
        return new View(queue,tasks);
    }

    private View(BlockingQueue<Message> queue, ArrayList<Task> tasks) {
        this.queue = queue;
        this.tasks = tasks;
        // TODO:
        // you should initalize JFrame and show it,
        // JFrame should be able to add Messages to queue
        // JFrame can be in a separate class or created JFrame with all the elements in this class
        // or you can make View a subclass of JFrame by extending it
        gameFrame = new JFrame();
        gameFrame.setPreferredSize(new Dimension(500,500));
        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(new JLabel("To-Do",SwingConstants.CENTER),BorderLayout.NORTH);
        TaskView taskView = new TaskView(tasks);

        //JButton newGame = new JButton("New Game");
        //JButton hitButton = new JButton("hit");

        JButton addToRegularButton = new JButton("Add To Regular");
        //JButton addToImportantButton = new JButton("Add To Important");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Add New Task:"),BorderLayout.NORTH);
        JTextField addNewTaskField = new JTextField(10);
        addNewTaskField.setText("");
        inputPanel.add(addNewTaskField,BorderLayout.NORTH);
        inputPanel.add(addToRegularButton,BorderLayout.SOUTH);



        addToRegularButton.addActionListener(event -> {
            try {
                String title = addNewTaskField.getText();
                addNewTaskField.setText("");
                this.queue.put(new AddTaskMessage(new Task(title))); // <--- adding Add New Task message to the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        newGame.addActionListener(event -> {
//            try {
//                this.queue.put(new NewGameMessage()); // <--- adding NewGame message to the queue
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

//        hitButton.addActionListener(event -> {
//            try {
//                this.queue.put(new HitMessage()); // <--- adding Hit message to the queue
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        // add everything and set layout and other standard JFrame settings
        //gameFrame.add(newGame);
        //gameFrame.add(hitButton);
        gameFrame.add(taskView);
        this.add(inputPanel,BorderLayout.SOUTH);
        this.pack();
        //gameFrame.setLayout(new FlowLayout());

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
        System.out.println("change: task size: " +tasks.size());
        this.tasks = tasks;
        taskView.updateTaskView(this.tasks);
       // System.out.println("change");
        this.revalidate();
        this.repaint();
    }

    public void dispose() {
        // TODO: clear all the resources
        // for example, gameFrame.dispose();
    }
}
