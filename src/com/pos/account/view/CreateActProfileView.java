package com.pos.account.view;
<<<<<<< HEAD
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateActProfileView extends Application {
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("CreateActProfileView.fxml"));
		Scene scene = new Scene(root,726,593);
		stage.setScene(scene);
		stage.setTitle("Your Account Infomations");
		stage.show();	
	}

	  public static void main(String [] args) {
		  
		    launch();
	  }

	
=======

public class CreateActProfileView {
>>>>>>> 5963731145b31b34751db59d60d8910e7c07fd77

}
