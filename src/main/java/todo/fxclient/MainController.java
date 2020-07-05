package todo.fxclient;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import todo.entity.Entity;
import todo.entity.Project;

import java.util.ArrayList;



import javafx.event.ActionEvent;
import javafx.collections.FXCollections;

public class MainController {
    @FXML
    private ComboBox projects;
    @FXML
    private TextField tfProjectName;
    @FXML
    private Button btnUpdateProject;
    @FXML
    private Button btnDeleteProject;
   
    ToDoClientConnection tdc;
    ArrayList<Entity> projectList = new ArrayList<Entity>();

    @FXML
    public void initialize() {
        System.out.println("init ProjectController");
        tdc = new ToDoClientConnection("http://localhost:8000");
        updateProjects();
    }

    private void updateProjects() {
        projectList = tdc.getProjects();
        projects.getItems().clear();
        for (int i=0;i<projectList.size();i++) {
            Project p =(Project)projectList.get(i);
            projects.getItems().add(p);
        }        
    }

    @FXML
    public void addProject(ActionEvent event)
    {
        System.out.println("klick! AddProject");
        Project p = new Project();
        p.setProjectName(tfProjectName.getText());
        tfProjectName.setText("");
        tdc.createProject(p);
        updateProjects();
        btnDeleteProject.setDisable(true);
        btnUpdateProject.setDisable(true);
    }
    @FXML
    public void deleteProject(ActionEvent event)
    {
        System.out.println("klick! DeleteProject");
        projects.getItems().remove(projects.getSelectionModel().getSelectedIndex());
        tfProjectName.setText("");
        tdc.deleteProject((Project)projects.getSelectionModel().getSelectedItem());
    }
    @FXML
    public void updateProject(ActionEvent event)
    {
        System.out.println("klick! UpdateProject");
        Project p = (Project)projects.getSelectionModel().getSelectedItem();
        int i = projects.getSelectionModel().getSelectedIndex();
        p.setProjectName(tfProjectName.getText());
        projects.getItems().set(i, p);
        tdc.updateProject(p);
    }
    @FXML
    public void selectProject(ActionEvent event)
    {
        if (projects.getSelectionModel().getSelectedItem()!=null) {
            System.out.println("klick! SelectProject"+projects.getSelectionModel().getSelectedItem().toString());        
            tfProjectName.setText(projects.getSelectionModel().getSelectedItem().toString());
            btnDeleteProject.setDisable(false);
            btnUpdateProject.setDisable(false);
        }
    }

}