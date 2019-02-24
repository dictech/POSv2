package com.pos.account.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import com.pos.account.model.SystemAccountDAO;
import com.pos.account.view.CreateLoginView;

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
import javafx.scene.control.ButtonType;
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
	    
	    @FXML
	    private Button btn_cancel_login;
	     
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



		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// Connect to Database
			btn_cancel_login.setOnMouseClicked(e ->{
				
				  Alert alert = new Alert(AlertType.CONFIRMATION);
				  alert.setContentText("Do you want to close the system ?");
				  alert.setHeaderText(null);
				  alert.setTitle("Confirm Exit");
				  
				  Optional <ButtonType> action = alert.showAndWait();
				  if(action.get() == ButtonType.OK)
					  System.exit(0);
			});
			
		}
	
		void sleepValidationText(Text text) {
			
        Thread time = new Thread() {

			@Override
			public void run() {
				try {
					sleep(6000);
					text.setText(null);
					  
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				super.run();
			}

        };
        time.start();
        
		}
		
		

	    @FXML
	    void loginAccount(ActionEvent event) throws Exception {
	    	

	    	
	    	if(userName.getText().isEmpty() || password.getText().isEmpty()) {
	    		
	    		this.validation.setText(" Please Enter Username and Password !");
	    		
                        this.sleepValidationText(this.validation);

	          }
	    	
	    	
	    	else {
        	    	SystemAccountDAO.logInSystemAccount(event,userName.getText(), password.getText());
        	    	this.validation.setText(SystemAccountDAO.getValidationstatus());
	    	        this.setU_name(userName.getText());
	    	        this.setPass(password.getText());
	    	        this.sleepValidationText(this.validation);
   
	    	}

	    	        }
	    

	       
	    
       }

