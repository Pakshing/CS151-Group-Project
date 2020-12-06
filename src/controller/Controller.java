package controller;

import view.HabitView;
import view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import model.*;
import java.io.*;


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

            File f = new File("obj.txt");
            try{
               f.createNewFile();
            }catch (IOException err){
                System.out.println("file already exists");
                System.out.println("createNewFile: IOException\n");
            }



            try{
                FileOutputStream fos = new FileOutputStream(f,false);
                try{
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeInt(habitModel.size());
                    for(int i=0; i < habitModel.size();i++){
                        oos.writeObject(habitModel.get(i));
                    }
                    oos.close();
                    System.out.println("Saved the habit with name: " + habit.getTitle());
                }catch (IOException error){
                    System.out.println("IOEception");
                }

            }catch (FileNotFoundException error){
                System.out.println("File not found");
            }

            ArrayList<Habit> inputList = new ArrayList<>();
            try{
                FileInputStream fis = new FileInputStream(f);
                try{
                    ObjectInputStream ois =  new ObjectInputStream(fis);
                    try{

                        int habitSize = ois.readInt();
                        for(int i=0; i < habitSize; i++){
                            inputList.add((Habit)ois.readObject()) ;
                        }


                    }catch (ClassNotFoundException error){
                        System.out.println("Input stream: ClassNotFoundException error");
                    }

                }catch (IOException error){
                    System.out.println("input stream: IOException error");
                }


            }catch (FileNotFoundException err){
                System.out.println("input stream: FileNotFound error");
            }

            System.out.println("\nRead From input stream\n");
            for(int i=0;i<inputList.size();i++){
                System.out.println(inputList.get(i).toString());
            }

            habitView.change(habitModel);



            return ValveResponse.EXECUTED;
        }
    }

}

