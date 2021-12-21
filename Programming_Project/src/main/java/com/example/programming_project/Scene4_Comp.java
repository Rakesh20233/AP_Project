package com.example.programming_project;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;

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

		playTransitions(slides, roll);
	}

	private void playTransitions(ImageView[] slides, int roll){
		SequentialTransition slideshow = new SequentialTransition();

		for(ImageView slide : slides){
			SequentialTransition sequentialTransition = new SequentialTransition();

			PauseTransition pause = new PauseTransition(Duration.millis(1000));
			FadeTransition fadeout = getFadeTransition(slide, 1.0, 0.0, 500);

			sequentialTransition.getChildren().addAll(pause, fadeout);
			slideshow.getChildren().add(sequentialTransition);
		}

		Scene4_Comp tmpScene = this;

		slideshow.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (game.getChance()){
					Platform.runLater(new moveThePlayer(roll, tmpScene, game.pl1));
				}else {
					Platform.runLater(new moveThePlayer(roll, tmpScene, game.pl2));
				}
			}
		});

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
				/* game_board.add(player_pawn, pl.x_location, pl.y_location); */
				Bounds og_xy = player_pawn.getBoundsInLocal();
				Bounds final_xy = game_board.getCellBounds(0, 9);

				path_transition(og_xy, final_xy);
				// give another move
			}else{
				/* Bounds og_xy = game_board.getCellBounds(pl.x_location, pl.y_location); */
				Bounds og_xy = player_pawn.getBoundsInLocal();
				/* getCenter(og_xy); */
				pl.move(roll);
				/* game_board.getChildren().remove(player_pawn); */
				/* game_board.add(player_pawn, pl.x_location, pl.y_location); */
				Bounds final_xy = game_board.getCellBounds(pl.x_location, pl.y_location);
				/* game_board.getChildren().remove(player_pawn); */
				/* getCenter(final_xy); */

				path_transition(og_xy, final_xy);

				// give another move to player
			}
		}else{
			if (pl.started){
				/* Bounds og_xy = game_board.getCellBounds(pl.x_location, pl.y_location); */
				Bounds og_xy = player_pawn.getBoundsInLocal();
				/* getCenter(og_xy); */
				pl.move(roll);
				/* game_board.getChildren().remove(player_pawn); */
				/* game_board.add(player_pawn, pl.x_location, pl.y_location); */
				Bounds final_xy = game_board.getCellBounds(pl.x_location, pl.y_location);
				/* getCenter(final_xy); */
				/* game_board.getChildren().remove(player_pawn); */
				path_transition(og_xy, final_xy);
			}
			game.toggleChance();
		}
		/* if (!game.getChance()) */
		/*     Platform.runLater(new moveTheComp(cur_scene)); */
	}

	private double getCenter(Bounds xy, boolean x){
		if (x){
			System.out.println("X is: "+(xy.getMaxX()+xy.getMinX())/2);
			return (xy.getMaxX()+xy.getMinX())/2;
		}
		System.out.println("Y is: "+(xy.getMaxY()+xy.getMinY())/2);
		return (xy.getMaxY()+xy.getMinY())/2;
	}

	private void path_transition(Bounds og_xy, Bounds final_xy){
		System.out.println("Starting to transition");
		/* Path pthMove = new Path(); */
		/* pthMove.getElements().add(new MoveTo(cur_x, cur_y)); */
		/* pthMove.getElements().add(new LineTo(new_x, new_y)); */

		Line pthMove = new Line(getCenter(og_xy, true), getCenter(og_xy, false), getCenter(final_xy, true), getCenter(final_xy, false));
		/* pthMove.toFront(); */

		PathTransition path = new PathTransition();
		path.setDuration(Duration.seconds(3));
		path.setPath(pthMove);
		path.setNode(player_pawn);
		path.setCycleCount(2);
		path.setAutoReverse(true);
		path.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a){
				/* game_board.add(player_pawn, pl.x_location, pl.y_location); */
				System.out.println("Done playing");
				Bounds tmp = player_pawn.localToScene(player_pawn.getBoundsInLocal());
				System.out.println("Player pos: "+getCenter(tmp, true)+" "+getCenter(tmp, false));
				if (!game.getChance())
					Platform.runLater(new moveTheComp(cur_scene));
			}
		});
		System.out.println("Playing the path");
		path.play();
	}
}

class moveTheComp implements Runnable {
	Scene4_Comp cur_scene;
	moveTheComp (Scene4_Comp sc){
		cur_scene = sc;
	}

	@Override
	public void run(){
		try {
			Thread.sleep(1000);
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("Computer's turn...");
		cur_scene.setonClickRoll(new ActionEvent());
	}
}
