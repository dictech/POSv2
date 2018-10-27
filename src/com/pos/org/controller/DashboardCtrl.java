package com.pos.org.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class DashboardCtrl {

    @FXML
    private AnchorPane mainPane;

    @FXML
    void createNewAttendant(ActionEvent event) {

    }

    @FXML
    void editAttendant(ActionEvent event) {

    }

    @FXML
    void editOrganizationDtls(ActionEvent event) {

    }

    @FXML
    void viewAttendant(ActionEvent event) {

    }

    @FXML
    void viewOrganizationDtls(ActionEvent event) throws IOException {
    	AnchorPane orgDtlsPane = FXMLLoader.load(getClass().getResource("../view/viewOrganization.fxml"));
    	this.mainPane.getChildren().setAll(orgDtlsPane);
    }

}