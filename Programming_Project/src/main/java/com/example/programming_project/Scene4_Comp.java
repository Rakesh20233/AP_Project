package com.example.programming_project;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.example.programming_project.Scene2.*;

public class Scene4_Comp implements Initializable {
	Random random = new Random();

	@FXML
	private ImageView Rolling_Dice_Img;

	@FXML
	public Button back_bn;

	@FXML
	private ImageView Final_Roll;

	@FXML 
	public Label Status;

	@FXML
	private Button Roll_bn;

	@FXML
	private ImageView player_pawn;

	@FXML
	private ImageView comp_pawn;

	@FXML
	private GridPane game_board;

	@FXML public Label message;

	@FXML private AnchorPane Scene4Anchor;

	private GameLogic game;
	private boolean flag;
	private boolean flag2 = true;
	public static String wins;

	public GameLogic getGame() { return game; }
	public GridPane getBoard() { return game_board;}

	public void setOnClickBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene3.fxml")));
        Stage stage = (Stage) Scene4Anchor.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle r){
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


	public void pauseMedia() {
		if (flag2){
			Scene2.mediaPlayer.pause();
			flag2 = false;
		}
		else{
			Scene2.mediaPlayer.play();
			flag2 = true;
		}
	}

	public void nextMedia(){

		if (songNumber < songs.size()-1){
			songNumber++;
			Scene2.mediaPlayer.dispose();

			media = new Media(songs.get(songNumber).toURI().toString());
			Scene2.mediaPlayer = new MediaPlayer(media);
			Scene2.mediaPlayer.getOnRepeat();
			Scene2.mediaPlayer.setAutoPlay(true);

		}

		else if (songNumber == songs.size()-1){
			songNumber = 0;
			Scene2.mediaPlayer.dispose();

			media = new Media(songs.get(songNumber).toURI().toString());
			Scene2.mediaPlayer = new MediaPlayer(media);
			Scene2.mediaPlayer.getOnRepeat();
			Scene2.mediaPlayer.setAutoPlay(true);
		}
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
				/* Bounds og_xy = player_pawn.localToScene(player_pawn.getLayoutBounds()); */
				/* GridPane.setRowIndex(player_pawn, pl.y_location); */
				/* GridPane.setColumnIndex(player_pawn, pl.x_location); */
				/* Bounds final_xy = game_board.localToScene(game_board.getCellBounds(pl.x_location, pl.y_location)); */

				/* path_transition(og_xy, final_xy); */
				game_board.add(player_pawn, pl.x_location, pl.y_location);
			}else{
				// Bounds og_xy = player_pawn.getBoundsInLocal();
				pl.move(roll);
				game_board.getChildren().remove(player_pawn);
				game_board.add(player_pawn, pl.x_location, pl.y_location);
				// Bounds final_xy = game_board.getCellBounds(pl.y_location, pl.x_location);
                //
				// path_transition(og_xy, final_xy);
				// give another move to player
			}
		}else{
			if (pl.started){
				// Bounds og_xy = player_pawn.getBoundsInLocal();
				pl.move(roll);
				// Bounds final_xy = game_board.getCellBounds(pl.y_location, pl.x_location);
				// path_transition(og_xy, final_xy);
				game_board.getChildren().remove(player_pawn);
				game_board.add(player_pawn, pl.x_location, pl.y_location);

				Platform.runLater(new Runnable() {
					public void run(){
						if (game.OnSnake(pl)){
							game_board.getChildren().remove(player_pawn);
							game_board.add(player_pawn, pl.x_location, pl.y_location);
						}
						if (game.OnLadder(pl)){
							game_board.getChildren().remove(player_pawn);
							game_board.add(player_pawn, pl.x_location, pl.y_location);
						}
						if (game.Collision(pl, game_board)){
							System.out.println("Collision occurred");
							game.toggleChance();
						}
					}
				});
			}
			game.toggleChance();
		}


		boolean[] win_pl = game.checkendGame();
		if (win_pl[0]){
			if (win_pl[1]){
				Scene4_Comp.wins = "Congratulations you won";
			}else{
				Scene4_Comp.wins = "Computer Wins";
			}
			try {
				Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Win.fxml")));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		if (!game.getChance() && !win_pl[0]){
			Platform.runLater(new moveTheComp(cur_scene));
		}
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

		// Line pthMove = new Line();
		// pthMove.setStartX(getCenter(og_xy, true));
		// pthMove.setStartY(getCenter(og_xy, false));
		// pthMove.setEndX(getCenter(final_xy, true));
		// pthMove.setEndY(getCenter(final_xy, false));

		Path pthMove = new Path();
		pthMove.getElements().add(new MoveTo(getCenter(og_xy, true), getCenter(og_xy, false)));
		pthMove.getElements().add(new LineTo(getCenter(final_xy, true), getCenter(final_xy, false)));

		PathTransition path = new PathTransition();
		path.setDuration(Duration.seconds(3));
		path.setPath(pthMove);
		path.setNode(player_pawn);
		path.setCycleCount(2);
		path.setAutoReverse(true);
		path.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent a){
				System.out.println("Done playing");
				Bounds tmp = player_pawn.localToScene(player_pawn.getBoundsInLocal());
				System.out.println("Player pos: "+getCenter(tmp, true)+" "+getCenter(tmp, false));
				// if (!game.getChance())
				//     Platform.runLater(new moveTheComp(cur_scene));
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
