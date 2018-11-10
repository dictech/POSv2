package com.pos.org.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import com.pos.org.model.Organization;
import com.pos.org.model.OrganizationDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OrganizationCtrl implements Initializable{

    @FXML
    private Tab orgTab;

    @FXML
    private TextField orgName;

    @FXML
    private TextArea orgLocation;

    @FXML
    private TextField orgPhoneNo;

    @FXML
    private TextField orgEmail;

    @FXML
    private Tab shopTab;

    @FXML
    private TextField orgShopName;

    @FXML
    private TextArea orgShopLocation;

    @FXML
    private TextField orgShopBranch;

    @FXML
    private TextField orgShopRCNo;

    @FXML
    private ComboBox<?> orgShopAttendant;

	private BigDecimal orgId;
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	List<Organization> listOfOrganizations = OrganizationDAO.getOrg();
    	
    	if(listOfOrganizations.size() > 0) {
	    	Organization org = listOfOrganizations.get(0);
            
	    	this.orgId = org.getOrg_id();
	    	this.orgName.setText(org.getOrg_name());
	    	this.orgLocation.setText(org.getOrg_addrs());
	    	this.orgPhoneNo.setText(org.getOrg_phone());
	    	this.orgEmail.setText(org.getOrg_email());
	    	}
    }

    @FXML
    void createOrganization(ActionEvent event) {
    	Organization org = new Organization();
    	org.setOrg_name(this.orgName.getText());
    	org.setOrg_addrs(this.orgLocation.getText());
    	org.setOrg_phone(this.orgPhoneNo.getText());
    	org.setOrg_email(this.orgEmail.getText());
    	OrganizationDAO.createOrg(org);
    }

    @FXML
    void createShop(ActionEvent event) {

    }

    @FXML
    void deleteOrganization(ActionEvent event) {
    	
    }

    @FXML
    void deleteShop(ActionEvent event) {

    }

    @FXML
    void updateOrganization(ActionEvent event) {    	
    	
    	Organization org =  new Organization();
    	org.setOrg_id(orgId);
    	org.setOrg_name(this.orgName.getText());
    	org.setOrg_addrs(this.orgLocation.getText());
    	org.setOrg_email(this.orgEmail.getText());
    	org.setOrg_phone(this.orgPhoneNo.getText());
    	OrganizationDAO.updateOrg(org);
    	
    }
    

    @FXML
    void updateShop(ActionEvent event) {

    }

}

