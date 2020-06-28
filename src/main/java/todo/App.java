package todo;

import java.io.IOException;
import todo.entity.Priority;
import todo.entity.Project;
import todo.entity.Task;

/**
 * Hello world!
 */
public final class App {


    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        try {
            Server s = new Server(8000);
            s.addRoute("/project", new MyHttpHandler(DBManager.getInstance("todo.db"),new Project()));
            s.addRoute("/priority", new MyHttpHandler(DBManager.getInstance("todo.db"),new Priority()));
            s.addRoute("/todo", new MyHttpHandler(DBManager.getInstance("todo.db"),new Task()));
            s.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
