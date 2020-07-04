package todo.fxclient;

import java.util.ArrayList;

import todo.entity.Project;

public class Main {

    public static void main(String[] args) {
        ToDoClientConnection tdc = new ToDoClientConnection("http://localhost:8000");
        ArrayList<Project> a = tdc.getProjects();
        Project p = a.get(0);
        p.setProjectName("Ein neues Project 1");
        tdc.updateProject(p);
        Project pn = new Project();
        pn.setProjectName("Ein Projekt!");
        tdc.createProject(pn);
        tdc.deleteProject(p);
    }
    
}