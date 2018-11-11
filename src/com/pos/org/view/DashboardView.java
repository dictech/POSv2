package com.pos.org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardView extends Application {
    
	
	public static void main(String args[]) {
		launch(args);
	}
	
	Stage window;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		window = arg0;
		
		Parent root = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
		Scene scene =  new Scene(root,1280,800);
		window.setScene(scene);
		window.show(); 
	}
	


}
