package com.example.programming_project;


public class Snakes {
	private int mouth_loc_x, mouth_loc_y, tail_loc_x, tail_loc_y;

	public Snakes(int sx,int sy, int fx, int fy){
		mouth_loc_x = sx;
		mouth_loc_y = sy;
		tail_loc_x = fx;
		tail_loc_y = fy;
	}

	public final int getmouthx() {return mouth_loc_x;}
	public final int getmouthy() {return mouth_loc_y;}
	public final int gettailx() {return tail_loc_x;}
	public final int gettaily() {return tail_loc_y;}
}
