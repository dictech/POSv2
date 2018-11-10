package com.pos.org.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardCtrl {

    @FXML
    private AnchorPane mainPane;

    @FXML
    void createNewAttendant(ActionEvent event) {

    	   
    }

    @FXML
    void editAttendant(ActionEvent event) {

    }

    @FXML
    void editOrganizationDtls(ActionEvent event) {

    }

    @FXML
    void viewAttendant(ActionEvent event) throws Exception{
       
    	   
    }
    
    public AnchorPane getPane(String pane) {
    	  
    	AnchorPane anchorPane = null;
    	
    	try{
    		anchorPane =  FXMLLoader.load(getClass().getResource(pane)); 		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
       
    	return anchorPane;
    }

    @FXML
    void viewOrganizationDtls(ActionEvent event){
    	this.mainPane.getChildren().setAll(getPane("../view/viewOrganization.fxml"));
    }
      
    @FXML
    void updateOrganizationDtls(ActionEvent event) {
    	this.mainPane.getChildren().setAll(getPane("../view/createOrganizationView.fxml"));
    }

}