package com.pos.org.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import com.pos.account.controller.LoginCtrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DashboardCtrl implements Initializable {
	

    @FXML
    private Text atttd_ID;
	
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button btn_logout;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}
	
	  void gotoLogin() throws Exception{
		  
			
			Parent root = FXMLLoader.load(getClass().getResource("../../account/view/login.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root,1366,730);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
	  }
	
	   @FXML
	    void logout(ActionEvent event) throws Exception{
		   
       	    Parent root = FXMLLoader.load(getClass().getResource("../../org/view/dashboard.fxml"));
	        Stage dashboard = new Stage();
	        Scene scene = new Scene(root);
	        dashboard.setScene(scene);
            dashboard = (Stage)this.btn_logout.getScene().getWindow();
            this.gotoLogin();
            dashboard.close();
            
  
	    }
	
    
    @FXML
    void createNewAttendant(ActionEvent event)throws Exception {

    	AnchorPane attendantList = FXMLLoader.load(getClass().getResource("../../account/view/Attendant.fxml"));
   	    this.mainPane.getChildren().setAll(attendantList);
    }

    @FXML
    void editAttendant(ActionEvent event) throws Exception{
          
        AnchorPane editAttdnt = FXMLLoader.load(getClass().getResource("../../account/view/EditAttendant.fxml"));
   	    this.mainPane.getChildren().setAll(editAttdnt);
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


    @FXML
    void new_order(ActionEvent event) {
     
    	this.mainPane.getChildren().setAll(getPane("../../payment/view/PaymentView.fxml"));
    }
    
    @FXML
    void createOrderTransaction(ActionEvent event) {
     
    	this.mainPane.getChildren().setAll(getPane("../../order/view/CreateOrderView.fxml"));
    }

     
}