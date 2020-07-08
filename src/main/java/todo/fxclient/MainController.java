package todo.fxclient;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import javafx.scene.control.Button;
import todo.entity.Entity;
import todo.entity.Project;
import todo.entity.Task;
import todo.entity.Priority;

import java.util.ArrayList;

import javafx.event.ActionEvent;

public class MainController {
    @FXML
    private ComboBox<Project> projects;
    @FXML
    private TextField tfProjectName;
    @FXML
    private Button btnUpdateProject;
    @FXML
    private Button btnDeleteProject;
    @FXML
    private Button btnNoProject;

    @FXML
    private ComboBox<Priority> priorities;
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

    @FXML
    private TableView<Task> tableView;
    @FXML
    private TableColumn<Task, String> tbColumnProject;
    @FXML
    private TableColumn<Task, String> tbColumnPriority;
    @FXML
    private TableColumn<Task, String> tbColumnTask;
    @FXML
    private TableColumn<Task, String> tbColumnDate;

    @FXML
    private Button btnUpdateTask;
    @FXML
    private Button btnDeleteTask;
    @FXML
    private Button btnCreateTask;

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDate;

    ToDoClientConnection tdc;
    ArrayList<Entity> projectList = new ArrayList<Entity>();
    ArrayList<Entity> priorityList = new ArrayList<Entity>();
    ArrayList<Entity> taskList = new ArrayList<Entity>();

    @FXML
    public void initialize() {
        System.out.println("init ProjectController");
        tdc = new ToDoClientConnection("http://localhost:8000");
        tbColumnTask.setCellValueFactory(new PropertyValueFactory<>("title"));
        tbColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tbColumnProject.setCellValueFactory(new PropertyValueFactory<>("project"));
        tbColumnPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        updateProjects();
        updatePriorities();
        updateTasks();
    }

    private void updateTasks() {
        taskList = tdc.getTasks(projectList, priorityList);
        tableView.getItems().clear();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = (Task) taskList.get(i);
            tableView.getItems().add(t);
        }
    }

    private void updateProjects() {
        projectList = tdc.getProjects();
        projects.getItems().clear();
        for (int i = 0; i < projectList.size(); i++) {
            Project p = (Project) projectList.get(i);
            projects.getItems().add(p);
        }
    }

    private void updatePriorities() {
        priorityList = tdc.getPriorities();
        priorities.getItems().clear();
        for (int i = 0; i < priorityList.size(); i++) {
            Priority p = (Priority) priorityList.get(i);
            priorities.getItems().add(p);
        }
    }

    @FXML
    public void noProject(ActionEvent event) {
        System.out.println("klick! NoProject");
        btnDeleteProject.setDisable(true);
        btnUpdateProject.setDisable(true);
        projects.getSelectionModel().clearSelection();
    }

    @FXML
    public void noPriority(ActionEvent event) {
        System.out.println("klick! NoPriority");
        btnDeletePriority.setDisable(true);
        btnUpdatePriority.setDisable(true);
        priorities.getSelectionModel().clearSelection();
    }

    @FXML
    public void addProject(ActionEvent event) {
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
    public void addPriority(ActionEvent event) {
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
    public void deleteProject(ActionEvent event) {
        System.out.println("klick! DeleteProject");
        tfProjectName.setText("");
        tdc.deleteProject((Project) projects.getSelectionModel().getSelectedItem());
        projects.getItems().remove(projects.getSelectionModel().getSelectedIndex());
        this.updateTasks();
    }

    @FXML
    public void deletePriority(ActionEvent event) {
        System.out.println("klick! DeletePriority");
        tfPriorityDescription.setText("");
        tfPriorityValue.setText("");
        tdc.deletePriority((Priority) priorities.getSelectionModel().getSelectedItem());
        priorities.getItems().remove(priorities.getSelectionModel().getSelectedIndex());
        this.updateTasks();
    }

    @FXML
    public void updateProject(ActionEvent event) {
        System.out.println("klick! UpdateProject");
        Project p = (Project) projects.getSelectionModel().getSelectedItem();
        int i = projects.getSelectionModel().getSelectedIndex();
        p.setProjectName(tfProjectName.getText());
        projects.getItems().set(i, p);
        tdc.updateProject(p);
        this.updateTasks();
    }

    @FXML
    public void updatePriority(ActionEvent event) {
        System.out.println("klick! UpdatePriority");
        Priority p = (Priority) priorities.getSelectionModel().getSelectedItem();
        int i = priorities.getSelectionModel().getSelectedIndex();
        p.setPriorityDescription(tfPriorityDescription.getText());
        p.setPriorityValue(Integer.parseInt(tfPriorityValue.getText()));
        priorities.getItems().set(i, p);
        tdc.updatePriority(p);
        this.updateTasks();
    }

    @FXML
    public void selectProject(ActionEvent event) {
        if (projects.getSelectionModel().getSelectedItem() != null) {
            System.out.println("klick! SelectProject" + projects.getSelectionModel().getSelectedItem().toString());
            tfProjectName.setText(projects.getSelectionModel().getSelectedItem().toString());
            btnDeleteProject.setDisable(false);
            btnUpdateProject.setDisable(false);
        }
    }

    @FXML
    public void selectPriority(ActionEvent event) {
        if (priorities.getSelectionModel().getSelectedItem() != null) {
            System.out.println("klick! SelectPriority" + priorities.getSelectionModel().getSelectedItem().toString());
            Priority p = (Priority) priorities.getSelectionModel().getSelectedItem();
            tfPriorityDescription.setText(p.getPriorityDescription());
            tfPriorityValue.setText("" + p.getPriorityValue());
            btnDeletePriority.setDisable(false);
            btnUpdatePriority.setDisable(false);
        }
    }

    @FXML
    public void taskSelected(MouseEvent me) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Task t = (Task) tableView.getSelectionModel().getSelectedItem();
            System.out.println("Task seleted:" + tableView.getSelectionModel().getSelectedItem());
            tfTitle.setText(t.getTitle());
            if (t.getDate() != null) {
                tfDate.setText(t.getDate());
            }
            if (t.getProject() != null) {
                projects.getSelectionModel().select(t.getProject());
            } else {
                projects.getSelectionModel().clearSelection();
                tfProjectName.setText("");
                btnDeleteProject.setDisable(true);
                btnUpdateProject.setDisable(true);
            }
            if (t.getPriority() != null) {
                priorities.getSelectionModel().select(t.getPriority());
            } else {
                priorities.getSelectionModel().clearSelection();
                tfPriorityDescription.setText("");
                tfPriorityValue.setText("");
                btnDeletePriority.setDisable(true);
                btnUpdatePriority.setDisable(true);
            }
            btnDeleteTask.setDisable(false);
            btnUpdateTask.setDisable(false);
        }
    }

    @FXML
    public void deleteTask(ActionEvent event) {
        System.out.println("delete Task");
        tdc.deleteTask((Task) tableView.getSelectionModel().getSelectedItem());
        tfTitle.setText("");
        tfDate.setText("");
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        priorities.getSelectionModel().clearSelection();
        tfPriorityDescription.setText("");
        tfPriorityValue.setText("");
        btnDeletePriority.setDisable(true);
        btnUpdatePriority.setDisable(true);
        projects.getSelectionModel().clearSelection();
        tfProjectName.setText("");
        btnDeleteProject.setDisable(true);
        btnUpdateProject.setDisable(true);
        btnUpdateTask.setDisable(true);
        btnDeleteTask.setDisable(true);

    }

    @FXML
    public void updateTask(ActionEvent event) {
        System.out.println("update Task");
        Task t = (Task) tableView.getSelectionModel().getSelectedItem();
        t.setTitle(tfTitle.getText());
        t.setDate(tfDate.getText());
        t.setPriority((Priority) priorities.getSelectionModel().getSelectedItem());
        t.setProject((Project) projects.getSelectionModel().getSelectedItem());
        tdc.updateTask(t);
        int index = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().set(index, tableView.getSelectionModel().getSelectedItem());

    }

    @FXML
    public void createTask(ActionEvent event) {
        System.out.println("create Task");
        Task t = new Task();
        t.setTitle(tfTitle.getText());
        if (!tfDate.getText().equals("")) {
            t.setDate(tfDate.getText());
        }
        t.setPriority((Priority) priorities.getSelectionModel().getSelectedItem());
        t.setProject((Project) projects.getSelectionModel().getSelectedItem());
        tdc.createTask(t);
        tfDate.setText("");
        tfTitle.setText("");
        updateTasks();
    }

}