package com.pos.account.controller;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
		
		CreateAttendantCtrl ctr = new CreateAttendantCtrl();
		 this.firstN.setText(ctr.getFname().getText());
		 this.lastN.setText(ctr.getSname().getText());
		 this.surN.setText(ctr.getMname().getText());
		 this.dob.setText(ctr.getDob().getValue().toString());
		    
	}

	
	
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
