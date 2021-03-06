package com.example.programming_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Scene2 implements Initializable {

    private Parent root;
    private Scene scene;
    private Stage stage;

    public void to_hello_view(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private File directory;
    private File[] files;

	private boolean flag = true;

    static ArrayList<File> songs;
//    private boolean running;

    static Media media;
    static MediaPlayer mediaPlayer;

    static int songNumber;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        songs = new ArrayList<File>();
        directory = new File("Music");
        files = directory.listFiles();

        if(files != null) {

            for(File file : files) {

                songs.add(file);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
//
    }

    public void pauseMedia() {
		if (flag){
			mediaPlayer.pause();
			flag = false;
		}
		else{
			mediaPlayer.play();
			flag = true;
		}
    }

    public void nextMedia(){

        if (songNumber < songs.size()-1){
            songNumber++;
            mediaPlayer.dispose();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.getOnRepeat();
            mediaPlayer.setAutoPlay(true);

        }

        else if (songNumber == songs.size()-1){
            songNumber = 0;
            mediaPlayer.dispose();

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.getOnRepeat();
            mediaPlayer.setAutoPlay(true);
        }
    }
}
