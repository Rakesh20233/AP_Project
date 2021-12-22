package com.example.programming_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import static com.example.programming_project.Scene2.*;

public class Scene3 {

    @FXML
    AnchorPane Scene3Anchor;

	@FXML
	private Button exit_bn;

	@FXML
	private Button VS_Computer_bn;

	@FXML
	private Button Settings_bn;

	@FXML
	private Button Two_Player_bn;

	Stage tmpStage;

	public void setonClickExit(ActionEvent e){
		tmpStage = (Stage) Scene3Anchor.getScene().getWindow();
		System.out.println("Successfully exited...");
		tmpStage.close();
	}

	public void setonClickVS_Comp(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Scene4_Game.fxml"));
        tmpStage = (Stage) Scene3Anchor.getScene().getWindow();
        Scene scene = new Scene(root);
        tmpStage.setScene(scene);
        tmpStage.show();
	}

	public void pauseMedia() {

		Scene2.mediaPlayer.pause();
	}

	public void nextMedia(){

		if (songNumber < songs.size()-1){
			songNumber++;
			Scene2.mediaPlayer.dispose();

			media = new Media(songs.get(songNumber).toURI().toString());
			Scene2.mediaPlayer = new MediaPlayer(media);
			Scene2.mediaPlayer.getOnRepeat();
			Scene2.mediaPlayer.setAutoPlay(true);

		}

		else if (songNumber == songs.size()-1){
			songNumber = 0;
			Scene2.mediaPlayer.dispose();

			media = new Media(songs.get(songNumber).toURI().toString());
			Scene2.mediaPlayer = new MediaPlayer(media);
			Scene2.mediaPlayer.getOnRepeat();
			Scene2.mediaPlayer.setAutoPlay(true);
		}
	}
}
