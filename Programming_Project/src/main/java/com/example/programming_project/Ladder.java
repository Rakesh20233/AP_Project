package com.example.programming_project;

public class Ladder {
	private int start_loc_x, start_loc_y, finish_loc_x, finish_loc_y;

	public Ladder(int start_x, int start_y, int finish_x, int finish_y){
		start_loc_x = start_x;
		start_loc_y = start_y;
		finish_loc_x = finish_x;
		finish_loc_y = finish_y;
	}

	public final int getstartx() { return start_loc_x; }
	public final int getstarty() { return start_loc_y; }
	public final int getfinishx() { return finish_loc_x; }
	public final int getfinishy() { return finish_loc_y; }
}
