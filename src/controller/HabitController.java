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

public class HabitController {
    private BlockingQueue<Message> queue; //queue.take(), queue.put();
    private HabitView habitView; // Direct reference to view
    private ArrayList<Habit> model; // Direct reference to model
    private GameInfo gameInfo; // Direct reference to the state of the Game/Application
    private List<Valve> valves = new LinkedList<Valve>();


    public HabitController(HabitView view, ArrayList<Habit> model, BlockingQueue<Message> queue) {
        this.habitView = view;
        this.model = model;
        this.queue = queue;
        valves.add(new AddNewHabitValve());
        //valves.add(new DoNewGameValve());
        //valves.add(new DoHitValve());

    }

    public void mainLoop() {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
            try {
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


    private interface Valve {
        /**
         * Performs certain action in response to message
         */
        public ValveResponse execute(Message message);
    }

    private class AddNewHabitValve implements Valve{
        //make the class object here
        @Override
        public ValveResponse execute(Message message) {
            if(message.getClass() != AddTaskMessage.class){
                return ValveResponse.MISS;
            }
            String title = ((AddHabitMessage)message).getTitle();
            Habit habit = new Habit(title );
            model.add(habit);
            habitView.change(model);
            return ValveResponse.EXECUTED;
        }
    }
}
