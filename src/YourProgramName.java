

import controller.Controller;
import controller.HabitController;
import controller.Message;
import model.Habit;
import model.Model;
import model.TaskList;
import view.View;
import view.HabitView;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class YourProgramName {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static TaskList model;
    private static ArrayList<Habit> habitsModel;
    private static HabitView habitView;

    public static void main(String[] args) {
        habitsModel = new ArrayList<Habit>();
        model = new TaskList();
        //view = View.init(queue,model.getWholeTasks());
        habitView = HabitView.init(queue, habitsModel);

        //model.attach(view);
        //Controller controller = new Controller(view, model.getWholeTasks(), queue);
        HabitController habitController = new HabitController(habitView,habitsModel, queue);

        //controller.mainLoop();
        habitController.mainLoop();

        habitView.dispose();
       // view.dispose();
        queue.clear();
    }
}

