package todo.fxclient;

import java.util.ArrayList;

import todo.entity.Priority;
import todo.entity.Project;
import todo.entity.Task;

public class Main {

    public static void main(String[] args) {
        ToDoClientConnection tdc = new ToDoClientConnection("http://localhost:8000");
        
        /*
        ArrayList<Project> a = tdc.getProjects();
        Project p = a.get(0);
        p.setProjectName("Ein neues Project 1");
        tdc.updateProject(p);
        Project pn = new Project();
        pn.setProjectName("Ein Projekt!");
        tdc.createProject(pn);
        tdc.deleteProject(p);
        */

        
        ArrayList<Priority> a = tdc.getPriorities();
        
        Priority p = a.get(0);
        p.setPriorityDescription("Eine aktualisierte Priorität");
        tdc.updatePriority(p);        
        Priority pn = new Priority();
        pn.setPriorityDescription("Eine neue Priorität");
        pn.setPriorityValue(10);
        tdc.createPriority(pn);
        tdc.deletePriority(p);
        
        /*
        ArrayList<Task> t = tdc.getTasks();
        Task ta = t.get(0);
        ta.setTitle("Neuer Title");
        tdc.updateTask(ta);        
        Task tn = new Task();
        tn.setTitle("Ein enuer Titelö");
        tdc.createTask(tn);
        tdc.deleteTask(ta);
        */
    }
    
}