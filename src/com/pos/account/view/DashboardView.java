package com.pos.account.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardView extends Application {
    
	Stage window;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		window = arg0;
		
		Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		Scene scene =  new Scene(root,800,800);
		window.setScene(scene);
		window.show();
	}

}
