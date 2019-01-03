package com.pos.payment.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PaymentView extends Application{

	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("../view/PaymentView.fxml"));
		Scene scene = new Scene(root,894,561);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Payment Section");
	
		
	}

	
	     public static void main(String args[]) {
	    	 
	    	 launch();
	     }
	
}
