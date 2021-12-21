package com.example.programming_project;

import javafx.scene.image.ImageView;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> 62feb5ce8e528cf18822fc41359584532511f477
public class GameLogic {
	public Player pl1;
	public Player pl2;

	private boolean chance;

<<<<<<< HEAD
=======
	ArrayList<Snakes> listOfSnakes;
	ArrayList<Ladder> listOfLadders;

>>>>>>> 62feb5ce8e528cf18822fc41359584532511f477
	public GameLogic(ImageView pl1_image, ImageView pl2_image){
		System.out.println("Game logic constructor called");
		if (pl1_image == null || pl2_image == null){
			System.out.println("Sending a null image to pl1 & pl2");
		}
		pl1 = new Player(0, 9, pl1_image);
		pl2 = new Player(0, 9, pl2_image);
		chance = true;
<<<<<<< HEAD
=======

		listOfSnakes = new ArrayList<>();
		listOfLadders = new ArrayList<>();
>>>>>>> 62feb5ce8e528cf18822fc41359584532511f477
	}

	public boolean getChance() { return chance; }
	public void setChance(boolean c) { chance = c; }

	public void toggleChance(){
		if (chance){
			chance = false;
		}else{
			chance = true;
		}
	}

	public boolean checkendGame(){
		if (pl1.wins){
			System.out.println("pl1 wins");
			return true;
		}else if (pl2.wins){
			System.out.println("comp wins");
			return true;
		}
		return false;
	}

	public void setPlayerImages(ImageView pl1_image, ImageView pl2_image){
		if (pl1_image==null){
			System.out.println("pl1 image still null");
		}
		if (pl1.getPawn() == null){
			pl1.setPawn(pl1_image);
		}
		if (pl2.getPawn() == null){
			pl2.setPawn(pl2_image);
		}
	}
}
