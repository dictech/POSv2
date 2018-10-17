package com.pos.account.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConfirmationScrView extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		     Parent root = FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
		     Scene scene = new Scene(root,500,520);
		     stage.setScene(scene);
		     stage.show();
		
	}

	  
	    
}
