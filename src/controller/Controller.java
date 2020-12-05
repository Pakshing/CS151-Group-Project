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


public class Controller {
    private BlockingQueue<Message> queue; //queue.take(), queue.put();
    private View toDoView; // Direct reference to view
    private HabitView habitView;
    private ArrayList<Task> model; // Direct reference to model
    private ArrayList<Task> imodel;
    private ArrayList<Habit> habitModel;


    private List<Valve> valves = new LinkedList<Valve>();

    public Controller(View view, HabitView habitView,ArrayList<Task> wholeTasks, ArrayList<Task> model, ArrayList<Habit> habits, BlockingQueue<Message> queue) {
        this.toDoView = view;
        this.habitView = habitView;
        this.model = wholeTasks;
        this.queue = queue;
        this.imodel = model;
        this.habitModel = habits;
        valves.add(new AddNewTaskValve());
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

    private void updateGameInfo() {

    }

    private interface Valve {
        /**
         * Performs certain action in response to message
         */
        public ValveResponse execute(Message message);
    }

    private class AddNewTaskValve implements Valve{
        //make the class object here
        @Override
        public ValveResponse execute(Message message) {
            if(message.getClass() != AddTaskMessage.class){
                return ValveResponse.MISS;
            }
            if (((AddTaskMessage) message).getImportant() == 1)
            {
                String title = ((AddTaskMessage) message).getTitle();
                Task task = new Task(title);
                imodel.add(task);
                toDoView.changeImp(imodel);
                return ValveResponse.EXECUTED;
            }
            else
                {
                String title = ((AddTaskMessage) message).getTitle();
                Task task = new Task(title);
                model.add(task);
                toDoView.change(model);
                return ValveResponse.EXECUTED;
                }
            //return null;
        }
    }
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
            habitModel.add(habit);
            habitView.change(habitModel);
            return ValveResponse.EXECUTED;
        }
    }

}

