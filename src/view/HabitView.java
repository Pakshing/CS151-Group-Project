package view;
import javax.swing.*;
import controller.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import model.*;

/**
 * HabitView is an instance of view in which we display JFrames that pertain to habits rather than tasks.
 */
public class HabitView extends JFrame {
    private BlockingQueue<Message> queue;
    private ArrayList<Habit> habits;
    private JPanel TaskPanel;
    private JPanel habitListPanel;
    private GridBagConstraints gbc = new GridBagConstraints();
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
        JPanel mainDisplayPanel = new JPanel();
        mainDisplayPanel.setLayout(new GridBagLayout());
        mainDisplayPanel.setBackground(new Color(242, 232, 255));
        mainDisplayPanel.setSize(400,400);
        gbc.insets = new Insets(2,2,2,2);

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
        System.out.println("In change");
        this.habits = habits;
        habitListPanel.removeAll();
        for(int i=0; i < habits.size(); i++){
            String title = ((Habit)habits.get(i)).getTitle();

            habitListPanel.add(new TaskButton(new Task(title)));
            //TaskPanel.add(new JLabel(title));
        }

        this.revalidate();
        this.repaint();
    }
/**
 * clear all resources, not really being used
 */
    public void dispose() {
        // for example, gameFrame.dispose();
    }


}