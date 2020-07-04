package todo.fxclient;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import todo.MissingParamaterException;
import todo.entity.Priority;
import todo.entity.Project;
import todo.entity.Task;
import todo.entity.Entity;

public class ToDoClientConnection {

    String server;

    public ToDoClientConnection(String ip) {
        this.server = ip;
    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> theList = new ArrayList<Project>();
        System.out.println("Get Projects");
        getEntity(theList,"/project/",new Project());
        return theList;
    }

    public void updateProject(Project p) {
        System.out.println("Update Project Id=" + p.getId());
        updateEntity(p, "/project/");
    }

    public void deleteProject(Project p) {
        System.out.println("Delete Project id=" + p.getId());
        deleteEntity(p, "/project/");
    }

    public void createProject(Project p) {
        System.out.println("Create Project!");
        createEntity(p, "/project/");
    }

    public ArrayList<Priority> getPriorities() {
        ArrayList<Priority> theList = new ArrayList<Priority>();
        System.out.println("Get Priorites");
        getEntity(theList,"/priority/",new Priority());
        return theList;
    }

    public void updatePriority(Priority p) {
        System.out.println("Update Priority Id=" + p.getId());
        updateEntity(p, "/priority/");
    }

    public void deletePriority(Priority p) {
        System.out.println("Delete Priority id=" + p.getId());
        deleteEntity(p, "/priority/");
    }

    public void createPriority(Priority p) {
        System.out.println("Create Priority!");
        createEntity(p, "/priority/");
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> theList = new ArrayList<Task>();
        System.out.println("Get Tasks");
        getEntity(theList,"/todo/",new Task());
        return theList;
    }

    public void updateTask(Task p) {
        System.out.println("Update Task Id=" + p.getId());
        updateEntity(p, "/todo/");
    }

    public void deleteTask(Task p) {
        System.out.println("Delete Task id=" + p.getId());
        deleteEntity(p, "/todo/");
    }

    public void createTask(Task p) {
        System.out.println("Create Task!");
        createEntity(p, "/todo/");
    }

    /**
     * Insert the Entities (call by reference) in the list for the given Endpoint
     * @param theList the List of Entities
     * @param endpoint the Entpoint
     * @param e the Entity
     */
    private void getEntity(ArrayList theList,String endpoint,Entity e) {
        URL obj;
        try {
            obj = new URL(server + endpoint);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                Scanner sc = new Scanner(con.getInputStream());
                String response = "";
                while (sc.hasNext()) {
                    response += sc.nextLine();
                }
                sc.close();
                System.out.println("Response:" + response);

                JSONArray ja = new JSONArray(response);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject) ja.get(i);
                    try {
                        e.parseJSON(jo.toString());
                        theList.add(e);
                    } catch (MissingParamaterException ex) {
                        ex.printStackTrace();
                    }

                }
            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateEntity(Entity e, String endPoint) {
        URL obj;
        try {
            obj = new URL(server + endPoint+ e.getId());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(e.toJSON());
            osw.flush();
            osw.close();
            os.close();
            con.connect();

            int responseCode = con.getResponseCode();
            System.out.println("PUT Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                Scanner sc = new Scanner(con.getInputStream());
                String response = "";
                while (sc.hasNext()) {
                    response += sc.nextLine();
                }
                sc.close();
                System.out.println("Response:" + response);

            } else {
                System.out.println("PUT request not worked");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void deleteEntity(Entity e, String endPoint) {
        URL obj;
        try {
            obj = new URL(server + endPoint + e.getId());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/json");
            con.connect();

            int responseCode = con.getResponseCode();
            System.out.println("DELETE Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                Scanner sc = new Scanner(con.getInputStream());
                String response = "";
                while (sc.hasNext()) {
                    response += sc.nextLine();
                }
                sc.close();
                System.out.println("Response:" + response);

            } else {
                System.out.println("DELETE request not worked");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void createEntity(Entity e, String endPoint) {
        URL obj;
        try {
            obj = new URL(server + endPoint);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(e.toJSON());
            osw.flush();
            osw.close();
            os.close();
            con.connect();

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                Scanner sc = new Scanner(con.getInputStream());
                String response = "";
                while (sc.hasNext()) {
                    response += sc.nextLine();
                }
                sc.close();
                System.out.println("Response:" + response);

            } else {
                System.out.println("POST request not worked");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}