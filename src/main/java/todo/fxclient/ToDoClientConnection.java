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
import todo.entity.Project;

public class ToDoClientConnection {

    String server;

    public ToDoClientConnection(String ip) {
        this.server = ip;
    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> theList = new ArrayList<Project>();
        URL obj;
        try {
            obj = new URL(server + "/project");
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
                    Project p = new Project();
                    try {
                        p.parseJSON(jo.toString());
                        theList.add(p);
                    } catch (MissingParamaterException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                System.out.println("GET request not worked");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return theList;
    }

    public void updateProject(Project p) {
        URL obj;
        System.out.println("Update Project Id="+p.getId());
        try {
            obj = new URL(server + "/project/" + p.getId());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(p.toJSON());
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteProject(Project p) {
        URL obj;
        System.out.println("Delete Project id="+p.getId());
        try {
            obj = new URL(server + "/project/"+p.getId());
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createProject(Project p) {
        URL obj;
        System.out.println("Create Project!");
        try {
            obj = new URL(server + "/project/");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(p.toJSON());
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
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}