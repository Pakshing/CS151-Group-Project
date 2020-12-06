package view;

import javax.swing.*;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import model.*;


public class HabitView extends JFrame {
    private BlockingQueue<Message> queue;
    private ArrayList<Habit> habits;
    private JPanel TaskPanel;
    private JPanel habitListPanel;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel mainDisplayPanel;
    private HabitView habitView;
    //private JPanel panelTwo;

    public static HabitView init(BlockingQueue<Message> queue, ArrayList<Habit> habits) {
        // Create object of type view
        return new HabitView(queue,habits);
    }


    private HabitView(BlockingQueue<Message> queue, ArrayList<Habit> habits) {
        this.queue = queue;
        this.habits = habits;
        // TODO:
        // you should initalize JFrame and show it,
        // JFrame should be able to add Messages to queue
        // JFrame can be in a separate class or created JFrame with all the elements in this class
        // or you can make View a subclass of JFrame by extending it


        /*
        Habit habit1 = new Habit("Exercising");
        ArrayList<Integer> habit1DaysOfComplete = new ArrayList<>();
        habit1DaysOfComplete.add(1);
        habit1DaysOfComplete.add(2);
        habit1DaysOfComplete.add(3);
        habit1DaysOfComplete.add(9);
        habit1DaysOfComplete.add(10);
        habit1DaysOfComplete.add(21);
        habit1.setDaysOfCompleted(habit1DaysOfComplete);

        Habit habit2 = new Habit("Working Out");
        ArrayList<Integer> habit2DaysOfComplete = new ArrayList<>();
        habit2DaysOfComplete.add(1);
        habit2DaysOfComplete.add(2);
        habit2DaysOfComplete.add(3);
        habit2DaysOfComplete.add(9);
        habit2DaysOfComplete.add(10);
        habit2DaysOfComplete.add(21);
        habit2.setDaysOfCompleted(habit2DaysOfComplete);

        Habit habit3 = new Habit("Running");
        ArrayList<Integer> habit3DaysOfComplete = new ArrayList<>();
        habit3DaysOfComplete.add(1);
        habit3DaysOfComplete.add(2);
        habit3DaysOfComplete.add(3);
        habit3DaysOfComplete.add(9);
        habit3DaysOfComplete.add(10);
        habit3DaysOfComplete.add(21);
        habit3.setDaysOfCompleted(habit3DaysOfComplete);

        habits.add(habit1);
        habits.add(habit2);
        habits.add(habit3);
        */



        this.setPreferredSize(new Dimension(600,600));
        this.setLayout(new BorderLayout());


        //this.add(new JLabel("Habit",SwingConstants.CENTER),BorderLayout.NORTH);

        //main display panel in the middle.
        mainDisplayPanel = new JPanel();
        mainDisplayPanel.setLayout(new GridBagLayout());
        mainDisplayPanel.setBackground(new Color(242, 232, 255));
        mainDisplayPanel.setSize(400,400);
        gbc.insets = new Insets(2,2,2,2);
        /*
        JLabel titleLabel = new JLabel("Exercising");

        gbc.gridx = 3;
        gbc.gridy = 0;
        //gbc.gridwidth =5;
        mainDisplayPanel.add(titleLabel,gbc);
        int indexY = 1;
        int indexX = 0;
        for(int i=0; i < 21;i++){
            if(i % 7 ==0){
                indexY +=1;
                indexX =0;
            }
            gbc.gridx = indexX;
            indexX++;
            gbc.gridy = indexY;
            JButton btn = new JButton(Integer.toString(i+1));
            mainDisplayPanel.add(btn,gbc);
        }

         */


        habitListPanel = new JPanel();
        habitListPanel.setLayout(new FlowLayout());
        for(int i =0; i < habits.size(); i++){
            JButton btn = new JButton(habits.get(i).getTitle());
            habitListPanel.add(btn);
        }


        JPanel inputPanel  = new JPanel();
        JLabel addNewHabitLabel = new JLabel("Add New Habit");
        TextField textField = new TextField(10);
        JButton addButton = new JButton("Add");
        JButton saveButton = new JButton("Save Data");



        saveButton.addActionListener(event -> {
            File f = new File("obj.txt");
            try{
                FileOutputStream fos = new FileOutputStream(f);
                try{
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeInt(habits.size());
                    for(int i=0; i < habits.size();i++){
                        oos.writeObject(habits.get(i));
                    }
                    oos.close();
                    System.out.println("Saved btn is clicked\n");
                }catch (IOException error){
                    System.out.println("IOEception");
                }

            }catch (FileNotFoundException error){
                System.out.println("File not found");
            }
        });

        addButton.addActionListener(event -> {
            try {
                String title = textField.getText();
                textField.setText("");
                this.queue.put(new AddHabitMessage(title)); // <--- adding Add New Task message to the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        inputPanel.add(addNewHabitLabel);
        inputPanel.add(textField);
        inputPanel.add(addButton);
        inputPanel.add(saveButton);
        //habitListPanel.add()

        //mainDisplayPanel.add(titleLabel,gbc);


        JPanel bottomPanel = new JPanel(new GridLayout(2,1));
        bottomPanel.add(inputPanel);
        bottomPanel.add(habitListPanel);
        this.add(bottomPanel, BorderLayout.SOUTH);
        //this.add(bottomPanel,BorderLayout.CENTER);
        this.add(mainDisplayPanel,BorderLayout.CENTER);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void change(ArrayList<Habit> habits) {
        // TODO: do all the updates and repaint
        //System.out.println("In change");
        this.habits = habits;
        habitListPanel.removeAll();
        for(int i=0; i < habits.size(); i++){
            String title = ((Habit)habits.get(i)).getTitle();
            HabitButton btn = new HabitButton(habits.get(i));
            btn.addActionListener(new habitButtonListener(habits.get(i)));
            habitListPanel.add(btn);
            //TaskPanel.add(new JLabel(title));
        }

        this.revalidate();
        this.repaint();
    }

    public void changeMainDisplay(Habit habit){
        //mainDisplayPanel.setLayout(new GridBagLayout());
        //mainDisplayPanel.setBackground(new Color(242, 232, 255));
        //mainDisplayPanel.setSize(400,400);

        mainDisplayPanel.removeAll();
        gbc.insets = new Insets(2,2,2,2);

        JLabel titleLabel = new JLabel(habit.getTitle());

        gbc.gridx = 3;
        gbc.gridy = 0;
        //gbc.gridwidth =5;
        mainDisplayPanel.add(titleLabel,gbc);


        int indexY = 1;
        int indexX = 0;
        for(int i=0; i < 21;i++){
            if(i % 7 ==0){
                indexY +=1;
                indexX =0;
            }
            gbc.gridx = indexX;
            indexX++;
            gbc.gridy = indexY;
            DayButton btn = new DayButton(i);
            if(habit.getDaysOfCompleted().contains(i)){
                btn.setBackground(new Color(232, 207, 97));
            }else{

                btn.setBackground(new Color(247, 242, 220));
            }
            btn.setOpaque(true);
            btn.addActionListener(new dayButtonListener(habit,i));
            mainDisplayPanel.add(btn,gbc);
        }

        this.revalidate();
        this.repaint();

    }

    private class habitButtonListener implements ActionListener {
        private Habit habit;

        public habitButtonListener(Habit habit) {
            this.habit = habit;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //HabitButton btn = (HabitButton) actionEvent.getSource();
            changeMainDisplay(habit);
            System.out.println("Clicked! habitBtn");
            //to do something
            //view.changeMainDisplay(habit);

        }
    }

    private class dayButtonListener implements ActionListener {
        private Habit habit;
        private int habitPosition;
        private int day;

        public dayButtonListener(Habit habit,int day) {
            this.habit = habit;
            this.habitPosition =habits.indexOf(habit);
            this.day = day++;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //HabitButton btn = (HabitButton) actionEvent.getSource();
            ArrayList<Integer> daysOfCompleted = habits.get(this.habitPosition).getDaysOfCompleted();
            daysOfCompleted.add(this.day);
            habits.get(this.habitPosition).setDaysOfCompleted(daysOfCompleted);
            changeMainDisplay(habits.get(this.habitPosition));
            System.out.println("Clicked! daybtn");
            //to do something
            //view.changeMainDisplay(habit);

        }
    }

    public void dispose() {
        // TODO: clear all the resources
        // for example, gameFrame.dispose();
    }


}