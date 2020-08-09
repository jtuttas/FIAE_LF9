package todo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


import com.sun.net.httpserver.*;

/**
 * HTTP Server
 * 
 * @author J. Tuttas
 * @version 1.0
 * 
 */
public class Server {

    int port;
    HttpServer server;

    /**
     * Constructor of the Server Instance
     * @param port Port of the Server
     * @throws IOException If Port is blocked etc.
     */
    public Server(int port) throws IOException {
        this.port = port;
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/",new MyHandler());
        server.setExecutor(null); // creates a default executor
    }

    /**
     * Starts the server
     * @throws IOException If Port is blocked etc.
     */
    public void start() throws IOException {
        server.start();
        System.out.println("Starte Server auf Port "+port);
    }


    /**
     * Stop the Server
     */
    public void stop() {
        server.stop(0);
    }

    /**
     * Add a route to the server
     * @param r String of the route
     * @param h Handler for managing Requests for the given route
     */
    public void addRoute(String r, MyHttpHandler h) {
        server.createContext(r, h);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            t.getResponseHeaders().add("Content-Type", "Application/JSON");
            String response = "{\"version\":1.2}";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }


    
    
}