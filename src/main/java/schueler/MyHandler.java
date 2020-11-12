package schueler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;

public class MyHandler implements HttpHandler {

    public MyHandler() {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", this);
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyHandler();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        String response = "<h1>Hallo Welt</h1>";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

}
