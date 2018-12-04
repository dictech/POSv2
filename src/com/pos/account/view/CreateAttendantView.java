package com.pos.account.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateAttendantView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("createAttendant.fxml"));
		Scene scene = new Scene(root,1082,733);
		stage.setScene(scene);
		stage.show();	
	}

	  public static void main(String [] args) {
		  
		    launch();
	  }
}
