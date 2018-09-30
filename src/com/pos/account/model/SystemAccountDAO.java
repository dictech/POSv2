package com.pos.account.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pos.database.Database;

public class SystemAccountDAO {

	   private static  Connection myConnect;
	   private static ResultSet result;
	   private static PreparedStatement prepareStatement;
	   
	   
	   
	   public static void createSystemAccount(SystemAccount act) {
		        
		      try {
		    	  String sql = "INSERT INTO account ("
		    	  		+ "      acct_attdt_id,"
		    	  		+ "      acct_username,"
		    	  		+ "      acct_password       )"
		    	  		+ " VALUES   (?,?,?)";
		    	  
		    	 myConnect = Database.getDatabaseConnection();
		    	  prepareStatement = myConnect.prepareStatement(sql);
		    	   prepareStatement.setInt(1, act.getActAttendantID());
		    	    prepareStatement.setString(2, act.getUserName());
		    	     prepareStatement.setString(3, act.getPassword());
                       prepareStatement.execute();
                        myConnect.close();
		    	  
		    	  
		        }
		   catch(Exception e) {
			   
			   e.printStackTrace();
		   }
		   
	   }
	   
	   
	   
	   public static void logInSystemAccount(String userName, String password) {
		   Attendance attendance = new Attendance();
             SystemAccount act = new SystemAccount();
		      try {
		    	  
		    	 String sql = "SELECT * FROM account"
		    	 		+ " WHERE acct_username=?"
		    	 		+ " AND acct_password=?";
		    	  myConnect = Database.getDatabaseConnection();
		    	   prepareStatement = myConnect.prepareStatement(sql);
		    	    prepareStatement.setString(1, userName);
		    	     prepareStatement.setString(2, password);
		    	      result = prepareStatement.executeQuery();
		    	     
		    	      if(result.next()) {
		    	    	   String space = " ";
		    	    	     String wl = "Welcome";
		    	    	  
		    	    	 act.setAccountID(result.getInt(1));  
		    	    	  act.setActAttendantID(result.getInt(2));
		    	    	   act.setUserName(result.getString(3));
		    	    	    act.setPassword(result.getString(4));
		    	    	     System.out.println(wl+space+result.getString(3));
		    	    	     
		    	    	        fillAttendance(attendance);
		    	    	         prepareStatement.close();
		    	    	          myConnect.close();
		    	      }
		    	      
		    	  
		      }
		      catch(Exception e) {
		    	  
		    	  e.printStackTrace();
		      }
		   
		   
	   }
	   
		 
	   public static void fillAttendance(Attendance attendance) {
			 
			 Attendant attendant = new Attendant();
			   Date signInTime = new Date();
			    Date signOutTime = new Date();
			     Date date = new Date();
			     
			   
		     String  formatSignIn = new SimpleDateFormat("E hh:mm a").format(signInTime);
			  String formatSignOut = new SimpleDateFormat("E hh:mm a").format(signOutTime);
			   String formatDate = new SimpleDateFormat("E dd/MM/yyyy a").format(date);
			     attendance.setAttenc_attendt_Id(attendant.getId());
			      attendance.setAttendcFirstName(attendant.getfName());
			       attendance.setAttendcLastName(attendant.getSurname());
			        attendance.setHasSignedIn(true);
			         attendance.setSignInTime(formatSignIn);
			          attendance.setDate(new Date());
			           AttendanceDAO.createNewAttendance(attendance);
		 }

	   
	   public static  void logOutSystemAccount(Attendance attendance) {
		   
		   try {
			   String query = "UPDATE attendace"
			   		+ " SET         attdnc_attdnt_id=?"
			   		+ "             attdnc_first_name=?"
			   		+ "             attdnc_last_name=?"
			   		+ "             attdnc_signin_time=?"
			   		+ "             attdnc_signout_time=?"
			   		+ "             has_loged_in=?"
			   		+ "             has_loged_out=?"
			   		+ "             attdnc_date=?"
			   		+ "WHERE        attdnc_attdnt_id=?";
			   
			   myConnect = Database.getDatabaseConnection();
			    prepareStatement = myConnect.prepareStatement(query);
			     prepareStatement.setBigDecimal(1, attendance.getAttenc_attendt_Id());
			      prepareStatement.setString(2, attendance.getAttendcFirstName());
			       prepareStatement.setString(3, attendance.getAttendcFirstName());
			        prepareStatement.setString(4, attendance.getSignInTime());
			         prepareStatement.setString(5, attendance.getSignOutTime());
			          prepareStatement.setBoolean(6, attendance.isHasSignedIn());
			        prepareStatement.setBoolean(7, attendance.isHasSignedOut());
			       prepareStatement.setDate(8, (java.sql.Date) attendance.getDate());
			      
			       prepareStatement.executeUpdate();
			      prepareStatement.close();
			     myConnect.close();
			
		   }
		   catch(Exception E) {
			   
			     E.printStackTrace();
		   }
	   }
	   
	     // logout method ends here.
	   
	   public static void logOut(Attendance attendance) {
		   
		   logOutSystemAccount(attendance);
	   }
	   
}
