package game.sample;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainmenuController {
	
	@FXML
	public void playButtonClicked(ActionEvent event){
		try {
            // Load the FXML file of the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameModes.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Set the new scene on the stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@FXML
	public void instructionButtonClicked(ActionEvent event){
		try {
			// Load the FXML file of the new scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Instruction.fxml"));
			Parent root = loader.load();
			
			// Create a new scene with the loaded FXML file
			Scene scene = new Scene(root);
			
			// Set the new scene on the stage
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
		
	

