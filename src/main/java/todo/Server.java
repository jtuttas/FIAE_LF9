package todo;

import java.io.IOException;
import java.net.InetSocketAddress;


import com.sun.net.httpserver.*;

public class Server {

    int port;
    HttpServer server;

    public Server(int port) throws IOException {
        this.port = port;
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(null); // creates a default executor
    }

    public void start() throws IOException {
        server.start();
        System.out.println("Starte Server auf Port "+port);
    }

    public void stop() {
        server.stop(0);
    }

    public void addRoute(String r, MyHttpHandler h) {
        server.createContext(r, h);
    }

    
    
}