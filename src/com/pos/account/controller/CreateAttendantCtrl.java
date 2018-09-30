package com.pos.account.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateAttendantCtrl {

    @FXML
    private TextField fname;

    @FXML
    private TextField sname;

    @FXML
    private TextField oname;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private DatePicker dob;

    @FXML
    private TextArea addr;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<?> pos;

    @FXML
    private DatePicker doe;

    @FXML
    private Button registerBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void cancelRegistration(ActionEvent event) {
    	
    }

    @FXML
    void registerAttendant(ActionEvent event) {

    }

}
