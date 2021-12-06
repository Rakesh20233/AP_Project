package com.example.javafx_using_scenebuilder;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
		try {
			ViewManager view = new ViewManager();
			stage = view.getStage();
			stage.show();
		} catch (Exception e){
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
