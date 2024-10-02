package game.sample;

import game.engine.Battle;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GamesModeController {

	// Constructor to initialize the stage
	// public MainmenuController(Stage stage) {
	// this.stage = stage;
	// }
	@FXML
	public void backButtonClicked(ActionEvent event) {
		try {
			// Load the FXML file of the new scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"Mainmenu.fxml"));
			Parent root = loader.load();

			// Create a new scene with the loaded FXML file
			Scene scene = new Scene(root);

			// Set the new scene on the stage
			Stage stage = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
@FXML
	public void hardButtonClicked(ActionEvent event) {
		try {
			Battle battle = new Battle(0, 0, 50, 5, 125);

			// Load the FXML file of the new scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"Battle.fxml"));
			Parent root = loader.load();
			BattleController hardModeController = loader.getController();
			hardModeController.setBattle(battle);
			hardModeController.refresh();
			// Create a new scene with the loaded FXML file
			Scene scene = new Scene(root);

			// Set the new scene on the stage
			Stage stage = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
@FXML
	public void easyButtonClicked(ActionEvent event) {
		try {
			Battle easyBattle = new Battle(0, 0, 50, 3, 250);

			// Load the FXML file of the new scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"Battle.fxml"));
			Parent root = loader.load();
			BattleController hardModeController = loader.getController();
			hardModeController.setBattle(easyBattle);
			hardModeController.refresh();
			// Create a new scene with the loaded FXML file
			Scene scene = new Scene(root);
			// hardModeController.Lane4
			Stage stage = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
