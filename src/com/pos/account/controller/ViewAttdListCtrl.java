package com.pos.account.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

import com.pos.account.model.Attendant;
import com.pos.account.model.AttendantDAO;
import com.pos.database.Database;
import com.pos.org.model.Organization;
import com.pos.org.model.OrganizationDAO;
import com.pos.org.model.Shop;
import com.pos.org.model.ShopDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ViewAttdListCtrl implements Initializable{


	 
    @FXML
    private AnchorPane index;

    @FXML
    private HBox header;

    @FXML
    private Pane head;

    @FXML
    private ImageView profile_image;

    @FXML
    private ImageView company_logo;

    @FXML
    private Pane actProfileCntHolder;
    

    @FXML
    private Button save_change_btn;

    @FXML
    private Pane body_items;

    @FXML
    private Label acct_info;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField mname;

    @FXML
    private TextField addr;

    @FXML
    private TextField gender;

    @FXML
    private TextField dob;

    @FXML
    private TextField mobile;

    @FXML
    private TextField doe;

    @FXML
    private TextField email;

    @FXML
    private TextField position;

    @FXML
    private TableView<Attendant> attendant_table;

    @FXML
    private TableColumn<Attendant, String> column_fName;

    @FXML
    private TableColumn<Attendant, String> column_lName;
    
    @FXML
    private TableColumn<Attendant, String> column_mName;

    @FXML
    private TableColumn<Attendant, String> column_address;

    @FXML
    private TableColumn<Attendant, String> column_mobile;

    @FXML
    private TableColumn<Attendant, String> column_email;

    @FXML
    private TableColumn<Attendant, String> column_position;

    @FXML
    private TableColumn<Attendant, String> column_dob;

    @FXML
    private TableColumn<Attendant, String> column_doe;

    @FXML
    private TableColumn<Attendant, String> column_gender;

   
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

    	ObservableList<Attendant> staffs = FXCollections.observableArrayList(AttendantDAO.getAllAttendants());
    	this.attendant_table.setItems(staffs);
    	
    	this.attendant_table.getColumns().clear();
    	this.column_fName.setCellValueFactory(new PropertyValueFactory<Attendant, String>("fName"));
    	this.column_lName.setCellValueFactory(new PropertyValueFactory<Attendant, String>("surname"));
    	this.column_mName.setCellValueFactory(new PropertyValueFactory<Attendant, String>("mName"));
    	this.column_address.setCellValueFactory(new PropertyValueFactory<Attendant, String>("address"));
    	this.column_email.setCellValueFactory(new PropertyValueFactory<Attendant, String>("email"));
    	this.column_gender.setCellValueFactory(new PropertyValueFactory<Attendant, String>("gender"));
    	this.column_mobile.setCellValueFactory(new PropertyValueFactory<Attendant, String>("phoneNo"));
    	this.column_position.setCellValueFactory(new PropertyValueFactory<Attendant, String>("position"));
    	this.column_dob.setCellValueFactory(new PropertyValueFactory<Attendant, String>("dob"));
    	this.column_doe.setCellValueFactory(new PropertyValueFactory<Attendant, String>("doe"));
    	this.attendant_table.getColumns().addAll(this.column_fName,this.column_lName,this.column_mName,
    			this.column_address,this.column_email,this.column_gender,this.column_mobile,this.column_dob,
    			this.column_doe,this.column_position);
		
	}

    
    
}
