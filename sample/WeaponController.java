package game.sample;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WeaponController {

	private Battle battle;
	@FXML
	ImageView weaponImage1, weaponImage2, weaponImage3, weaponImage4;
	@FXML
	TextArea weaponInfo;
	@FXML
	ComboBox<String> selectingLanes;
	@FXML
	Button buyButton;
	@FXML
	TabPane weaponTabPane;
	@FXML
	Tab weapon1, weapon2, weapon3, weapon4;
	Tab selectedWeaponTab = weapon1;

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	public void CancelButtonClicked(ActionEvent event) {
		loadScreen(event);
	}

	@FXML
	public void buyWeaponClicked(ActionEvent event)
			throws InsufficientResourcesException, InvalidLaneException {
		battle.purchaseWeapon(getCode(), getLane());
		loadScreen(event);
	}

	private Lane getLane() {
		if ("Lane1".equals(selectingLanes.getValue()))
			return battle.getOriginalLanes().get(0);
		if ("Lane2".equals(selectingLanes.getValue()))
			return battle.getOriginalLanes().get(1);
		if ("Lane3".equals(selectingLanes.getValue()))
			return battle.getOriginalLanes().get(2);
		if ("Lane4".equals(selectingLanes.getValue()))
			return battle.getOriginalLanes().get(3);
		return battle.getOriginalLanes().get(4);
	}

	private int getCode() {
		if (weapon4.equals(selectedWeaponTab))
			return 1;
		if (weapon2.equals(selectedWeaponTab))
			return 2;
		if (weapon3.equals(selectedWeaponTab))
			return 3;
		return 4;

	}

	private void checkSelections(ComboBox<String> selectingLanes,
			Button buyButton) {
		// Enable the button if both a weapon tab and a lane are selected
		boolean laneSelected = selectingLanes.getValue() != null;
		buyButton.setDisable(!laneSelected);
	}

	@FXML
	public void populateLanes() {
		selectingLanes.getItems().addAll("Lane1", "Lane2", "Lane3");
		if (battle.getOriginalLanes().size() == 5) {
			selectingLanes.getItems().addAll("Lane4", "Lane5");
		}
		weaponTabPane.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					selectedWeaponTab = newValue;
					checkSelections(selectingLanes, buyButton);
				});

		selectingLanes.setOnAction(event -> checkSelections(selectingLanes,
				buyButton));

	}

	private void loadScreen(ActionEvent event) {
		try {
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

}
