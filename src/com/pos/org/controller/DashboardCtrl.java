package com.pos.org.controller;

import java.io.IOException;

import com.pos.account.controller.CreateActProfileCtrl;

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
    void createNewAttendant(ActionEvent event)throws Exception {

    	AnchorPane attendantList = FXMLLoader.load(getClass().getResource("../../account/view/createAttendant.fxml"));
   	    this.mainPane.getChildren().setAll(attendantList);
    }

    @FXML
    void editAttendant(ActionEvent event) throws Exception{
          
    
   	   
    }

    @FXML
    void editOrganizationDtls(ActionEvent event) {

    }

    @FXML
    void viewAttendant(ActionEvent event) throws Exception{
       
    	AnchorPane attendantList = FXMLLoader.load(getClass().getResource("../../account/view/ViewAttdList.fxml"));
    	 this.mainPane.getChildren().setAll(attendantList);
    	   
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
    
    @FXML
    void updateInventoryDtls(ActionEvent event) {
    	this.mainPane.getChildren().setAll(getPane("../../inventory/view/updateInventoryView.fxml"));
    }
    
    @FXML
    void viewInventory(ActionEvent event) {
    	this.mainPane.getChildren().setAll(getPane("../../inventory/view/inventoryView.fxml"));
    }

    
    
   
      
}