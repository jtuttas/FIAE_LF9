package todo.fxclient;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.scene.control.Button;
import todo.entity.Entity;
import todo.entity.Project;
import todo.entity.Priority;

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
    @FXML
    private Button btnNoProject;

    @FXML
    private ComboBox priorities;
    @FXML
    private TextField tfPriorityDescription;
    @FXML
    private TextField tfPriorityValue;
    @FXML
    private Button btnUpdatePriority;
    @FXML
    private Button btnDeletePriority;
    @FXML
    private Button btnNoPriority;

    ToDoClientConnection tdc;
    ArrayList<Entity> projectList = new ArrayList<Entity>();
    ArrayList<Entity> priorityList = new ArrayList<Entity>();

    @FXML
    public void initialize() {
        System.out.println("init ProjectController");
        tdc = new ToDoClientConnection("http://localhost:8000");
        updateProjects();
        updatePriorities();
    }

    private void updateProjects() {
        projectList = tdc.getProjects();
        projects.getItems().clear();
        for (int i=0;i<projectList.size();i++) {
            Project p =(Project)projectList.get(i);
            projects.getItems().add(p);
        }        
    }
    private void updatePriorities() {
        priorityList = tdc.getPriorities();
        priorities.getItems().clear();
        for (int i=0;i<priorityList.size();i++) {
            Priority p =(Priority)priorityList.get(i);
            priorities.getItems().add(p);
        }        
    }

    @FXML
    public void noProject(ActionEvent event)
    {
        System.out.println("klick! NoProject");
        btnDeleteProject.setDisable(true);
        btnUpdateProject.setDisable(true);
        projects.getSelectionModel().clearSelection();
    }
    @FXML
    public void noPriority(ActionEvent event)
    {
        System.out.println("klick! NoPriority");
        btnDeletePriority.setDisable(true);
        btnUpdatePriority.setDisable(true);
        priorities.getSelectionModel().clearSelection();
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
    public void addPriority(ActionEvent event)
    {
        System.out.println("klick! AddPriority");
        Priority p = new Priority();
        p.setPriorityDescription(tfPriorityDescription.getText());
        tfPriorityDescription.setText("");
        p.setPriorityValue(Integer.parseInt(tfPriorityValue.getText()));
        tfPriorityValue.setText("");
        tdc.createPriority(p);
        updatePriorities();
        btnDeletePriority.setDisable(true);
        btnUpdatePriority.setDisable(true);
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
    public void deletePriority(ActionEvent event)
    {
        System.out.println("klick! DeletePriority");
        priorities.getItems().remove(priorities.getSelectionModel().getSelectedIndex());
        tfPriorityDescription.setText("");
        tfPriorityValue.setText("");
        tdc.deletePriority((Priority)priorities.getSelectionModel().getSelectedItem());
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
    public void updatePriority(ActionEvent event)
    {
        System.out.println("klick! UpdatePriority");
        Priority p = (Priority)priorities.getSelectionModel().getSelectedItem();
        int i = priorities.getSelectionModel().getSelectedIndex();
        p.setPriorityDescription(tfPriorityDescription.getText());
        p.setPriorityValue(Integer.parseInt(tfPriorityValue.getText()));
        priorities.getItems().set(i, p);
        tdc.updatePriority(p);
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

    @FXML
    public void selectPriority(ActionEvent event)
    {
        if (priorities.getSelectionModel().getSelectedItem()!=null) {
            System.out.println("klick! SelectPriority"+priorities.getSelectionModel().getSelectedItem().toString());        
            Priority p = (Priority)priorities.getSelectionModel().getSelectedItem();
            tfPriorityDescription.setText(p.getPriorityDescription());
            tfPriorityValue.setText(""+p.getPriorityValue());
            btnDeletePriority.setDisable(false);
            btnUpdatePriority.setDisable(false);
        }
    }

}