package todo.fxclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import todo.entity.Priority;
import todo.entity.Project;
import todo.entity.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application
{
	public static void main(String[] args) 
	{
		Application.launch(args);
	}

    @Override
	public void start(Stage stage) throws IOException
	{
		// Create the FXMLLoader 
		FXMLLoader loader = new FXMLLoader();
		// Path to the FXML File
		String fxmlDocPath = "gui.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

		// Create the Pane and all Details
		Parent root = (Parent) loader.load(fxmlStream);

		// Create the Scene
		Scene scene = new Scene(root);
		// Set the Scene to the Stage
		stage.setScene(scene);
		// Set the Title to the Stage
		stage.setTitle("ToDo");
		// Display the Stage
		stage.show();
	}

   
}