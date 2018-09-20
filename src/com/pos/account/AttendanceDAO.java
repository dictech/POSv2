package com.pos.account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import com.pos.database.Database;

public class AttendanceDAO {
	
	   private static Connection myConnect;
	    private static PreparedStatement prepareStatement;
	     private static ResultSet result;
	      private static Statement statement;
	
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
	    	         
	    	                   prepareStatement.executeUpdate();
	    	                       attendance.setHasSignedIn(true);
 	                                  prepareStatement.close();
 	                                    myConnect.close();
	    	    	          
	    	          }
	    	                       
	    	
	    	catch(Exception e)
	    	{
	    		
	    		e.printStackTrace();
	    	
	    	               }
	    	  }
	    	
	       
	    	
		      public static void readAttendance(Attendance attendance) {
		    	  
		    	  try {
		    		  
		    		     myConnect = Database.getDatabaseConnection();
		    		      String sqlQuery = "SELECT * FROM attendance";
		    		       statement = myConnect.createStatement();
		    		        result = statement.executeQuery(sqlQuery);
		    		        
		  	                while(result.next()) {
		  	                 attendance.setAttenc_ID(result.getInt(1));
		  	    	          attendance.setAttenc_attendt_Id(result.getInt(2));
		  	    	           attendance.setAttendcFirstName(result.getString(3));
		  	    	            attendance.setAttendcLastName(result.getString(4));
		  	    	             attendance.setSignInTime(result.getString(5));
		  	    	              attendance.setSignOutTime(result.getString(6));
		  	    	               attendance.setLastSeen(result.getDate(7));
		  	    	        
		  	    	           System.out.println(result.getString("attdnc_first_name")+" "+result.getString("attdnc_last_name"));
		  	                }
		  	              
		  	            result.close();
 	    	             myConnect.close();
		    	  }
		    	  catch(Exception error) {
		    		  
		    		    error.printStackTrace();
		    	  }
		    	  
		   	  
	              
		      }
	   
	}

	    

