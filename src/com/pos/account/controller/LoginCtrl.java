package com.pos.account.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pos.account.model.SystemAccount;
import com.pos.account.model.SystemAccountDAO;
import com.pos.account.view.CreateLoginView;
import com.pos.org.view.DashboardView;

import javafx.animation.RotateTransition;
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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	    
	    @FXML
	    private HBox loadingHolder;

	    @FXML
	    private Circle c1;

	    @FXML
	    private Circle c2;

	    @FXML
	    private Circle c3;
	    

	    @FXML
	    private Text veri;
	    
	    @FXML
	    private Text please_wait;
	     
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
	
		      void callLoading() {
			  
			  loadingHolder.setOpacity(1);
			  this.please_wait.setOpacity(1);
			  LoginCtrl.rotate(c1, true, 180, 9);
			  LoginCtrl.rotate(c2, true, 150, 12);
			  LoginCtrl.rotate(c3, true, 110, 18);
			  
		  }
		  
		public void sleepValidationText(Text text) {
			
        Thread time = new Thread() {
            
			@Override
			public void run() {
				try {
					
					sleep(6000);
					validation.setText(text.getText());
					loadingHolder.setOpacity(0);
					please_wait.setOpacity(0);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				super.run();
			}

        };
        time.start();
        
		}
		
      
		void closeLogin() {
			
			Stage stage = (Stage) btnLogIn.getScene().getWindow();
		    stage.close();
		}
		
	    @FXML
	    void loginAccount(ActionEvent event) throws Exception {
	    	
	    	this.validation.setText(null);
	    	
	    	if(userName.getText().isEmpty() || password.getText().isEmpty()) {
	    		check(new Text(" Please Enter Username and Password !"));

	          }
	    	
	    	
	    	else {
	    		    
        	    	SystemAccountDAO.logInSystemAccount(event,userName.getText(), password.getText());
        	    	this.veri.setText(SystemAccountDAO.getValidationstatus());
        	    	String v = this.veri.getText();
        	    	check(new Text(SystemAccountDAO.getValidationstatus()));
	    	        this.setU_name(userName.getText());
	    	        this.setPass(password.getText());
	    	        
	    	        
	    	        
	    	          if(v.contains("Verified!")) {

	    	        	Parent root = FXMLLoader.load(getClass().getResource("../../account/view/login.fxml"));
		    	        Stage loginScreen = new Stage();
		    	        Scene scene = new Scene(root);
		    	        loginScreen.setScene(scene);
		    	        closeLogin();
	    			    
		    	        
	    	          }
                                         
	    	}

	    	        }
	    
        private static void rotate(Circle c, boolean reverse, int angle, int duration) {
        	
        	 RotateTransition rt = new RotateTransition(Duration.seconds(duration), c);
             rt.setAutoReverse(reverse);
             rt.setByAngle(angle);
             rt.setDelay(Duration.seconds(0));
             rt.setRate(3);
             rt.setCycleCount(18);
             rt.play();
        	
        } 
	            
        void check (Text text) {
        	
	         Thread th = new Thread(){

				@Override
				public void run() {
					try {
						
						sleep(700);
						callLoading();
						sleepValidationText(text);	
						super.run();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
	        	 
	        	 
	        	 
	         };
	         th.start();
        }
	    
       }

