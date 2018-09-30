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
    private TextField usernameBtn;

    @FXML
    private TextField passwordBtn;

    @FXML
    private Button signinBtn;

    @FXML
    void signIn(ActionEvent event) {
    	System.out.println("Button Clicked");
    	String username = this.usernameBtn.getText();
    	String password = this.passwordBtn.getText();
    	SystemAccountDAO.logInSystemAccount(username,password);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Connect to Database
		
	}
}

