package views;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import model.characters.*;
import engine.Game;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.characters.Fighter;
import model.characters.Hero;

public class GUI extends Application {
	static Hero h;
	static Hero myHeroes[];
	static Label sheetH = new Label();

	public static Hero getH() {
		return h;
	}

	public static void setH(Hero h) {
		GUI.h = h;
	}

	Button startGame;
	static Button EndGame;
	Button Quit;
	private static Stage window;
	static Scene s2;
	static Button UP, DOWN, RIGHT, LEFT;
	public static Point clicked;
	static Label name;
	static Label Hp;
	static Label Maxy;
	static Label type;
	static Label points;
	static Label RM;
	static ProgressBar myHealth, mySupplies, myVaccines;
	static Label Healthy, Supplyy, Vacciny;
	static ArrayList<String> avs;


	public static GridPane makeDashBoard() {
		GridPane v = new GridPane();
		name = new Label("Name: ");
		name.setTextFill(Color.WHITE);
		Maxy = new Label("MaxHp: ");
		Maxy.setTextFill(Color.WHITE);
		Maxy.setFont(Font.font("Algerian", 15));
		name.setFont(Font.font("Algerian", 15));
		Hp = new Label("HP: Maximum");
		Hp.setTextFill(Color.WHITE);
		Hp.setFont(Font.font("Algerian", 15));
		type = new Label("Type: Hero");
		type.setTextFill(Color.WHITE);
		type.setFont(Font.font("Algerian", 15));
		points = new Label("Action Points: " + 0);
		points.setTextFill(Color.WHITE);
		points.setFont(Font.font("Algerian", 15));
		RM = new Label("Remaining Heroes");
		RM.setTextFill(Color.WHITE);
		RM.setFont(Font.font("Algerian", 20));
		Healthy = new Label("Health indicator: ");
		Healthy.setTextFill(Color.WHITE);
		Supplyy = new Label("Supply Indicator: ");
		Supplyy.setTextFill(Color.WHITE);
		Vacciny = new Label("Vaccine indicator: ");
		Vacciny.setTextFill(Color.WHITE);
		
		Healthy.setFont(Font.font("Algerian", 15));
		Supplyy.setFont(Font.font("Algerian", 15));
		Vacciny.setFont(Font.font("Algerian", 15));
		v.setVgap(10);
		v.setHgap(10);
		GridPane.setConstraints(name, 0, 0);
		GridPane.setConstraints(Hp, 0, 3);
		GridPane.setConstraints(Maxy, 1, 3);
		GridPane.setConstraints(type, 0, 1);
		myHealth = new ProgressBar();
		mySupplies = new ProgressBar();
		myVaccines = new ProgressBar();

		GridPane.setConstraints(points, 0, 2);
		// GridPane.setConstraints(Healthy, 0, 3);
		GridPane.setConstraints(myHealth, 1, 3);
		GridPane.setConstraints(mySupplies, 1, 4);
		GridPane.setConstraints(Supplyy, 0, 4);
		GridPane.setConstraints(Vacciny, 0, 5);
		GridPane.setConstraints(myVaccines, 1, 5);
		GridPane.setConstraints(RM, 0, 6);
		v.getChildren().addAll(name, Hp, type, Supplyy, Vacciny, points, RM);

		return v;
	}

	public static void updateDash() { // Progress A7eh
		name.setText("Name: " + getH().getName());
		Hp.setText("HP: " + getH().getCurrentHp() + "/" + getH().getMaxHp());
		points.setText("Action Points: " + getH().getActionsAvailable());
		Supplyy.setText("Supply Indicator: "
				+ getH().getSupplyInventory().size());
		Vacciny.setText("Vaccine indicator: "
				+ getH().getVaccineInventory().size());
		String t = "";
		String rabie = "";
		for (int i = 0; i < Game.heroes.size(); i++) {
			rabie += (i + 1) + " - " + Game.heroes.get(i).toString2();
		}
		RM.setText("Remainig Heroes: " + "\n" + rabie);
		if (getH() instanceof Fighter)
			t = "Fighter";
		else if (getH() instanceof Medic)
			t = "Medic";
		else
			t = "Explorer";
		type.setText("Type: " + t);
	
	}

	public static Scene chooseHero() {
		Scene s = null;
		try {
			s = new Scene(Control.makeAvailableHeroesList(), 1000, 1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	private void handleKeyPressed(KeyEvent event) {        //new
        KeyCode keyCode = event.getCode();

        // Move the rectangle based on the pressed key
        switch (keyCode) {
            case UP:
            	Control.moveGui("up");
                break;
            case DOWN:
            	Control.moveGui("down");
                break;
            case LEFT:
            	Control.moveGui("left");
                break;
            case RIGHT:
            	Control.moveGui("right");
                break;
            default:
                // Ignore other keys
                break;
        }
    }

	
	public static Scene makeScenePlay() {
		try {
			Game.loadHeroes("Heros.csv");
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		EndGame.setWrapText(true);
		Button EndTurn = new Button("End Turn");
		// EndTurn.setPrefSize(100, 100);
		EndTurn.setWrapText(true);
		// cure button
		Button cure = new Button("cure zombie");
		cure.setWrapText(true);
		Button useSpecial = new Button("Special Action");
		useSpecial.setWrapText(true);
		Button attack = new Button("Attack");
		attack.setWrapText(true);
		BorderPane layout2 = new BorderPane();
		layout2.setLeft(makeDashBoard());
		GridPane grid = Game.makeGrid();
		EndTurn.setOnAction(e -> {
			Control.endTurnGui();
			// System.out.println(Game.heroes);
			GUI.updateDash();
			Game.SetHero.setVisible(false);
			Game.SetTrarget.setVisible(false);
		});
		cure.setOnAction(e -> {
			Control.cureGui();
			GUI.updateDash();
			Game.SetHero.setVisible(false);
			Game.SetTrarget.setVisible(false);
		});
		useSpecial.setOnAction(e -> {
			Control.useSpecialGui();
			GUI.updateDash();
			Game.SetHero.setVisible(false);
			Game.SetTrarget.setVisible(false);
		});
		attack.setOnAction(e -> {
			Control.attackGUI();
			GUI.updateDash();
			Game.SetHero.setVisible(false);
			Game.SetTrarget.setVisible(false);
		});
		layout2.setCenter(grid);
		GridPane hbox = new GridPane();
		hbox.setAlignment(Pos.BOTTOM_CENTER); // Set alignment to top center

		//		DOWN = new Button("↓");
//		DOWN.setOnAction(e -> {
//			Control.moveGui("down");
//		});
//		UP = new Button("↑");
//		UP.setOnAction(e -> {
//			Control.moveGui("up");
//		});
//		RIGHT = new Button("→");
//		RIGHT.setOnAction(e -> {
//			Control.moveGui("right");
//		});
//		LEFT = new Button("←");
//		LEFT.setOnAction(e -> {
//			Control.moveGui("left");
//		});

		GridPane.setConstraints(EndGame, 5, 3);
		GridPane.setConstraints(cure, 5, 0);
		GridPane.setConstraints(useSpecial, 4, 0);
		GridPane.setConstraints(attack, 6, 0);
		GridPane.setConstraints(EndTurn, 7, 0);
		GridPane.setConstraints(Game.SetHero, 6, 1); // c
		GridPane.setConstraints(Game.SetTrarget, 5, 1); // c
//		GridPane.setConstraints(UP, 6, 0);
//		GridPane.setConstraints(DOWN, 6, 1);
//		GridPane.setConstraints(LEFT, 5, 1);
//		GridPane.setConstraints(RIGHT, 7, 1);
		hbox.setHgap(20);
		hbox.setVgap(20);
		hbox.setPadding(new Insets(0, 0, 30, 0));
//		hbox.getChildren().addAll(EndGame, cure, useSpecial, attack, EndTurn,
//				Game.SetTrarget, Game.SetHero, UP, DOWN, RIGHT, LEFT);
		hbox.getChildren().addAll(cure, useSpecial, attack, EndTurn,
				Game.SetTrarget, Game.SetHero);
		layout2.setBottom(hbox);

		Image backgroundImage = new Image("msm.jpg");

		// Create a BackgroundSize object to fit the scene
		BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,
				BackgroundSize.AUTO, true, true, true, false);

		// Create a BackgroundImage object with the image, repeat option, and
		// sizing
		BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, backgroundSize);

		// Create a Background object with the background image
		Background background = new Background(backgroundImg);
		// System.out.println(Game.availableHeroes);
		layout2.setBackground(background);
		Scene s = new Scene(layout2, 1000, 1000);
		return s;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});

		// setting background
		// Create the background image object
		Image backgroundImage = new Image("Sunny.jpg");
		Image image = new Image("Blue.jpg");
		BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		
		// scene1
		// Create the background
		Background background = new Background(backgroundImg);

		// setting icon
		Image logoImage = new Image("Blue.jpg");
		// Replace with the actual path to your logo image
		primaryStage.getIcons().add(logoImage);

		startGame = new Button("Start the Game");
		startGame.setPrefHeight(50);
		startGame.setPrefWidth(200);
		// startGame.setBackground(myStart);
		EndGame = new Button("Quit");
		EndGame.setOnAction(e -> {
			closeProgram();
		});
		Quit = new Button("Quit"); // //////////c
		Quit.setPrefSize(200, 50);
		Quit.setOnAction(e -> { // //////////c
			closeProgram(); // //////////c
		});

		VBox layout1 = new VBox(15);
		layout1.setAlignment(Pos.CENTER);
		layout1.setBackground(background);

		layout1.getChildren().addAll(startGame, Quit);

		Scene s1 = new Scene(layout1, 1000, 1000);
		// scene2
		startGame.setOnAction(e -> primaryStage.setScene(chooseHero()));
		s2 = makeScenePlay();
		
		//move using keyboard
		s2.setOnKeyPressed(this::handleKeyPressed);     //new
	
		Control.mapUpdate();
		primaryStage.setTitle("The Last of Us");
		primaryStage.setScene(s1);
		primaryStage.show();
	}

	public static Stage getWindow() {
		return window;
	}

	public static void main(String[] args) throws IOException {
		launch(args);

	}

	private void closeProgram() {
		Boolean res = Confirm.display("Closing", "Are you sure?");
		if (res)
			window.close();
	}


}