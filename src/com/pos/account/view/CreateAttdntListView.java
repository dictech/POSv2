package com.pos.account.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateAttdntListView extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		
		  Parent root = FXMLLoader.load(getClass().getResource("CreateAttdntList.fxml"));
		  Scene scene = new Scene(root,700,600);
		  arg0.setScene(scene);
		  arg0.show();
		
	}

	public static void main(String [] args) {
		
		  launch(args);
	}
	     
}
