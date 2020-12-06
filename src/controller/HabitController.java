package controller;

import model.Model;
import model.TaskList;
import view.HabitView;
import view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import model.*;

/**
 * This controller was being used for trial/error, but is not being used anymore. So really you can ignore this as we aren't using it anymore.
 * The job of this is to have a seperate controller for handlind habits
 */
public class HabitController {
    private BlockingQueue<Message> queue; //queue.take(), queue.put();
    private HabitView habitView; // Direct reference to view
    private ArrayList<Habit> model; // Direct reference to model
    private List<Valve> valves = new LinkedList<Valve>();

    /**
     *
     * @param view create a view for habits
     * @param model create a model for habits
     * @param queue queue for the message queue
     */
    public HabitController(HabitView view, ArrayList<Habit> model, BlockingQueue<Message> queue) {
        this.habitView = view;
        this.model = model;
        this.queue = queue;
        valves.add(new AddNewHabitValve());

    }

    /**
     * main loop for the valves and message queue
     */
    public void mainLoop() {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
            try {
                System.out.println("in main");
                message = queue.take(); // <--- take next message from the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Look for a Valve that can process a message
            for (Valve valve : valves) {
                response = valve.execute(message);
                // if successfully processed or game over, leave the loop
                if (response != ValveResponse.MISS) {
                    break;
                }
            }
        }
    }

    /**
     * valve for message queue execution
     */
    private interface Valve {
        /**
         * Performs certain action in response to message
         */
        public ValveResponse execute(Message message);
    }

    /**
     * valve for adding new habit
     */
    private class AddNewHabitValve implements Valve{
        //make the class object here
        @Override
        public ValveResponse execute(Message message) {
            System.out.println("ValveRes");
            if(message.getClass() != AddHabitMessage.class){
                System.out.println("Missed from valve");
                return ValveResponse.MISS;
            }
            String title = ((AddHabitMessage)message).getTitle();
            Habit habit = new Habit(title);
            model.add(habit);
            habitView.change(model);
            return ValveResponse.EXECUTED;
        }
    }
}
