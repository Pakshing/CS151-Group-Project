

import controller.Controller;
import controller.Message;
import model.Model;
import view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class YourProgramName {
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view;
    private static Model model;

    public static void main(String[] args) {
        view = View.init(queue);
        model = new Model();
        Controller controller = new Controller(view, model, queue);

        controller.mainLoop();
        view.dispose();
        queue.clear();
    }
}

