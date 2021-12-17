package com.example.programming_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Scene4_Comp{
	Random random = new Random();

	@FXML
	private ImageView Rolling_Dice_Img;

	@FXML
	private Button Roll_bn;

	public void setonClickRoll(ActionEvent e){
		/* System.out.println("Setting gif"); */
		Image img = new Image(getClass().getResourceAsStream("rolling_dices.gif"));
		Rolling_Dice_Img.setImage(img);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie){
			ie.printStackTrace();
		}

		switch(getRandomRoll()){
			case 1:
				img = new Image(getClass().getResourceAsStream("diceroll_1.png"));
				Rolling_Dice_Img.setImage(img);
				break;
			case 2:
				img = new Image(getClass().getResourceAsStream("diceroll_2.png"));
				Rolling_Dice_Img.setImage(img);
				break;
			case 3:
				img = new Image(getClass().getResourceAsStream("diceroll_3.png"));
				Rolling_Dice_Img.setImage(img);
				break;
			case 4:
				img = new Image(getClass().getResourceAsStream("diceroll_4.png"));
				Rolling_Dice_Img.setImage(img);
				break;
			case 5:
				img = new Image(getClass().getResourceAsStream("diceroll_5.png"));
				Rolling_Dice_Img.setImage(img);
				break;
			case 6:
				img = new Image(getClass().getResourceAsStream("diceroll_6.png"));
				Rolling_Dice_Img.setImage(img);
				break;
			default:
				Rolling_Dice_Img.setImage(img);
		}
	}

	private int getRandomRoll(){
		return random.nextInt(1, 6);
	}
}
