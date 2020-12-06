
import model.*;
import controller.Controller;
import controller.Message;
import model.ImportantTaskList;
import model.Model;
import model.TaskList;
import view.HabitView;
import java.util.*;
import controller.HabitController;
import model.ImportantTaskList;
import view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class of our program that implements blocking queue.
 * Initialize view and habitView, and our models we will be using.
 */
public class YourProgramName {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    //private static BlockingQueue<Message> queue2 = new LinkedBlockingQueue<>();
    private static View view;
    private static TaskList model;
    public static ImportantTaskList modelImportant; //create new model for important values
    private static ArrayList<Habit> habitsModel;
    private static HabitView habitView;

    /**
     * declaring our different models
     * declaring our view and its parameters of different task types, habitView and its model
     * declaring our controller and its six parameters, which are the original view, habits view, models regular task list, models important task list, habits model, and the message queue for execution and miss returns.
     * @param args default param for main
     * We finish up by cleaning up our different views via dispose and clear methods.
     */
    public static void main(String[] args) {
        model = new TaskList();
        habitsModel = new ArrayList<Habit>();
        modelImportant = new ImportantTaskList();

        view = View.init(queue,model.getWholeTasks(), modelImportant.getTasksImportant());
        habitView = HabitView.init(queue, habitsModel);

        Controller controller = new Controller(view,habitView, model.getWholeTasks(), modelImportant.getTasksImportant(),habitsModel, queue);
        //HabitController habitController = new HabitController(habitView,habitsModel, queue);

        controller.mainLoop();
        //habitController.mainLoop();

        view.dispose();
        habitView.dispose();

        queue.clear();

    }

}

