package com.example.programming_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

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
}
