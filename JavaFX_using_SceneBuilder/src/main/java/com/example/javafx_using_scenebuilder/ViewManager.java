package com.example.javafx_using_scenebuilder;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ViewManager{
	private int width = 320;
	private int height = 240;

	private Stage main_stage;
	private Scene main_scene;
	private Parent root;

	ViewManager() throws IOException{
		root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
		main_stage = new Stage();
		main_scene = new Scene(root, width, height);

		main_stage.setTitle("Hello World");
		main_stage.setScene(main_scene);
	}

	public final Stage getStage() { return this.main_stage; }
}
