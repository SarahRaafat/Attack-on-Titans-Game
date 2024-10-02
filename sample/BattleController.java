package game.sample;

import game.engine.Battle;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import game.engine.weapons.PiercingCannon;
import game.engine.weapons.SniperCannon;
import game.engine.weapons.VolleySpreadCannon;
import game.engine.weapons.WallTrap;
import game.engine.weapons.Weapon;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BattleController {
	@FXML
	Label turnLabel, scoreLabel, resourcesLabel, phaseLabel, wall1, wall2,
			wall3, wall4, wall5;
	@FXML
	Pane lane1, lane2, lane3, lane4, lane5;
	@FXML
	AnchorPane lanesPane;
	int count = 0;
	private Battle battle;

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	public void refresh() {
		removeExtraLanes();
		updateInfoTable();
		fillWallHealth();
		drawTitans();
		setWeaponCount();
		visulaizeLostLanes();

	}

	private void fillWallHealth() {
		// TODO Auto-generated method stub
		wall1.setText(""
				+ battle.getOriginalLanes().get(0).getLaneWall()
						.getCurrentHealth());
		wall2.setText(""
				+ battle.getOriginalLanes().get(1).getLaneWall()
						.getCurrentHealth());
		wall3.setText(""
				+ battle.getOriginalLanes().get(2).getLaneWall()
						.getCurrentHealth());
		if (battle.getOriginalLanes().size() == 5) {
			wall4.setText(""
					+ battle.getOriginalLanes().get(3).getLaneWall()
							.getCurrentHealth());
			wall5.setText(""
					+ battle.getOriginalLanes().get(4).getLaneWall()
							.getCurrentHealth());
		}
	}

	private void visulaizeLostLanes() {
		// TODO Auto-generated method stub
		int laneNumber = 1;
		for (Lane lane : battle.getOriginalLanes()) {
			if (lane.isLaneLost()) {
				getLane(laneNumber).setStyle("-fx-background-color: black;");
			}
			laneNumber++;
		}
	}

	private void setWeaponCount() {
		// TODO Auto-generated method stub
		int laneNumber = 1;
		for (Lane lane : battle.getOriginalLanes()) {
			int piercingCannonCount = 0, sniperCannonCount = 0, volleySpreadCannonCount = 0, wallTrapCount = 0;
			for (Weapon weapon : lane.getWeapons()) {
				if (weapon instanceof PiercingCannon)
					piercingCannonCount++;
				if (weapon instanceof SniperCannon)
					sniperCannonCount++;
				if (weapon instanceof VolleySpreadCannon)
					volleySpreadCannonCount++;
				if (weapon instanceof WallTrap)
					wallTrapCount++;
			}
			// Label label = (Label) gridPane.getChildren().get(i * 2 + 1);
			// label.setText(newTexts[i]);
			// if (node instanceof GridPane) {
			// GridPane foundGridPane = (GridPane)
			// parentPane.lookup(".grid-pane");
			// GridPane gridPane = (GridPane)
			// getLane(laneNumber).getChildren().filtered();
			GridPane gridPane = (GridPane) getLane(laneNumber).getChildren()
					.stream().filter(node -> node instanceof GridPane)
					.findFirst().orElse(null);
			List<Label> labels = gridPane.getChildren().stream()
					.filter(node -> node instanceof Label)
					.map(node -> (Label) node).collect(Collectors.toList());
			labels.get(0).setText("" + wallTrapCount);
			labels.get(1).setText("" + sniperCannonCount);
			labels.get(2).setText("" + volleySpreadCannonCount);
			labels.get(3).setText("" + piercingCannonCount);
			laneNumber++;

		}
	}

	private void drawTitans() {
		int laneNumber = 1;
		for (Lane lane : battle.getOriginalLanes()) {
			getLane(laneNumber).getChildren().removeIf(
					node -> node instanceof VBox);

			for (Titan titan : lane.getTitans()) {
				int d = titan.getDistance();
				titan.getCurrentHealth();
				Label label = new Label();
				ImageView imageView = new ImageView(getImage(titan));
				imageView.setFitWidth(20); // Set the width
				imageView.setFitHeight(20); // Set the height
				VBox vBox = new VBox(imageView, label);
				vBox.setLayoutX((titan.getDistance() * 450 / battle.getTitanSpawnDistance())+20);
				label.setText(titan.getCurrentHealth() + "/"
						+ titan.getBaseHealth());
				getLane(laneNumber).getChildren().add(vBox);
			}
			laneNumber++;
		}

	}

	private Image getImage(Titan titan) {
		// TODO Auto-generated method stub
		if (titan instanceof PureTitan)
			return new Image("file:C:\\Users\\16abo\\Downloads\\a.png");
		if (titan instanceof AbnormalTitan)
			return new Image("file:C:\\Users\\16abo\\Downloads\\b.png");
		if (titan instanceof PureTitan)
			return new Image("a.png");
		if (titan instanceof PureTitan)
			return new Image("a.png");
		return new Image("file:C:\\Users\\16abo\\Downloads\\c.png");

	}

	private Pane getLane(int laneNumber) {
		switch (laneNumber) {
		case 1:
			return lane1;
		case 2:
			return lane2;
		case 3:
			return lane3;
		case 4:
			return lane4;
		case 5:
			return lane5;

		default:
			return null;
		}

	}

	private void updateInfoTable() {
		turnLabel.setText("" + battle.getNumberOfTurns());
		scoreLabel.setText("" + battle.getScore());
		resourcesLabel.setText("" + battle.getResourcesGathered());
		phaseLabel.setText("" + battle.getBattlePhase());
		// battle.getLanes().peek().getLaneWall().getCurrentHealth();

	}

	public void removeExtraLanes() {
		if (battle.getOriginalLanes().size() == 3) {
			lanesPane.getChildren().remove(lane4);
			lanesPane.getChildren().remove(lane5);
		}
	}

	@FXML
	public void passTurnClicked(ActionEvent event) {
		battle.passTurn();
		refresh();
	}

	public void weaponShopClicked(ActionEvent event) {
		try {
			// Load the FXML file of the new scene
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"WeaponShop.fxml"));
			Parent root = loader.load();
			WeaponController controller = loader.getController();
			controller.setBattle(battle);
			controller.populateLanes();
			// controller.weaponImage;
			// controller.weaponInfo;
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
