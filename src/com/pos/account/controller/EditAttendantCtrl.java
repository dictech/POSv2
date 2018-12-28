package com.pos.account.controller;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.pos.account.model.Attendant;
import com.pos.account.model.AttendantDAO;
import com.pos.org.controller.DashboardCtrl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class EditAttendantCtrl implements Initializable{

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
    private Pane body_items;

    @FXML
    private Label acct_info;
    

    @FXML
    private TextField imageUri;

    @FXML
    private TextField id;
    
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
    private Button save_change_btn;
    
    @FXML
    private Button change_photo_btn;

    @FXML
    private TextField email;

    @FXML
    private TextField position;

        
    @FXML
    private TableView<Attendant> attendant_table;

    @FXML
    private TableColumn<Attendant, String> column_lName;

    @FXML
    private TableColumn<Attendant, String> column_fName;

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

    @FXML
    private TableColumn<Attendant, BigDecimal> column_id;

    @FXML
    private TableColumn<Attendant, Image> column_image;
    
    
    private FileChooser choseImage;
    


	public TextField getImageUri() {
		return imageUri;
	}

	public void setImageUri(TextField imageUri) {
		this.imageUri = imageUri;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<Attendant>staff = FXCollections.observableArrayList(AttendantDAO.getAllAttendants());
		this.attendant_table.setItems(staff);
		this.attendant_table.getColumns().clear();
		this.column_id.setCellValueFactory(new PropertyValueFactory<Attendant,BigDecimal>("id"));
		this.column_lName.setCellValueFactory(new PropertyValueFactory<Attendant,String>("surname"));
		this.column_fName.setCellValueFactory(new PropertyValueFactory<Attendant,String>("fName"));
		this.column_mName.setCellValueFactory(new PropertyValueFactory<Attendant,String>("mName"));
		this.column_address.setCellValueFactory(new PropertyValueFactory<Attendant,String>("address"));
		this.column_email.setCellValueFactory(new PropertyValueFactory<Attendant,String>("email"));
		this.column_mobile.setCellValueFactory(new PropertyValueFactory<Attendant,String>("phoneNo"));
		this.column_dob.setCellValueFactory(new PropertyValueFactory<Attendant,String>("dob"));
		this.column_doe.setCellValueFactory(new PropertyValueFactory<Attendant,String>("doe"));
		this.column_position.setCellValueFactory(new PropertyValueFactory<Attendant,String>("position"));
		this.column_gender.setCellValueFactory(new PropertyValueFactory<Attendant,String>("gender"));
		this.column_image.setCellValueFactory(new PropertyValueFactory<Attendant,Image>("image"));
		this.attendant_table.getColumns().addAll(this.column_fName,this.column_lName,this.column_mName,
				this.column_address,this.column_dob,this.column_doe,this.column_email,this.column_gender,
				this.column_mobile,this.column_position,this.column_id,this.column_image);
		
		this.column_id.setVisible(false);
		this.id.setOpacity(0);
		this.change_photo_btn.setDisable(true);
		
		
	}

	 @FXML
	    void getAttendantInfo(MouseEvent event) {
		       this.attendant_table.getSelectionModel().getSelectedItem();
		       this.id.setText(this.attendant_table.getSelectionModel().getSelectedItem().getId().toPlainString());
		       this.fname.setText(this.attendant_table.getSelectionModel().getSelectedItem().getfName());
		       this.lname.setText(this.attendant_table.getSelectionModel().getSelectedItem().getSurname());
		       this.mname.setText(this.attendant_table.getSelectionModel().getSelectedItem().getmName());
		       this.email.setText(this.attendant_table.getSelectionModel().getSelectedItem().getEmail());
		       this.addr.setText(this.attendant_table.getSelectionModel().getSelectedItem().getAddress());
		       this.dob.setText(this.attendant_table.getSelectionModel().getSelectedItem().getDob().toString());
		       this.doe.setText(this.attendant_table.getSelectionModel().getSelectedItem().getDoe().toString());
		       this.gender.setText(this.attendant_table.getSelectionModel().getSelectedItem().getGender());
		       this.position.setText(this.attendant_table.getSelectionModel().getSelectedItem().getPosition());
		       this.mobile.setText(this.attendant_table.getSelectionModel().getSelectedItem().getPhoneNo());
		       this.profile_image.setImage(this.attendant_table.getSelectionModel().getSelectedItem().getImage());
		       this.save_change_btn.setVisible(true);
		       this.change_photo_btn.setDisable(true);
		      

	    }
    
	    
	    @FXML
	    void change_photo(ActionEvent event) throws Exception{
//              
//	    	   FileChooser fc = new FileChooser();  
//	    	   fc.getExtensionFilters().addAll(new ExtensionFilter("Image","*.png","*.jpg","*.gif"));
//	    	   File file = fc.showOpenDialog(null);
//	    	  
//	    	   if(file != null) {
//	    		   Image img = new Image(new FileInputStream(file.getAbsolutePath()));   
//	    		   this.profile_image.setImage(img);
//	    		   this.imageUri.setText(file.getPath());
//	    		   this.imageUri.getText();
//	    		   
//	    	   }
	    }


	    @FXML
	    void saveChanges(ActionEvent event)throws Exception {
            Attendant attd = new Attendant();
            attd.setId(new BigDecimal(this.id.getText()));
            attd.setfName(this.fname.getText());
	    	attd.setmName(this.mname.getText());
	    	attd.setSurname(this.lname.getText());
	    	attd.setAddress(this.addr.getText());
	    	attd.setDob(Date.valueOf(this.dob.getText()));
	    	attd.setDoe(Date.valueOf(this.doe.getText()));
	        attd.setEmail(this.email.getText());
	        attd.setGender(this.gender.getText());
	        attd.setPhoneNo(this.mobile.getText());
	        attd.setPosition(this.position.getText());
	        attd.setImage(this.profile_image.getImage());
	        AttendantDAO.updateAttendant(attd);
	    

	    }
	    

	    
}
