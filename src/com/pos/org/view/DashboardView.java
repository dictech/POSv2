package com.pos.org.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardView extends Application{

	Stage window;
	

	@Override
	public void start(Stage arg0) throws Exception {
		window = arg0;
		
	}
	  


public void startDashBoard() throws Exception {		
	
	Parent root = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
	Scene scene =  new Scene(root,1366,800);
	
	window = new Stage();
	window.setScene(scene);
	window.setFullScreen(true);
	window.show(); 
}

}