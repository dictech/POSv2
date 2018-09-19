package com.pos.account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

import com.pos.database.Database;

public class AttendanceDAO {
	
	   private static Connection myConnect;
	    private static PreparedStatement prepareStatement;
	     private static ResultSet result;
	
	       public static void createAttendance(Attendance attendance ) {
	    	   
	    	try {
	    	String signIn = "INSERT INTO attendance ("
	    			+ "			attdnc_attdnt_id,"
	    			+ "			attdnc_first_name,"
	    			+ "			attdnc_last_name,"
	    			+ "			attdnc_signin_time,"
	    			+ "			attdnc_signout_time,"
	    			+ "			attdnc_date)"
	    			+ " VALUES (?,?,?,?,?,?)";
	    	
	    	    myConnect = Database.getDatabaseConnection();
	    	     prepareStatement = myConnect.prepareStatement(signIn);
	    	             
	    	     prepareStatement.setInt(1, attendance.getAttenc_attendt_Id());
	    	             prepareStatement.setString(2, attendance.getAttendcFirstName());
	    	              prepareStatement.setString(3, attendance.getAttendcLastName());
	    	               prepareStatement.setString(4, attendance.getSignInTime());
	    	                prepareStatement.setString(5, attendance.getSignOutTime());
	    	                 prepareStatement.setDate(6,(java.sql.Date) attendance.getLastSeen());
	    	         
	    	                   prepareStatement.execute();
	    	                       attendance.setHasSignedIn(true);
 	                                  prepareStatement.close();
 	                                    myConnect.close();
	    	    	          
	    	          }
	    	                       
	    	
	    	catch(Exception e)
	    	{
	    		
	    		e.printStackTrace();
	    	
	    	               }
	    	  }
	    	
	       
	    	
		      public static void readAttendance() {
		    	  
		    	  
		    	  
		    	  
		   	/*       
	              while(result.next()) {
	    	      attendance.setAttenc_attendt_Id(attendant.getId());
	    	       attendance.setAttendcFirstName(attendant.getfName());
	    	        attendance.setAttendcLastName(attendant.getSurname());
	    	         attendance.setSignInTime(attendance.getSignInTime());
	    	          attendance.setSignOutTime(attendance.getSignOutTime());
	    	           attendance.setLastSeen(attendance.getLastSeen());
	              }
	              */
		      }
	   
	}

	    

