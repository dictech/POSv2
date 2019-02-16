package com.pos.account.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateLoginView extends Application {

	public static void main(String[] args) {
	
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root,1366,700);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Check Point");
		stage.setFullScreen(true);
		stage.show();
	}
	
}
