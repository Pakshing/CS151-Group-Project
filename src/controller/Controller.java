package controller;

import model.Model;
import model.TaskList;
import view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import model.*;

public class Controller {
    private BlockingQueue<Message> queue; //queue.take(), queue.put();
    private View view; // Direct reference to view
    private ArrayList<Task> model; // Direct reference to model
    private GameInfo gameInfo; // Direct reference to the state of the Game/Application

    private List<Valve> valves = new LinkedList<Valve>();

    public Controller(View view, ArrayList<Task> model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
        valves.add(new AddNewTaskValve());
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
            String title = ((AddTaskMessage)message).getTitle();
            Task task = new Task(title );
            model.add(task);
            view.change(model);
            return ValveResponse.EXECUTED;
        }
    }

    private class DoNewGameValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != NewGameMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise it means that it is a NewGameMessage message
            // actions in Model
            System.out.println("Do new Game!");
            // actions in View
            return ValveResponse.EXECUTED;
        }
    }

    private class DoHitValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != HitMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise message is of HitMessage type
            // actions in Model and View
            return ValveResponse.EXECUTED;
        }
    }




}

