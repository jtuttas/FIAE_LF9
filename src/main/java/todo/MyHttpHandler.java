package todo;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.sun.net.httpserver.*;
import todo.entity.Entity;

/**
 * Router für incoming requests
 */
public class MyHttpHandler implements HttpHandler {

    DBManager db;
    Entity e;

    /**
     * Constructor for the Router
     * @param db Reference to the database
     * @param e Enity to manage
     */
    public MyHttpHandler(DBManager db, Entity e) {
        this.db = db;
        this.e = e;
    }

    @Override
    /**
     * handler for the incomig requests
     */
    public void handle(com.sun.net.httpserver.HttpExchange t) throws IOException {
        System.out.println("HTTP Method:" + t.getRequestMethod());
        t.getResponseHeaders().add("Content-Type", "Application/JSON");
        String responce = "";
        if (t.getRequestMethod().equals("GET")) {
            handleGet(t);
        }
        if (t.getRequestMethod().equals("POST")) {
            handlePost(t);
        }
        if (t.getRequestMethod().equals("PUT")) {
            handlePut(t);
        }
        if (t.getRequestMethod().equals("DELETE")) {
            handleDelete(t);
        } else {
            responce = "{\"error\":\"unknown Method\"}";
            t.sendResponseHeaders(500, responce.length());
            OutputStream os = t.getResponseBody();
            os.write(responce.getBytes());
            os.close();
        }
    }

    private void handleDelete(com.sun.net.httpserver.HttpExchange t) throws IOException {
        System.out.println("Handle Delete");
        String subPath = t.getRequestURI().toString().substring(t.getRequestURI().toString().lastIndexOf("/")+1);
        System.out.println("sub Path:" + subPath);
        String responce="{";
        try {
            int id = Integer.parseInt(subPath);
            this.e.setId(id);
            Statement stm = db.getConnection().createStatement();
            System.out.println("Statement:"+e.getDeleteStatement());
            stm.execute(e.getDeleteStatement());
            responce=responce+"\"success\":true}"; 
            t.sendResponseHeaders(200,responce.length());
        } catch (NumberFormatException nf) {
            responce="{\"error\":\""+nf.getMessage()+"\"}"; 
            nf.printStackTrace();
            t.sendResponseHeaders(500, responce.length()); 
        }
        catch (SQLException e) {
            responce="{\"error\":\""+e.getMessage()+"\"}"; 
            e.printStackTrace();
            t.sendResponseHeaders(500, responce.length()); 
        }
        OutputStream os = t.getResponseBody();
        os.write(responce.getBytes()); 
        os.close();

    }

    private void handlePut(com.sun.net.httpserver.HttpExchange t) throws IOException {
        System.out.println("Handle Put");
        String subPath = t.getRequestURI().toString().substring(t.getRequestURI().toString().lastIndexOf("/")+1);
        System.out.println("sub Path:" + subPath);
        String responce="{";
        try {
            int id = Integer.parseInt(subPath);
            this.e.setId(id);
            Scanner sc = new Scanner(t.getRequestBody());

            String body = "";
            while (sc.hasNext()) {
                body=body+sc.nextLine();
            }
            System.out.println("Body:"+body);
            e.parseJSON(body);
            Statement stm = db.getConnection().createStatement();
            System.out.println("Statement:"+e.getUpdateStatement());
            stm.execute(e.getUpdateStatement());
            responce=responce+"\"success\":true}"; 
            t.sendResponseHeaders(200,responce.length());
        } catch (NumberFormatException nf) {
            responce="{\"error\":\""+nf.getMessage()+"\"}"; 
            nf.printStackTrace();
            t.sendResponseHeaders(500, responce.length()); 
        }
        catch (SQLException e) {
            responce="{\"error\":\""+e.getMessage()+"\"}"; 
            e.printStackTrace();
            t.sendResponseHeaders(500, responce.length()); 
        }
        OutputStream os = t.getResponseBody();
        os.write(responce.getBytes()); 
        os.close();
    }

    private void handlePost(com.sun.net.httpserver.HttpExchange t) throws IOException {
        System.out.println("Handle Post");
        String responce="{";
        try {
            Scanner sc = new Scanner(t.getRequestBody());

            String body = "";
            while (sc.hasNext()) {
                body=body+sc.nextLine();
            }
            System.out.println("Body:"+body);
            e.parseJSON(body);
            Statement stm = db.getConnection().createStatement();
            System.out.println("Statement:"+e.getCreateStatement());
            stm.execute(e.getCreateStatement());
            responce=responce+"\"success\":true}"; 
            t.sendResponseHeaders(200,responce.length());
        } 
        catch (SQLException e) {
            responce="{\"error\":\""+e.getMessage()+"\"}"; 
            e.printStackTrace();
            t.sendResponseHeaders(500, responce.length()); 
        }
        OutputStream os = t.getResponseBody();
        os.write(responce.getBytes()); 
        os.close();

    }

    private void handleGet(com.sun.net.httpserver.HttpExchange t) throws IOException {
        System.out.println("Handle Get");
        String responce = "[";
        try {
            Statement stm = db.getConnection().createStatement();
            System.out.println("Query:" + this.e.getReadStatement());
            ResultSet rs = stm.executeQuery(this.e.getReadStatement());
            while (rs.next()) {
                e.setEnity(rs);
                System.out.println("JSON:" + e.toJSON());
                responce = responce + e.toJSON() + ",\r\n";
            }
            responce = responce.substring(0, responce.length() - 3);
            responce = responce + "]";
            t.sendResponseHeaders(200, responce.length());
        } catch (SQLException e) {
            responce = "{\"error\":\"" + e.getMessage() + "\"}";
            e.printStackTrace();
            t.sendResponseHeaders(500, responce.length());
        }
        OutputStream os = t.getResponseBody();
        os.write(responce.getBytes());
        os.close();
    }

}