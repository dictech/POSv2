package com.pos.account.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class EditAttendantCtrl {

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
    private TextField email;

    @FXML
    private TextField position;

    @FXML
    private TableView<?> attendant_table;

    @FXML
    private TableColumn<?, ?> column_lName;

    @FXML
    private TableColumn<?, ?> column_fName;

    @FXML
    private TableColumn<?, ?> column_mName;

    @FXML
    private TableColumn<?, ?> column_address;

    @FXML
    private TableColumn<?, ?> column_mobile;

    @FXML
    private TableColumn<?, ?> column_email;

    @FXML
    private TableColumn<?, ?> column_position;

    @FXML
    private TableColumn<?, ?> column_dob;

    @FXML
    private TableColumn<?, ?> column_doe;

    @FXML
    private TableColumn<?, ?> column_gender;

    @FXML
    void getAttendantInfo(MouseEvent event) {

    }

    
    
}
