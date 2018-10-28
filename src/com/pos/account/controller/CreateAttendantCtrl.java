package com.pos.account.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pos.account.model.Attendant;
import com.pos.account.model.AttendantDAO;
import com.pos.account.model.SystemAccount;
import com.pos.account.model.SystemAccountDAO;
import com.pos.database.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateAttendantCtrl implements Initializable{

    @FXML
    private TextField fname;

    @FXML
    private TextField sname;

    @FXML
    private TextField mname;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private DatePicker dob;

    @FXML
    private TextArea addr;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> pos;

    @FXML
    private DatePicker doe;

    @FXML
    private Button registerBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField uName;

    @FXML
    private TextField password;

    @FXML
    private TextField Repeat_password;

    



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    ObservableList<String> gender = FXCollections.observableArrayList("male","female");
	    ObservableList<String> position = FXCollections.observableArrayList("Attendant","Cleaner","Manager");
	     this.gender.setItems(gender);
	     this.pos.setItems(position);
		
	}
	
	
    @FXML
    void cancelRegistration(ActionEvent event) {
    	 Alert alert = new Alert(AlertType.CONFIRMATION);
    	 alert.setTitle("confirmation Dialog");
    	 alert.setHeaderText(null);
    	 alert.setContentText("Are you sure you want to cancel the registration ?");
    	 Optional <ButtonType> action = alert.showAndWait();
    	  if(action.get() == ButtonType.OK) {
    		  System.exit(0);
    	  }
          
    }

    
    @FXML
     void registerAttendant(ActionEvent event) throws Exception {
       
    	    if(fname.getText().isEmpty() || sname.getText().isEmpty()
    	    	|| mname.getText().isEmpty() || phoneNo.getText().isEmpty()
    	    	|| addr.getText().isEmpty() || email.getText().isEmpty()
                || gender.getValue() == null || dob.getValue() == null ||
                pos.getValue() == null || doe.getValue() == null || uName.getText().isEmpty()
                || password.getText().isEmpty() || Repeat_password.getText().isEmpty()) {
    	    	
    	    	
    	       Alert alert = new Alert(AlertType.WARNING);
    	       alert.setHeaderText(" Error");
    	       alert.setContentText("form validation");
    	       alert.setContentText("Please fill in the empty field !");
    	       alert.showAndWait();
	
    	       }
    	     else if(password.getText().equals(Repeat_password.getText())) {
    	    	       
    	    	     Attendant attendant = new Attendant();
    	    	      attendant.setId(attendant.getId());
    	    	      attendant.setfName(this.fname.getText());
    	    	      attendant.setmName(this.mname.getText());
    	    	      attendant.setSurname(this.sname.getText());
    	    	      attendant.setDob(Date.valueOf(this.dob.getValue()));
    	    	      attendant.setDoe(Date.valueOf(this.doe.getValue()));
    	    	      attendant.setAddress(this.addr.getText());
    	    	      attendant.setEmail(this.email.getText());
    	    	      attendant.setGender(this.gender.getValue());
    	    	      attendant.setPhoneNo(this.phoneNo.getText());
    	    	      attendant.setPosition(this.pos.getValue());
    	    	      AttendantDAO.createNewAttendant(attendant);
    	    	       
    	    	      
    	    	       
    	    	    	   SystemAccount act = new SystemAccount();
    	    	    	   act.setActAttendantID(attendant.getId());
    	    	    	   act.setUserName(this.uName.getText());
    	    	    	   act.setPassword(this.password.getText());
    	    	    	   SystemAccountDAO.createSystemAccount(act);
    	    	    	   System.out.println("Account created");
    	    	    	   confirmScreen(event); 
    	    	       
    	    	       
                 
    	            }
    	        
    	         else {
    	    	   
    	        	 Alert alert = new Alert(AlertType.WARNING);
          	         alert.setHeaderText(" password mismatch");
          	         alert.setContentText("form validation");
          	         alert.setContentText("Password does not match !");
          	         alert.showAndWait();  
    	    	           
    	          }
    
    }


                  public void confirmScreen(ActionEvent event) throws Exception {
   	 
    	          Parent root = FXMLLoader.load(getClass().getResource("../view/Confirmation.fxml"));
	              Scene scene =  new Scene(root,500,520);
	    	      Stage stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
	    	      stage.setScene(scene);
	    	      stage.show();}

        
                
        
   }

    
    
    
    
    
    
    
    
    

