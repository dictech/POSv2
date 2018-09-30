package com.pos.account.model;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class loginForm extends Application{
	
	public void start(Stage stage) throws Exception {
		
		Button btn =  new Button("Login");
		Pane root = new Pane();
		Scene scene = new Scene(root,300,400);
		root.getChildren().addAll(btn);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String arg[]) {
		launch(arg);
	}

}
