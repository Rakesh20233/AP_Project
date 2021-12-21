package com.example.programming_project;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Random;

import org.controlsfx.control.spreadsheet.Grid;

public class Scene4_Comp {
	Random random = new Random();

	@FXML
	private ImageView Rolling_Dice_Img;

	@FXML
	private ImageView Final_Roll;

	@FXML
	private Button Roll_bn;

	@FXML
	private ImageView player_pawn;

	@FXML
	private ImageView comp_pawn;

	@FXML
	private GridPane game_board;

	private GameLogic game;
	private boolean flag;

	public GameLogic getGame() { return game; }
	public GridPane getBoard() { return game_board;}

	public Scene4_Comp (){
		game = new GameLogic(player_pawn, comp_pawn);
		flag = true;
	}

	public void setonClickRoll(ActionEvent e){
		ImageView[] slides = new ImageView[2];

		Image img = new Image(getClass().getResourceAsStream("rolling_dices.gif"));
		Rolling_Dice_Img.setImage(img);
		slides[0] = Rolling_Dice_Img;

		int roll = random.nextInt(1, 7);

		if (flag){
			game.setPlayerImages(player_pawn, comp_pawn);
			roll = 6;
			flag = false;
		}

		switch(roll){
			case 1:
				System.out.println("Got 1");
				img = new Image(getClass().getResourceAsStream("diceroll_1.png"));
				Final_Roll.setImage(img);
				slides[1] = Final_Roll;
				break;
			case 2:
				System.out.println("Got 2");
				img = new Image(getClass().getResourceAsStream("diceroll_2.png"));
				Final_Roll.setImage(img);
				slides[1] = Final_Roll;
				break;
			case 3:
				System.out.println("Got 3");
				img = new Image(getClass().getResourceAsStream("diceroll_3.png"));
				Final_Roll.setImage(img);
				slides[1] = Final_Roll;
				break;
			case 4:
				System.out.println("Got 4");
				img = new Image(getClass().getResourceAsStream("gimpfiles/diceroll_4.png"));
				Final_Roll.setImage(img);
				slides[1] = Final_Roll;
				break;
			case 5:
				System.out.println("Got 5");
				img = new Image(getClass().getResourceAsStream("diceroll_5.png"));
				Final_Roll.setImage(img);
				slides[1] = Final_Roll;
				break;
			case 6:
				System.out.println("Got 6");
				img = new Image(getClass().getResourceAsStream("diceroll_6.png"));
				Final_Roll.setImage(img);
				slides[1] = Final_Roll;
				break;
		}
		playTransitions(slides);

		if (game.getChance())
			Platform.runLater(new moveThePlayer(roll, this, game.pl1));
		else
			Platform.runLater(new moveThePlayer(roll, this, game.pl2));
	}

	private void playTransitions(ImageView[] slides){
		SequentialTransition slideshow = new SequentialTransition();

		for(ImageView slide : slides){
			SequentialTransition sequentialTransition = new SequentialTransition();

			PauseTransition pause = new PauseTransition(Duration.millis(1000));
			FadeTransition fadeout = getFadeTransition(slide, 1.0, 0.0, 1000);

			sequentialTransition.getChildren().addAll(pause, fadeout);
			slideshow.getChildren().add(sequentialTransition);
		}

		slideshow.play();
	}

	private FadeTransition getFadeTransition(ImageView img, double fromValue, double toValue, int duration){
		FadeTransition tmp = new FadeTransition(Duration.millis(duration), img);
		tmp.setToValue(toValue);
		tmp.setFromValue(fromValue);
		return tmp;
	}
}

class moveThePlayer implements Runnable {
	int roll;
	GameLogic game;
	GridPane game_board;
	ImageView player_pawn;
	Player pl;
	Scene4_Comp cur_scene;

	moveThePlayer(int r, Scene4_Comp s, Player p){
		cur_scene = s;
		roll = r;
		game = s.getGame();
		game_board = s.getBoard();
		pl = p;
		player_pawn = pl.getPawn();
		if (player_pawn == null){
			System.out.println("Couldn't get image for player");
		}
	}

	@Override
	public void run() {
		try{
			Thread.sleep(1000);
		} catch (Exception e){
			e.printStackTrace();
		}
		if (roll == 6){
			if (!pl.started){
				pl.start();
				game_board.add(player_pawn, pl.x_location, pl.y_location);
				// give another move
			}else{
				pl.move(roll);
				game_board.getChildren().remove(player_pawn);
				game_board.add(player_pawn, pl.x_location, pl.y_location);
				// give another move to player
			}
		}else{
			if (pl.started){
				pl.move(roll);
				game_board.getChildren().remove(player_pawn);
				game_board.add(player_pawn, pl.x_location, pl.y_location);
			}
			game.toggleChance();
		}
		if (!game.getChance())
			Platform.runLater(new moveTheComp(cur_scene));
	}

	// private void path_transition(int cur_x, int cur_y, int new_x, int new_y){
	//     // deal with this later
	// }
}

class moveTheComp implements Runnable {
	Scene4_Comp cur_scene;
	moveTheComp (Scene4_Comp sc){
		cur_scene = sc;
	}

	@Override
	public void run(){
		System.out.println("Computer's turn...");
		cur_scene.setonClickRoll(new ActionEvent());
	}
}
