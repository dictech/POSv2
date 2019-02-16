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

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginCtrl implements Initializable {
	
	
	

        @FXML
        private PasswordField password;

        @FXML
        private TextField userName;
	    
	    @FXML
	    private Text validation;

	    @FXML
	    private Button btnLogIn;
	    
	    private  String u_name;
	     
	    private String pass;
	     
	    public String getU_name() {
			return u_name;
		}

		public void setU_name(String u_name) {
			this.u_name = u_name;
		}

		public String getPass() {
			return pass;
		}


		public void setPass(String pass) {
			this.pass = pass;
		}


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
	    		
                        this.validation.setText(" Please Enter Username and Password !");
	          }
	    	
	    	
	    	else {
        	    	SystemAccountDAO.logInSystemAccount(event,userName.getText(), password.getText());
        	    	this.validation.setText(SystemAccountDAO.getValidationstatus());
	    	        this.setU_name(userName.getText());
	    	        this.setPass(password.getText());
	    	         
                    
	    	}

	    	        }
	    
	             
	          public void exitLoginScreen() throws Exception{

	        	      
	             }
	    
       }

