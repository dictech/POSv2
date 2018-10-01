package com.pos.org;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.pos.database.Database;

public class BranchDAO {

	public static Connection myConnect;
	  public static PreparedStatement prepareStatement;
	    public static Statement stmt;
	      public static ResultSet result;
	      
 // a method which is called any time a branch needs to be created.
   public static void createBranch(Branch branch) {

	     try {
		    myConnect = Database.getDatabaseConnection();
		      prepareStatement = myConnect.prepareStatement("insert into branch "
		   	    + "(branchName,branchAddress,branchState,branchLGA)"
		   	  	 + " values(?,?,?,?);");
		        prepareStatement.setString(1,branch.getBranchName());
		        prepareStatement.setString(2,branch.getBranchAddr());
		        prepareStatement.setString(3,branch.getBranchState());
		        prepareStatement.setString(4,branch.getBranchLga());
		        prepareStatement.execute();
		        System.out.println("Branch Succesfully created!");
		        prepareStatement.close();
		                    myConnect.close();}
	   
	    catch(Exception error){
		    error.printStackTrace();}
	   
	   // try and catch stops here.	   
 }
 
 // a method that is called any time we want to get information from a branch.

   
 public static Branch readBranch(int id) {
	        Branch branch = new Branch();
	  try {
		  myConnect = Database.getDatabaseConnection();
		   prepareStatement = myConnect.prepareStatement("select * from branch "
		    + "where branchID=?");
		    prepareStatement.setInt(1, id);
		     result = prepareStatement.executeQuery();
		    if(result.next()) {
		     branch.setBranchID(result.getInt(1));
		     branch.setBranchName(result.getString("branchName"));
		     branch.setBranchAddr(result.getString("branchAddress"));
		     branch.setBranchState(result.getString("branchState"));
		      branch.setBranchLga(result.getString("branchLGA"));
		    	 
		     }
		  
	  } catch(Exception error) {error.printStackTrace();} 
	  
	  return branch;
 }
 
 
 public static void updateBranch(Branch branch) {
	  
	     try {
	    	 
	    	 myConnect = Database.getDatabaseConnection();
	    	   prepareStatement = myConnect.prepareStatement("UPDATE branch"
	    	 		+ " SET 	branchName=?,"
	    	 		+ " 		branchAddress=?,"
	    	 		+ " 		branchState=?,"
	    	 		+ " 		branchLGA=?"
	    	 		+ " WHERE branchID=?");
	    	   // prepared statement for Branch.
	    	       prepareStatement.setString(1, branch.getBranchName());
	    	        prepareStatement.setString(2, branch.getBranchAddr());
	    	         prepareStatement.setString(3, branch.getBranchState());
	    	          prepareStatement.setString(4, branch.getBranchLga());
	    	           prepareStatement.setInt(5, branch.getBranchID());
	    	            prepareStatement.executeUpdate();
	    	            System.out.print("update complete !");
	    	             prepareStatement.close();
	    	             myConnect.close();
	    	         
	  		    	            
	                   
	                     
	    	           
	    	             
	     }catch(Exception e) {
	    	  e.printStackTrace();
	     }  
 }
 
 public static void deleteBranch(int id) {

             
	    try {
	    	    String sql = "DELETE FROM branch"
	    	    		+ " WHERE branchID=?";   
	    	
	    	    myConnect = Database.getDatabaseConnection();
	    	     prepareStatement = myConnect.prepareStatement(sql);
	    	      prepareStatement.setInt(1, id);
	    	       prepareStatement.executeUpdate();
	    	         prepareStatement.close();
	    	           myConnect.close();
	    	
	    }catch(Exception e) {
	    	
	    	e.printStackTrace();
	    }
 }
 
       
}
