package com.pos.account.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class AttendanceDAO {
	
	   private static Connection myConnect;
	    private static PreparedStatement prepareStatement;
	     private static ResultSet result;
	      private static Statement statement;
	
	       public static void createAttendance(Attendance attendance, BigDecimal id, String firstName, String lastName) {
	    	   
	    	try {
	    	String signIn = "INSERT INTO attendance ("
	    			
	    			+ "         attdnc_attdnt_id,"
	    			+ "			attdnc_attdnt_firstName,"
	    			+ "			attdnc_attdnt_lastName,"
	    			+ "			attdnc_signin_time,"
	    			+ "			attdnc_signout_time,"
	    			+ "         attdnc_date )"
	    			+ " VALUES (?,?,?,?,?,?)";
	    	
	    	    myConnect = Database.getDatabaseConnection();
	    	     prepareStatement = myConnect.prepareStatement(signIn);
	    	        
	    
	    	     prepareStatement.setBigDecimal(1, id);
	    	             prepareStatement.setString(2, firstName);
	    	              prepareStatement.setString(3, lastName);
	    	               prepareStatement.setObject(4,  Time.valueOf(LocalTime.now())); 
	    	                prepareStatement.setObject(5, attendance.getSignOutTime());
	    	                 prepareStatement.setObject(6, Date.valueOf(LocalDate.now()));
	    	         
	    	                    prepareStatement.executeUpdate();
	    	                    System.out.print("your Attendance for today has been  created ! ");
 	                               prepareStatement.close();
 	                                myConnect.close();
	    	    	          
	    	          }
	    	                       
	    	
	    	catch(Exception e)
	    	{
	    		
	    		e.printStackTrace();
	    	
	    	               }
	    	  }
	    	
	       
	    	
		      public static List<Attendance> readAllAttendance() {
		    	       List<Attendance> attendanceList =  new ArrayList<Attendance>();
		    	  try {
		    		  
		    		       myConnect = Database.getDatabaseConnection();
		    		       String sqlQuery = "SELECT * FROM attendance";
		    		       statement = myConnect.createStatement();
		    		       result = statement.executeQuery(sqlQuery);
		    		        
		  	                while(result.next()) {
		  	                Attendance attendance =  new Attendance();	
		  	                attendance.setAttenc_ID(result.getBigDecimal(1));
		  	    	        attendance.setAttenc_attendt_Id(result.getBigDecimal(2));
		  	    	        attendance.setAttendcFirstName(result.getString(3));
		  	    	        attendance.setAttendcLastName(result.getString(4));
		  	    	        attendance.setSignInTime(result.getTime(5));
		  	    	        attendance.setSignOutTime(result.getTime(6));
		  	    	        attendance.setDate(result.getDate(7));
		  	    	        
		  	    	        attendanceList.add(attendance);
		  	    	        
		  	    	    
		  	                }
		  	              
		  	            result.close();
 	    	             myConnect.close();
		    	  }
		    	  catch(Exception error) {
		    		  
		    		    error.printStackTrace();
		    	  }
		    	  
		   	    
	            return attendanceList;  
	            
		      }
	   
		      //code for readAttendance Ends here.
		      
		  
		      public static  Attendance checkAttendance(BigDecimal id, Date date) {
	            	Attendance attendance = new Attendance();
	            	   try {
	            		   String sql = "SELECT * from attendance "
	            		   		      + " WHERE    attdnc_attdnt_id =?"
	            		   		      + " AND      attdnc_date=?";
	            		    myConnect = Database.getDatabaseConnection();
	            		    prepareStatement = myConnect.prepareStatement(sql);
	            		    prepareStatement.setBigDecimal(1, id);
	            		    prepareStatement.setDate(2, date);
	            		    result = prepareStatement.executeQuery();
	            		    
	            		    if(result.next()) {
	            		    	
	            		    	 attendance.setAttenc_ID(result.getBigDecimal(1));
	            		    	 attendance.setAttenc_attendt_Id(result.getBigDecimal(2));
	            		    	 attendance.setAttendcFirstName(result.getString(3));
	            		    	 attendance.setAttendcLastName(result.getString(4));
	            		    	 attendance.setSignInTime(result.getTime(5));
	            		    	 attendance.setSignOutTime(result.getTime(6));
	            		    	 attendance.setDate(result.getDate(7));
	            		    
	            		    	 System.out.println("your Attendance Already Exist!");
	            		    	 
	            		    }else {
	            		    	System.out.println("sorry your Attendance list is not present! and will be created soon. ");
	            		    	System.out.println("Please Wait... ");
	            	
	            			    String query = "SELECT * from attendant "
		            		   		      + " WHERE      attdt_id =?";
		            		    myConnect = Database.getDatabaseConnection();
		            		    prepareStatement = myConnect.prepareStatement(query);
		            		    prepareStatement.setBigDecimal(1, id);
		            		    
		            		    result = prepareStatement.executeQuery();
		            		    
		            		    if(result.next()) {
		            		    	      
		            		   Attendant attendant = new Attendant();
		            		   AttendanceDAO.createAttendance(attendance, id,
		            		   attendant.getfName(), attendant.getSurname());
		            		   
			    	    	         }
	            		    
	            		    }
	            		   
	            		   
	            		   
	            	   }catch(Exception e) {
	            		  
	            		    e.printStackTrace();
	            	   }
	            	
	            	return attendance;
	            }     

	
	   
		   
	}

	    

