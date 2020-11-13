package schueler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.net.httpserver.*;

import org.sqlite.SQLiteConfig;

public class MyHandler implements HttpHandler {

    Entity entity;

    public MyHandler(Entity e) {
        entity = e;
    }

    public static void main(String[] args) {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new MyHandler(null));
            server.createContext("/todo", new MyHandler(new Project("Test")));
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        String response = "";
        System.out.println("METHOD:"+exchange.getRequestMethod());
        if (exchange.getRequestMethod().equals("GET")) {
            response = handleGet();
        }
        if (entity != null)
            response += entity.getCreateStatement();
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    private String handleGet() {
        System.out.println("Handle GET");
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            Connection c = DriverManager.getConnection("jdbc:sqlite:todo_klein.db", config.toProperties());
            Statement st = c.createStatement();
            System.out.println("Execute:"+entity.getReadStatement());
            ResultSet rs = st.executeQuery(entity.getReadStatement());
            while (rs.next()) {
                System.out.println("id:"+rs.getInt(1));
            }
            c.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Treiber nicht gefunden");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "null";
    }

}
