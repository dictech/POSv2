package com.pos.org.controller;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class OrganizationCtrl implements Initializable{

    @FXML
    private Tab orgTab;

    @FXML
    private TextField orgName;

    @FXML
    private ImageView org_logo_image;
    
    @FXML
    private Button org_logo_btn;
    
    @FXML
    private Text orglogo_text;
    
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
    
    @FXML
    private Label orgShopIdLbl;

    @FXML
    private TextField orgShopId;
    
    private Shop shop;
    
    private File file;

    
    
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
    void uploadOrgImage(ActionEvent event) throws Exception{

    	  FileChooser fc = new FileChooser();
    	  fc.getExtensionFilters().addAll(new ExtensionFilter("image file","*.png","*.jpg","*.gif"));
    	  this.file = fc.showOpenDialog(null);
    	  
    	  if(file !=null) {
    		  this.orglogo_text.setVisible(false);
        	  this.org_logo_image.setImage(new Image(new FileInputStream(file.getPath())));  
    		  
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
    	ShopDAO.deleteShop(this.shop);
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
    	
    	this.shop.setShopId(new BigDecimal(orgShopId.getText()));
    	this.shop.setShopName(orgShopName.getText());
    	this.shop.setShopLocation(orgShopLocation.getText());
        this.shop.setShopBranch(orgShopBranch.getText());
        this.shop.setShopRCNo(orgShopRCNo.getText());
        
        ShopDAO.updateShop(shop);
    }
    
    
    @FXML
    void getTableRowData(MouseEvent event) {
    	
      this.shop = this.shopTable.getSelectionModel().getSelectedItem();

      orgShopId.setText(this.shopTable.getSelectionModel().getSelectedItem().getShopId().toString());
      orgShopName.setText(this.shopTable.getSelectionModel().getSelectedItem().getShopName());
      orgShopLocation.setText(this.shopTable.getSelectionModel().getSelectedItem().getShopLocation());
      orgShopBranch.setText(this.shopTable.getSelectionModel().getSelectedItem().getShopBranch());
      orgShopRCNo.setText(this.shopTable.getSelectionModel().getSelectedItem().getShopRCNo());
      
    }

    

}

