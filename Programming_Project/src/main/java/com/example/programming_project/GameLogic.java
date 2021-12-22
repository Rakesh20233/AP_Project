package com.example.programming_project;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GameLogic {
	public Player pl1;
	public Player pl2;

	private boolean chance;

	ArrayList<Snakes> listOfSnakes;
	ArrayList<Ladder> listOfLadders;

	public GameLogic(ImageView pl1_image, ImageView pl2_image){
		System.out.println("Game logic constructor called");
		if (pl1_image == null || pl2_image == null){
			System.out.println("Sending a null image to pl1 & pl2");
		}
		pl1 = new Player(0, 9, pl1_image, "p1");
		pl2 = new Player(0, 9, pl2_image, "p2");
		chance = true;

		listOfSnakes = new ArrayList<>();
		listOfLadders = new ArrayList<>();

		loadSnakes();
		loadLadders();
	}

	private Snakes findInSnakes(int sx, int sy){
		for(Snakes e : listOfSnakes){
			if (e.getmouthx() == sx && e.getmouthy() == sy){
				return e;
			}
		}
		System.out.println("No snake found");
		return null;
	}
	private Ladder findInLadder(int sx, int sy){
		for(Ladder e : listOfLadders){
			if (e.getstartx() == sx && e.getstarty() == sy){
				return e;
			}
		}
		System.out.println("No ladder found");
		return null;
	}

	private void loadSnakes(){
		listOfSnakes.add(new Snakes(1, 0, 0, 2));
		listOfSnakes.add(new Snakes(1, 3, 1, 8));
		listOfSnakes.add(new Snakes(5, 0, 5, 2));
		listOfSnakes.add(new Snakes(3, 3, 0, 4));
		listOfSnakes.add(new Snakes(5, 5, 4, 7));
		listOfSnakes.add(new Snakes(4, 8, 5, 9));
		listOfSnakes.add(new Snakes(6, 2, 7, 4));
		listOfSnakes.add(new Snakes(8, 0, 7, 1));
		listOfSnakes.add(new Snakes(8, 5, 9, 8));
	}

	private void loadLadders(){
		listOfLadders.add(new Ladder(1, 9, 2, 6));
		listOfLadders.add(new Ladder(0, 7, 1, 5));
		listOfLadders.add(new Ladder(2, 2, 2, 0));
		listOfLadders.add(new Ladder(4, 6, 3, 5));
		listOfLadders.add(new Ladder(5, 8, 5, 7));
		listOfLadders.add(new Ladder(6, 9, 6, 8));
		listOfLadders.add(new Ladder(6, 1, 6, 0));
		listOfLadders.add(new Ladder(7, 9, 9, 6));
		listOfLadders.add(new Ladder(7, 7, 3, 1));
		listOfLadders.add(new Ladder(9, 4, 6, 3));
		listOfLadders.add(new Ladder(9, 2, 9, 0));
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

	public boolean[] checkendGame(){
		boolean [] winrate = {false, false};
		if (pl1.wins){
			System.out.println("pl1 wins");
			winrate[0] =true;
			winrate[1] = true;
			return winrate;
		}else if (pl2.wins){
			System.out.println("comp wins");
			winrate[0] = true;
			winrate[1] = false;
			return winrate;
		}
		return winrate;
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

	public boolean OnSnake(Player pl){
		Snakes tmp = findInSnakes(pl.x_location, pl.y_location);
		if (tmp != null){
			pl.x_location = tmp.gettailx();
			pl.y_location = tmp.gettaily();
			if (pl.y_location%2 == 0){
				pl.setDirection(false);
			}else {
				pl.setDirection(true);
			}
			return true;
		}
		return false;
	}
	public boolean OnLadder(Player pl){
		Ladder tmp = findInLadder(pl.x_location, pl.y_location);
		if (tmp != null){
			pl.x_location = tmp.getfinishx();
			pl.y_location = tmp.getfinishy();
			if (pl.y_location%2 == 0){
				pl.setDirection(false);
			}else {
				pl.setDirection(true);
			}
			return true;
		}
		return false;
	}

	public boolean Collision(Player come, GridPane game_board){
		Player stand;
		if (come.getId().equals("p1")){
			stand = pl2;
		}else {
			stand = pl1;
		}

		if (come.x_location == stand.x_location && come.y_location == stand.y_location){
			stand.x_location = 0;
			stand.y_location = 9;
			stand.setDirection(true);
			game_board.getChildren().remove(stand.getPawn());
			game_board.add(stand.getPawn(), stand.x_location, stand.y_location);
			return true;
		}
		return false;
	}
}
