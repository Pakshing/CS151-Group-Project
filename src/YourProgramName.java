

import controller.Controller;
import controller.Message;
import model.Model;
import model.TaskList;
import view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class YourProgramName {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static TaskList model;

    public static void main(String[] args) {
        model = new TaskList();
        view = View.init(queue,model.getWholeTasks());
        //model.attach(view);
        Controller controller = new Controller(view, model.getWholeTasks(), queue);

        controller.mainLoop();
        view.dispose();
        queue.clear();
    }
}

