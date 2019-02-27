package com.pos.account.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pos.account.model.Attendant;
import com.pos.account.model.AttendantDAO;
import com.pos.account.model.SystemAccount;
import com.pos.account.model.SystemAccountDAO;
import com.pos.database.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AttendantCtrl implements Initializable{

    @FXML
    private TextField fname;

	@FXML
    private TextField sname;

    @FXML
    private TextField mname;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private DatePicker dob;

    @FXML
    private TextArea addr;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> pos;

    @FXML
    private DatePicker doe;

    @FXML
    private Button registerBtn;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private Button btn_upload;

    @FXML
    private TextField uName;

    @FXML
    private TextField password;

    @FXML
    private TextField Repeat_password;

    @FXML
    private ImageView attendant_image;

    @FXML
    private TextField image_uri;
    
    @FXML
    private Text photoValidation;

    @FXML
    private Pane confirmation;
    
    @FXML
    private TabPane attendant_regHolder;
    
    @FXML
    private Pane attd_form_holder;

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
    
    private File selectedFile;
    
    @FXML
    private Label clt;
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    ObservableList<String> gender = FXCollections.observableArrayList("male","female");
	    ObservableList<String> position = FXCollections.observableArrayList("Attendant","Cleaner","Manager");
	     this.gender.setItems(gender);
	     this.pos.setItems(position);
	     this.doe.setValue(LocalDate.now());
	    	this.clt.setVisible(false);
	    	
	    	this.attendant_image.setImage(null);	     
	     
			
	     // initialize editAttendant section
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

			
			
	    
	}
	
	

	
    @FXML
        void insertImage (ActionEvent event) throws Exception{
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("image file","*.png","*.jpg","*.gif"));
    	 selectedFile = fc.showOpenDialog(null);
        
    	
    	if(selectedFile !=null) {
    		Image img = new Image(new FileInputStream(selectedFile.getPath()));	
    		this.attendant_image.setImage(img);
    		this.image_uri.setText(selectedFile.getPath());
    	
    	}
    	
    }

	
    @FXML
    void cancelRegistration(ActionEvent event) {
    	 Alert alert = new Alert(AlertType.CONFIRMATION);
    	 alert.setTitle("confirmation Dialog");
    	 alert.setHeaderText(null);
    	 alert.setContentText("Are you sure you want to cancel the registration ?");
    	 Optional <ButtonType> action = alert.showAndWait();
    	  if(action.get() == ButtonType.OK) {
    		  System.out.println("reg canceled");
    	  }
          
    }
 
    void validationTimer(Text text){
      Thread th = new Thread() {

		@Override
		public void run() {
			try {
			sleep(700);
			text.setText(null);
			super.run();
			}catch(InterruptedException e) {
            	e.getMessage();
			}
		}
    	  
    	          
      };
      th.start();
  
    }
    
    @FXML
     void registerAttendant(ActionEvent event) throws Exception {
    	       
    	  if(this.attendant_image.getImage() == null) {
              this.photoValidation.setText("Please provide a passport photo of you ! ");
              validationTimer(photoValidation);
              LoginCtrl lc= new LoginCtrl();
              lc.sleepValidationText(this.photoValidation);
	       }
       
    	  else if(fname.getText().isEmpty() || sname.getText().isEmpty()
    	    	|| mname.getText().isEmpty() || phoneNo.getText().isEmpty()
    	    	|| addr.getText().isEmpty() || email.getText().isEmpty()
                || gender.getValue() == null || dob.getValue() == null ||
                pos.getValue() == null || doe.getValue() == null || uName.getText().isEmpty()
                || password.getText().isEmpty() || Repeat_password.getText().isEmpty()) {
    	    	
    	    	
    	       Alert alert = new Alert(AlertType.WARNING);
    	       alert.setHeaderText(" Error");
    	       alert.setContentText("form validation");
    	       alert.setContentText("Please fill in the empty field !");
    	       alert.showAndWait();
	
    	       }
    	      
    	        else if(password.getText().equals(Repeat_password.getText())) {
    	    	       
    	    	     Attendant attendant = new Attendant();
    	    	      attendant.setId(attendant.getId());
    	    	      attendant.setfName(this.fname.getText());
    	    	      attendant.setmName(this.mname.getText());
    	    	      attendant.setSurname(this.sname.getText());
    	    	      attendant.setDob(Date.valueOf(this.dob.getValue()));
    	    	      attendant.setDoe(Date.valueOf(this.doe.getValue()));
    	    	      attendant.setAddress(this.addr.getText());
    	    	      attendant.setEmail(this.email.getText());
    	    	      attendant.setGender(this.gender.getValue());
    	    	      attendant.setPhoneNo(this.phoneNo.getText());
    	    	      attendant.setPosition(this.pos.getValue());
    	    	      attendant.setImage(this.attendant_image.getImage()); 
    	    	      AttendantDAO.setAttendantmageUri(this.image_uri.getText());
    	    	      AttendantDAO.createNewAttendant(attendant);
    	    	       
    	    	      
    	    	       
    	    	    	   SystemAccount act = new SystemAccount();
    	    	    	   act.setActAttendantID(attendant.getId());
    	    	    	   act.setUserName(this.uName.getText());
    	    	    	   act.setPassword(this.password.getText());
    	    	    	   SystemAccountDAO.createSystemAccount(act);
    	    	    	   System.out.println("Account created");
    	    	    	   this. attd_form_holder.setOpacity(0);
    	    	    	   this.clt.setVisible(true);
    	    	    	   
    	    	    	   
    	    	    
                 
    	            }
    	        
    	         else {
    	    	   
    	        	 Alert alert = new Alert(AlertType.WARNING);
          	         alert.setHeaderText(" password mismatch");
          	         alert.setContentText("form validation");
          	         alert.setContentText("Password does not match !");
          	         alert.showAndWait();  
    	    	           
    	          }
    
    }
      void relogin() throws Exception{
    	  
        Stage stage = new Stage();
  		Parent root = FXMLLoader.load(getClass().getResource("../../account/view/login.fxml"));
  		Scene scene = new Scene(root,1366,730);
  		stage.setScene(scene);
  		stage.setResizable(false);
  		stage.initStyle(StageStyle.UNDECORATED);
  		stage.show();	  
  		
   	   }

    
    @FXML
    void continueLogin(MouseEvent event) throws Exception{ 
    	   Stage stage = new Stage();
    	   Parent root = FXMLLoader.load(getClass().getResource("../../org/view/dashboard.fxml"));
    	   Scene scene = new Scene(root);
    	   stage.setScene(scene);
    	   stage = (Stage)this.clt.getScene().getWindow();
    	   relogin();
    	   stage.close();
    	   
    }
    
        // edit attendant section.
    
    

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
    private TextField id;
   

    @FXML
    private TextField change_fname;

    @FXML
    private TextField change_lname;

    @FXML
    private TextField change_mname;

    @FXML
    private TextField change_addr;

    @FXML
    private TextField change_gender;

    @FXML
    private TextField change_dob;

    @FXML
    private TextField change_doe;
    
    @FXML
    private TextField change_mobile;

    @FXML
    private Button save_change_btn;

    @FXML
    private TextField change_email;

    @FXML
    private TextField change_position;
    
    @FXML
    private Button change_photo_btn;


    @FXML
    private TextField position;



	 @FXML
	    void getAttendantInfo(MouseEvent event) {
		       this.attendant_table.getSelectionModel().getSelectedItem();
		       this.id.setText(this.attendant_table.getSelectionModel().getSelectedItem().getId().toPlainString());
		       this.change_fname.setText(this.attendant_table.getSelectionModel().getSelectedItem().getfName());
		       this.change_lname.setText(this.attendant_table.getSelectionModel().getSelectedItem().getSurname());
		       this.change_mname.setText(this.attendant_table.getSelectionModel().getSelectedItem().getmName());
		       this.change_email.setText(this.attendant_table.getSelectionModel().getSelectedItem().getEmail());
		       this.change_addr.setText(this.attendant_table.getSelectionModel().getSelectedItem().getAddress());
		       this.change_dob.setText(this.attendant_table.getSelectionModel().getSelectedItem().getDob().toString());
		       this.change_doe.setText(this.attendant_table.getSelectionModel().getSelectedItem().getDoe().toString());
		       this.change_gender.setText(this.attendant_table.getSelectionModel().getSelectedItem().getGender());
		       this.change_position.setText(this.attendant_table.getSelectionModel().getSelectedItem().getPosition());
		       this.change_mobile.setText(this.attendant_table.getSelectionModel().getSelectedItem().getPhoneNo());
		       this.profile_image.setImage(this.attendant_table.getSelectionModel().getSelectedItem().getImage());
		       this.save_change_btn.setVisible(true);
		       
		
	    }
    



	    @FXML
	    void saveChanges(ActionEvent event)throws Exception {
	    	
            Attendant attd = new Attendant();
            attd.setId(new BigDecimal(this.id.getText()));
            attd.setfName(this.change_fname.getText());
	    	attd.setmName(this.change_mname.getText());
	    	attd.setSurname(this.change_lname.getText());
	    	attd.setAddress(this.change_addr.getText());
	    	attd.setDob(Date.valueOf(this.change_dob.getText()));
	    	attd.setDoe(Date.valueOf(this.change_doe.getText()));
	        attd.setEmail(this.change_email.getText());
	        attd.setGender(this.change_gender.getText());
	        attd.setPhoneNo(this.change_mobile.getText());
	        attd.setPosition(this.change_position.getText());
	        attd.setImage(this.profile_image.getImage());
	        AttendantDAO.updateAttendant(attd);
	        Database.refreshTable(attendant_table, AttendantDAO.getAllAttendants());

	    }
	    

   }

    
    
    
    
    
    
    
    
    

