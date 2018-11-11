package com.pos.org.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.pos.org.model.Organization;
import com.pos.org.model.OrganizationDAO;
import com.pos.org.model.Shop;
import com.pos.org.model.ShopDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class viewOrganizationCtrl implements Initializable {
	
	

    @FXML
    private AnchorPane orgDtlsPane;

    @FXML
    private ImageView orgLogo;

    @FXML
    private Label orgName;

    @FXML
    private Label orgAddr;

    @FXML
    private Label orgPhone;

    @FXML
    private Label orgEmail;
    
    @FXML
    private TableView<Shop> shopsTable;
    
    @FXML
    private TableColumn<Shop, String> shopName;

    @FXML
    private TableColumn<Shop, String> shopBranch;

    @FXML
    private TableColumn<Shop, String> shopLocation;

    @FXML
    private TableColumn<Shop, String> shopManager;

    @FXML
    private TableColumn<Shop, String> shopRCNo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Organization  organization= OrganizationDAO.getOrg().get(0);
		orgName.setText(organization.getOrg_name());
		orgAddr.setText(organization.getOrg_addrs());
		orgPhone.setText(organization.getOrg_phone());
		orgEmail.setText(organization.getOrg_email());
		
		//Convert ArrayList to ObservableArrayList
		ObservableList<Shop> listOfShops = FXCollections.observableArrayList(ShopDAO.getAllShops());
		
		shopsTable.setItems(listOfShops);
		shopsTable.getColumns().clear();
		
		//Map front-end table column to Pojo properties
		shopName.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopName"));
		shopBranch.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopBranch"));
		shopLocation.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopLocation"));
		shopManager.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopMgr"));
		shopRCNo.setCellValueFactory(new PropertyValueFactory<Shop, String>("shopRCNo"));
		
		shopsTable.getColumns().addAll(shopName,shopBranch,shopLocation,shopManager,shopRCNo);
	}
    
    
}


