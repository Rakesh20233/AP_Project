package com.example.programming_project;

public class GameLogic {
	// this is where the game thrives and comes to life

	private Player pl1;
	private Player pl2;

	private boolean chance;

	public GameLogic(){
		pl1 = new Player(0);
		pl2 = new Player(0);
	}

	public void gameloop(){
		while(!checkendGame()){
		}
	}

	private boolean checkendGame(){
		if (pl1.wins || pl2.wins){
			return true;
		}
		return false;
	}
}
