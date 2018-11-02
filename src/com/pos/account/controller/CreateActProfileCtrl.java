package com.pos.account.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.pos.account.model.Attendant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateActProfileCtrl  implements Initializable{

	
	    @FXML
	    private ImageView profile_image;

	    @FXML
	    private AnchorPane body_container;

	    @FXML
	    private AnchorPane body_item_container;

	    @FXML
	    private AnchorPane body_item_container1;

	    @FXML
	    private AnchorPane main_container1;

	    @FXML
	    private AnchorPane head_container1;

	    @FXML
	    private Pane image_container1;

	    @FXML
	    private ImageView profile_image1;

	    @FXML
	    private AnchorPane body_container1;

	    @FXML
	    private Label homeAddr;

	    @FXML
	    private Label mobile;

	    @FXML
	    private Label position;

	    @FXML
	    private Label gender;

	    @FXML
	    private Label email;

	    @FXML
	    private Label dob;

	    @FXML
	    private Label surN;

	    @FXML
	    private Label lastN;

	    @FXML
	    private Label firstN;

	    


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		  Attendant ctr = new Attendant();
		 this.firstN.setText(ctr.getfName());
		 this.lastN.setText(ctr.getSurname());
		 this.surN.setText(ctr.getmName());
		 this.dob.setText(Date.554              );
		 this.mobile.setText(ctr.getPhoneNo().getText());
		 this.position.setText(ctr.getPos().getValue());
		 this.email.setText(ctr.getEmail().getText());
    	 this.homeAddr.setText(ctr.getAddr().getText());
		 this.gender.setText(ctr.getGender().getValue());
		    
	}

	
	public void editProfile(ActionEvent event) throws Exception {
		

	}
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
