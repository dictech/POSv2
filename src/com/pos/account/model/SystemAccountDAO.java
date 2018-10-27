package com.pos.account.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.sql.Date;

import com.pos.account.controller.LoginController;
import com.pos.database.Database;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SystemAccountDAO {

	   private static  Connection myConnect;
	   private static ResultSet result;
	   private static PreparedStatement prepareStatement;
	   
	   
	   
	   public static void createSystemAccount(SystemAccount act) {
		        
		      try {
		    	  String sql = "INSERT INTO account ("
		    			+ "      acct_id,"
		    	  		+ "      acct_attdt_id,"
		    	  		+ "      acct_username,"
		    	  		+ "      acct_password       )"
		    	  		+ " VALUES   (?,?,?,?)";
		    	  
		    	 myConnect = Database.getDatabaseConnection();
		    	  prepareStatement = myConnect.prepareStatement(sql);
		    	  prepareStatement.setBigDecimal(1, act.getAccountID());
		    	   prepareStatement.setBigDecimal(2, act.getActAttendantID());
		    	    prepareStatement.setString(3, act.getUserName());
		    	     prepareStatement.setString(4, act.getPassword());
                       prepareStatement.execute();
                        myConnect.close();
		    	      
		    	  
		        }
		   catch(Exception e) {
			   
			   e.printStackTrace();
		   }
		   
	   }
	   
	   public static void newAccount( String firstName, String middleName,  String surName,
			    String address,  String gender,  String dateOfBirth,  String email, String phoneNumber,
			    String workPosition, String userName, String password) {
		   
//	     DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//		      Date date =  Date.valueOf( LocalDate.parse(dateOfBirth, format));           
		   
		      final  Random generator = new Random();
		      final int gen = 30000 + generator.nextInt(30000);
		      final BigDecimal id = new BigDecimal(gen);


        Attendant attd = new Attendant();
        attd.setId(id);
        attd.setfName(firstName);
        attd.setmName(middleName);
        attd.setSurname(surName);
        attd.setAddress(address);
        attd.setGender(gender);
        attd.setDob(Date.valueOf(dateOfBirth));
        attd.setDoe(Date.valueOf(LocalDate.now()));
        attd.setEmail(email);
        attd.setPhoneNo(phoneNumber);
        attd.setPosition(workPosition);
	    AttendantDAO.createNewAttendant(attd);
        
	       try {
	       String sql = "SELECT * FROM attendant"
	       		      + " WHERE       attdt_id=?";
	       
	       myConnect = Database.getDatabaseConnection();
	       prepareStatement = myConnect.prepareStatement(sql);
	       prepareStatement.setBigDecimal(1, attd.getId());
	       result = prepareStatement.executeQuery();
	       while(result.next()) {

	         SystemAccount act = new SystemAccount();
	         act.setActAttendantID(result.getBigDecimal(1));
	         act.setUserName(userName);
	         act.setPassword(password);
	         SystemAccountDAO.createSystemAccount(act);
	         System.out.println("Your Account has been Created Sucessfully! \n please login to continue");
	       }
	           
	       }catch(Exception e) {
	    	   
	    	   e.printStackTrace();
	       }
	           
	           
	      
	          
	   }
	   
	   public static void logInSystemAccount(ActionEvent event, String userName,  String password) {
		               
		   
		         
		             
		      try {
		
		    	 String sql = "SELECT acct_attdt_id FROM account "
		    	 		    + "WHERE  acct_username = ? "
		    	 		    + "AND    acct_password = ?";
		    	 
		    	 
		    	   myConnect = Database.getDatabaseConnection();
		    	   prepareStatement = myConnect.prepareStatement(sql);
		    	   prepareStatement.setString(1, userName);
		    	   prepareStatement.setString(2, password);
		    	   result = prepareStatement.executeQuery();
		    	     
		    	      if(result.next()) {

		    	    	    Attendant attendant = AttendantDAO.getAttendant(result.getBigDecimal(1)); 
		    	    	    System.out.println("Welcome " + attendant.getfName() + " " + attendant.getSurname());
		    	    	    
		    	    	     LoginController home = new LoginController();
		    	    	     home.homePage(event);
		    	    	     AttendanceDAO.checkAttendance(attendant.getId(),
		    	    		 Date.valueOf(LocalDate.now()), attendant.getfName(), attendant.getSurname());
    	                        }
		    	      else {
		    	    	  
		    	    	      Alert alert = new Alert(AlertType.ERROR);
		    	    	      alert.setContentText("Invalid user name or password !");
		    	    	      alert.showAndWait();
		    	      }
		    	      
		    	      prepareStatement.close();
	    	          myConnect.close();
		    	  
		      }
		      catch(Exception e) {
		    	  
		    	  e.printStackTrace();
		      }
		   
		             }
	   
	   
		 

	   
	   public static  void logOutSystemAccount(Attendance attendance) {
		   
		   try {
			   String query = "UPDATE attendance"
			   		+ " SET         attdnc_id=?"   
			   		+ "             attdnc_attdnt_id=?"
			   		+ "             attdnc_first_name=?"
			   		+ "             attdnc_last_name=?"
			   		+ "             attdnc_signin_time=?"
			   		+ "             attdnc_signout_time=?"
			   		+ "             attdnc_date=?"
			   		+ "WHERE        attdnc_attdnt_id=?";
			   
			   myConnect = Database.getDatabaseConnection();
			    prepareStatement = myConnect.prepareStatement(query);
			    prepareStatement.setBigDecimal(1, attendance.getAttenc_ID());
			     prepareStatement.setBigDecimal(2, attendance.getAttenc_attendt_Id());
			      prepareStatement.setString(3, attendance.getAttendcFirstName());
			       prepareStatement.setString(4, attendance.getAttendcFirstName());
			        prepareStatement.setObject(5,  attendance.getSignInTime());
			         prepareStatement.setObject(6, attendance.getSignOutTime());
			       prepareStatement.setObject(7,  attendance.getDate());
			      
			       prepareStatement.executeUpdate();
			      prepareStatement.close();
			     myConnect.close();
			
		   }
		   catch(Exception E) {
			   
			     E.printStackTrace();
		   }
	   }
	   
	   
	
	   

	   
	   
	   
}
