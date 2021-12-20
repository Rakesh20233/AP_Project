package com.example.programming_project;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	private GameLogic game = new GameLogic();

	public void setonClickRoll(ActionEvent e){
		ImageView[] slides = new ImageView[2];

		Image img = new Image(getClass().getResourceAsStream("rolling_dices.gif"));
		Rolling_Dice_Img.setImage(img);
		slides[0] = Rolling_Dice_Img;

		switch(random.nextInt(1, 6)){
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
				img = new Image(getClass().getResourceAsStream("diceroll_4.jpg"));
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
	}

	private void playTransitions(ImageView[] slides){
		SequentialTransition slideshow = new SequentialTransition();

		for(ImageView slide : slides){
			SequentialTransition sequentialTransition = new SequentialTransition();

			PauseTransition pause = new PauseTransition(Duration.millis(1000));
			FadeTransition fadeout = getFadeTransition(slide, 1.0, 0.0, 500);

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
