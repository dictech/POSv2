package com.pos.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.pos.database.Database;

public class SystemAccountDAO {

	   private static  Connection myConnect;
	   private static ResultSet result;
	   private static Statement statement;
	   private static PreparedStatement prepareStatement;
	   
	   public static void createSystemAccount(SystemAccount act) {
		   
		    /* creating a system Account is like creating an Attendant, therefore we do not need to create a system account
		     * all we would do is set a method that fills in the account table immediately An Attendant has been created.
		     * so when a user comes to login , the system gets the  information directly from the account table.
		     */
		   
	   }
	   
	   
	   
	   public static void logInSystemAccount(int userID) {
             SystemAccount act = new SystemAccount();
		      try {
		    	  
		    	 String sql = "SELECT * FROM attendant"
		    	 		+ " WHERE attdt_id=?";
		    	  myConnect = Database.getDatabaseConnection();
		    	   prepareStatement = myConnect.prepareStatement(sql);
		    	    prepareStatement.setInt(1, userID);
		    	     result = prepareStatement.executeQuery();
		    	     
		    	      if(result.next()) {
		    	    	  String lastName = "attdt_surname";
		    	    	   String space = " ";
		    	    	    String firstName = "attdt_first_name";
		    	    	     String wl = "Welcome";
		    	    	  
		    	    	 act.setAccountID(result.getInt(1));  
		    	    	  act.setfName(result.getString(2));
		    	    	   act.setmName(result.getString(3));
		    	    	    act.setLastName(result.getString(4));
		    	    	     System.out.println(wl+space+result.getString(lastName)+space+result.getString(firstName));
		    	    	     
		    	      }
		    	      
		    	  
		      }
		      catch(Exception e) {
		    	  
		    	  e.printStackTrace();
		      }
		   
		   
	   }

	   
	   public void logOutSystemAccount() {
		   
		   
	   }
	   
	   
}
