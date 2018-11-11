package com.pos.org.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.pos.org.model.Organization;
import com.pos.org.model.OrganizationDAO;
import com.pos.org.model.Shop;
import com.pos.org.model.ShopDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
	
	@FXML
    private TableView<Shop> shopTable;
	
    @FXML
    private TableColumn<Shop, String> shopNameCol;
    
    @FXML
    private TableColumn<Shop, String> shopLocationCol;

    @FXML
    private TableColumn<Shop, String> shopBranchCol;

    @FXML
    private TableColumn<Shop, String> shopManagerCol;

    @FXML
    private TableColumn<Shop, String> shopRCNoCol;
    
    
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
    	
    	ObservableList<Shop> listOfShops = FXCollections.observableArrayList(ShopDAO.getAllShops());
    	this.shopTable.setItems(listOfShops);
    	
    	
    	this.shopTable.getColumns().clear();
    	this.shopNameCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopName"));
    	this.shopLocationCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopLocation"));
    	this.shopBranchCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopBranch"));
    	this.shopManagerCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopMgr"));
    	this.shopRCNoCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopRCNo"));
    	this.shopTable.getColumns().addAll(this.shopNameCol,this.shopLocationCol,this.shopBranchCol,this.shopManagerCol,this.shopRCNoCol);
    	
    	
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
    	Shop shop = new Shop();
    	shop.setShopName(orgShopName.getText());
    	shop.setShopLocation(orgShopLocation.getText());
    	shop.setShopBranch(orgShopBranch.getText());
    	shop.setShopRCNo(orgShopRCNo.getText());
    	//shop.setShopAttendant(orgShopAttendant.get);
    	ShopDAO.createShop(shop);
    	this.shopTable.refresh();;
    }

    @FXML
    void deleteOrganization(ActionEvent event) {
    	Organization org = OrganizationDAO.getOrg().get(0);
    	OrganizationDAO.deleteOrg(org);
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

