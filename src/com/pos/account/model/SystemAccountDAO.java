package com.pos.account.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

import com.pos.database.Database;

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
	   
	   
	   
	   public static void logInSystemAccount(String userName, String password) {
		               
		   
		             if(userName == null || password == null) {
		            	 
		            	    System.out.println("please Enter userName or password.");
		             }
		             
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
		    	    	    AttendanceDAO.readAllAttendance();
		    	    	    AttendanceDAO.getSingleAttendance(result.getBigDecimal(1));
		    	    	    System.out.println("Welcome !");
		    	      }else {
		    	    	      System.out.println("Invalid userName or password !");
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
	   
	   
	   /*
	    *  below is the list of methods 
	    *  that will be called on other
	    *   classes for functionality.
	    */

	   
	     public static void fillAccountDetails() {
	    	 SystemAccount act = new SystemAccount ();
	    	   Attendant attd = new Attendant();
			     act.setActAttendantID(attd.getId());
			     act.setPassword(act.getPassword());
			     act.setUserName(act.getUserName());
			     SystemAccountDAO.createSystemAccount(act);
	                  
		   	 
	       }
	   
	   
		   public static void fillAttendance(Attendance attendance) {
				   final Date date;
				 Attendant attendant = new Attendant();
			
				     attendance.setAttenc_attendt_Id(attendant.getId());
				     attendance.setAttendcFirstName(attendant.getfName());
				     attendance.setAttendcLastName(attendant.getSurname());
				     // attendance.setSignInTime(Time.);
				    //  attendance.setDate();
				      AttendanceDAO.createAttendance(attendance);
			 }

	   
	   
	   
	   
	   
}
