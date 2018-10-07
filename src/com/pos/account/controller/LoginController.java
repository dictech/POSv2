package com.pos.account.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pos.account.model.SystemAccountDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	
	   @FXML
	    private TextField userName;

	    @FXML
	    private TextField password;

	    @FXML
	    private Button btnLogIn;

	    @FXML
	    private Button btnSignUp;

	    @FXML
	    void createAccount(ActionEvent event) {

	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Connect to Database
		
	}
}

