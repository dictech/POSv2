package com.pos.account.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.sql.Date;

import com.pos.account.controller.LoginCtrl;
import com.pos.database.AttendantCache;
import com.pos.database.Database;
import com.pos.org.view.DashboardView;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SystemAccountDAO {

	   private static  Connection myConnect;
	   private static ResultSet result;
	   private static PreparedStatement prepareStatement;
	   
	   





	public static void createSystemAccount(SystemAccount act)throws SQLException {
		        
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
	   
	   
	   
	   public static SystemAccount readSystemAccount(BigDecimal id) throws SQLException{
		      SystemAccount act = new SystemAccount();
		      myConnect = null;
		      prepareStatement = null;
		      result = null;
		      
		      String sql = " SELECT * FROM posv2.account "
		      		     + " WHERE acct_id=?";
		      myConnect = Database.getDatabaseConnection();
		      prepareStatement = myConnect.prepareStatement(sql);
		      prepareStatement.setBigDecimal(1, id);
		      result = prepareStatement.executeQuery();
		      
		      if(result.next()) {
		    	  
		    	  act.setAccountID(result.getBigDecimal(1));
		    	  act.setActAttendantID(result.getBigDecimal(2));
		    	  act.setUserName(result.getString(3));
		    	  act.setPassword(result.getString(4));
		    	  prepareStatement.close();
		    	  myConnect.close();
		      }
		      return act;
	   }
	   
	   public static void updateSystemAccount(BigDecimal id) throws SQLException{
		   SystemAccount act = new SystemAccount();
		   myConnect = null;
		   prepareStatement = null;
		   result = null;
		   
		   String sql = "UPDATE posv2.account "
		   		+ " SET       acct_username=? "
		   		+ "            acct_password=? "
		   		+ " WHERE            acct_id=? ";
		   myConnect = Database.getDatabaseConnection();
		   prepareStatement = myConnect.prepareStatement(sql);
		   prepareStatement.setString(1, act.getUserName());
		   prepareStatement.setString(2, act.getPassword());
		   prepareStatement.setBigDecimal(3, id);
		   prepareStatement.executeUpdate();
		   prepareStatement.close();
		   myConnect.close();
		   
	   }
		
	   public static void deleteSystemAccount(BigDecimal id) throws SQLException{
		   myConnect = null;
		   prepareStatement = null;
		    
		     String sql = " DELETE * FROM posv2.account "
		     		    + " WHERE acct_id=? ";
		              myConnect = Database.getDatabaseConnection();
		              prepareStatement = myConnect.prepareStatement(sql);
		              prepareStatement.setBigDecimal(1, id);
		              prepareStatement.execute();
		              prepareStatement.close();
		              myConnect.close();
		              System.out.println(" your account has been deleted !");
		              
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
		    	    	    
		    	    	   
		    	    	     AttendanceDAO.checkAttendance(attendant.getId(),
		    	    		 Date.valueOf(LocalDate.now()), attendant.getfName(), attendant.getSurname());
		    	    	     
		    	    	     AttendantCache.setAttendant(attendant);
		    	    	     AttendantCache.getCache().get(attendant.getCacheId());
		    	    	     		    	    	   	 
		    	    	   	DashboardView dv =  new DashboardView();
		    	    	   	dv.startDashBoard();
		    	    	   	
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
