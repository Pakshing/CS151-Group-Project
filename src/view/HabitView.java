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

/**
 * HabitView is an instance of view in which we display JFrames that pertain to habits rather than tasks.
 */
public class HabitView extends JFrame {
    private BlockingQueue<Message> queue;
    private ArrayList<Habit> habits;
    //private JPanel TaskPanel;
    private JPanel habitListPanel;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel mainDisplayPanel;
    //private HabitView habitView;
    //private JPanel panelTwo;
    /**
     *
     * @param queue message queue
     * @param habits different objects from habit arraylist
     * @return the new view of our habits and the message queue result.
     */
    public static HabitView init(BlockingQueue<Message> queue, ArrayList<Habit> habits) {
        // Create object of type view
        return new HabitView(queue,habits);
    }

    /**
     * this is the code for our habit view. We set up all of our front-end elements that hook up with our controller/back end code
     * @param queue message queue
     * @param habits different objects from habit arrayList
     */
    private HabitView(BlockingQueue<Message> queue, ArrayList<Habit> habits) {
        this.queue = queue;
        this.habits = habits;
        // TODO:
        // you should initalize JFrame and show it,
        // JFrame should be able to add Messages to queue
        // JFrame can be in a separate class or created JFrame with all the elements in this class
        // or you can make View a subclass of JFrame by extending it
        this.setPreferredSize(new Dimension(600,600));
        this.setLayout(new BorderLayout());


        gbc.insets = new Insets(2,2,2,2);
        if(habits.size() >0){
            Habit habit = habits.get(0);
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
        }




        //this.add(new JLabel("Habit",SwingConstants.CENTER),BorderLayout.NORTH);

        //main display panel in the middle.
        mainDisplayPanel = new JPanel();
        mainDisplayPanel.setLayout(new GridBagLayout());
        mainDisplayPanel.setBackground(new Color(242, 232, 255));
        mainDisplayPanel.setSize(400,400);
        gbc.insets = new Insets(2,2,2,2);




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

    /**
     * this method is here to change the view of habits, and print out the habit the user has added via title variable
     * @param habits values in the arrayList
     */
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
    /**
     * clear all resources, not really being used
     */
    public void dispose() {
        // for example, gameFrame.dispose();
    }


}
