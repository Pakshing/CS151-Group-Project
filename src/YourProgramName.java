
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

public class YourProgramName {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static TaskList model;
    public static ImportantTaskList modelImportant; //create new model for important values
    private static ArrayList<Habit> habitsModel;
    private static HabitView habitView;

    public static void main(String[] args) {
        model = new TaskList();
        habitsModel = new ArrayList<Habit>();
        modelImportant = new ImportantTaskList();
        view = View.init(queue,model.getWholeTasks(), modelImportant.getTasksImportant());
        habitView = HabitView.init(queue, habitsModel);
        Controller controller = new Controller(view, model.getWholeTasks(), modelImportant.getTasksImportant(), queue);
        HabitController habitController = new HabitController(habitView,habitsModel, queue);
        controller.mainLoop();
        habitController.mainLoop();
        view.dispose();
        habitView.dispose();
        queue.clear();
    }

}

