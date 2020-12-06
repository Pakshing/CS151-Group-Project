
import model.*;
import controller.Controller;
import controller.Message;
import view.HabitView;

import java.io.*;
import java.util.*;

import view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The starting point of this program
 * Initialize all the models and BlockingQueue, Views
 */
public class HabitTracker {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static ArrayList<Task> modelTask =  new ArrayList<>();
    public static ArrayList<Task> modelImportant = new ArrayList<>(); //create new model for important values
    private static ArrayList<Habit> habitsModel;
    private static HabitView habitView;

    /**
     * main method for starting the program
     * @param args
     */
    public static void main(String[] args) {

        habitsModel = readDataFromTxt();
        view = View.init(queue,modelTask, modelImportant);
        habitView = HabitView.init(queue, habitsModel);

        Controller controller = new Controller(view,habitView, modelTask, modelImportant,habitsModel, queue);


        controller.mainLoop();


        view.dispose();
        habitView.dispose();

        queue.clear();

    }

    /**
     * Read data from a tile file or create one if not exits
     * @return list of habit objects
     */
    public static ArrayList<Habit> readDataFromTxt(){
        File f = new File("obj.txt");
        try{
            f.createNewFile();
        }catch (IOException err){
            System.out.println("file already exists");
            System.out.println("createNewFile: IOException\n");
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

        return inputList;
    }

}

