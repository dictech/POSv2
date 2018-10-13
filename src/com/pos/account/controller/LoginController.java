package com.pos.account.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pos.account.model.SystemAccountDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	   @FXML
	    private TextField userName;

	    @FXML
	    private TextField password;

	    @FXML
	    private Button btnLogIn;



	    @FXML
	    void createAccount(ActionEvent event) {

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// Connect to Database
		}
	
		
	    @FXML
	    void loginAccount(ActionEvent event) throws Exception {
	    	
	    	 
	    	if(userName.getText().isEmpty() || password.getText().isEmpty()) {
	    		
	                  Alert alert = new Alert(AlertType.WARNING);
	                  alert.setContentText("please Enter the required fields !");
	                  alert.showAndWait();}
	    	
	    	
	    	else {
        	    	SystemAccountDAO.logInSystemAccount(event,userName.getText(), password.getText());}
	    	
	    	
	  
	    	        }
	    
	    
	                        public void homePage(ActionEvent event) throws Exception {
	                    	 
	                    	Parent root = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
	             	    	Scene scene =  new Scene(root,400,400);
	             	    	Stage stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
	             	    	stage.setScene(scene);
	             	    	stage.show();}
	    
	                        
	                        
                       }

