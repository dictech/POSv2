package com.pos.account.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.pos.account.model.Attendant;
import com.pos.account.model.AttendantDAO;
import com.pos.database.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class CreateAttdntListCtr implements Initializable{

    @FXML
    private HBox header;

    @FXML
    private ListView<Attendant> attendantList;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		   ObservableList<Attendant>staff = FXCollections.observableArrayList();
		      Connection myConnect = null;
		      Statement statement = null;
		      ResultSet result = null;
		         String sql = "SELECT * FROM posv2.attendant";
		         try {
		        	 
		        	 myConnect = Database.getDatabaseConnection();
		        	 statement = myConnect.createStatement();
		        	 result = statement.executeQuery(sql);
		        	 while(result.next()) {
		        		 
		        	 Attendant attd = new Attendant();
		 		     attd.setfName(result.getString(2));
		 		     attd.setmName(result.getString(3));
		 		     attd.setSurname(result.getString(4));
		 		     attd.setGender(result.getString(5));
		 		     attd.setDob(result.getDate(6));
		 		     attd.setAddress(result.getString(7));
		 		     attd.setPhoneNo(result.getString(8));
		 		     attd.setEmail(result.getString(9));
		 		     attd.setDoe(result.getDate(10));
		 		     attd.setPosition(result.getString(11));
		 		     AttendantDAO.getAllAttendants();
		 		     staff.add(attd);
		 		     staff.forEach((count) -> System.out.println(attd.getSurname()));
		 		     this.attendantList.setItems(attd.getSurname());
		 		    
		        	 }
		 		    
		        	 }catch(Exception e) {
		        	 e.printStackTrace();
		         }
		   
	}
    

}
