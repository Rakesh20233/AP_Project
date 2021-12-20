package com.example.programming_project;

import javafx.scene.image.ImageView;

public class Player {
	public int x_location;
	public int y_location;
	public boolean wins;
	public boolean started;
	private boolean direction; // true = right

	private ImageView img;

	public ImageView getPawn() { return img; }
	public void setPawn(ImageView img) { this.img = img; }

	Player(int xloc, int yloc, ImageView i){
		this.x_location = xloc;
		this.y_location = yloc;
		this.wins = false;
		this.started = false;
		this.direction = true;
		this.img = i;
	}

	public void start(){
		started = true;
	}

	public void move(int moveby){
		if (direction){
			if (x_location + moveby > 9){
				y_location--;
				x_location = 19-(x_location+moveby);
				direction = false;
			} else{
				x_location += moveby;
			}
		}
		else {
			if (x_location - moveby < 0 && y_location-1 >= 0){
				y_location--;
				x_location = Math.abs(1+(x_location-moveby));
				direction = true;
			}else if (y_location-1 >= 0){
				x_location -= moveby;
			}
		}
	}
}
