package com.example.programming_project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SceneWin implements Initializable{
	@FXML
	private Label message;

	@Override
	public void initialize(URL url, ResourceBundle r){
		System.out.println("running this Scene4_Comp: "+Scene4_Comp.wins);
		if (message != null){
			message.setText(Scene4_Comp.wins);
		}else{
			System.out.println("Still null");
		}
	}
}
