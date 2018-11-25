package com.pos.account.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.pos.account.model.Attendant;
import com.pos.account.model.AttendantDAO;
import com.pos.database.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CreateActProfileCtrl implements Initializable{

	private BigDecimal id;
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
    private Pane body;

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
    private TableView<Attendant> tableView;

    @FXML
    private TableColumn<Attendant, BigDecimal> columnID;

    @FXML
    private TableColumn<Attendant, String> columnFname;

    @FXML
    private TableColumn<Attendant, String> columnMname;

    @FXML
    private TableColumn<Attendant, String> columnLname;

    @FXML
    private TableColumn<Attendant, String> columnGender;

    @FXML
    private TableColumn<Attendant, String> columnMobile;

    @FXML
    private TableColumn<Attendant, String> columnEmail;

    @FXML
    private TableColumn<Attendant, String> columnDob;

    @FXML
    private TableColumn<Attendant, String> columnDoe;

    @FXML
    private TableColumn<Attendant, String> columnPos;

    @FXML
    private TableColumn<Attendant, String> columnAddr;

    
    private Connection myConnect = null;
    private PreparedStatement prepareStatement = null;
    private ResultSet result = null;
	
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	    String sql = " SELECT * FROM posv2.attendant;";
        try {
             this.myConnect = Database.getDatabaseConnection();
             this.result = myConnect.createStatement().executeQuery(sql);
             
             while(result.next()) {

        ObservableList<Attendant> staffs = FXCollections.observableArrayList();
        
        Attendant attendant = new Attendant();
        attendant.setId(result.getBigDecimal(1));
        attendant.setfName(result.getString(2));
        attendant.setmName(result.getString(3));
        attendant.setSurname(result.getString(4));
        attendant.setGender(result.getString(5));
        attendant.setDob(result.getDate(6));
        attendant.setAddress(result.getString(7));
        attendant.setPhoneNo(result.getString(8));
        attendant.setEmail(result.getString(9));
        attendant.setDoe(result.getDate(10));
        attendant.setPosition(result.getString(11));
     
        
        PropertyValueFactory<Attendant, String> test = new PropertyValueFactory<Attendant, String>("fName");
        columnID.setCellValueFactory(new PropertyValueFactory<Attendant, BigDecimal>("id"));
        columnFname.setCellValueFactory(test);
        columnMname.setCellValueFactory(new PropertyValueFactory<Attendant, String>("mName"));
        columnLname.setCellValueFactory(new PropertyValueFactory<Attendant, String>("surname"));
        columnGender.setCellValueFactory(new PropertyValueFactory<Attendant, String>("gender"));
        columnAddr.setCellValueFactory(new PropertyValueFactory<Attendant, String>("address"));
        columnDob.setCellValueFactory(new PropertyValueFactory<Attendant, String>("dob"));
        columnPos.setCellValueFactory(new PropertyValueFactory<Attendant, String>("position"));
        columnDoe.setCellValueFactory(new PropertyValueFactory<Attendant, String>("doe"));
        columnMobile.setCellValueFactory(new PropertyValueFactory<Attendant, String>("phoneNo"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Attendant, String>("email"));
        
         staffs.addAll(AttendantDAO.getAllAttendants());
         tableView.setItems(staffs);
         tableView.getColumns().clear();

         
         tableView.getColumns().addAll(columnFname,columnMname,columnLname);

         
         Attendant attd = tableView.getSelectionModel().getSelectedItem();
         
         
         
         this.id = result.getBigDecimal(1);
           this.result.close();
           this.myConnect.close();
             }
             
             
        }catch(Exception error) {
     	   
     	   error.getMessage();
        }
	}

	
	
    
    
    
    
    
}
