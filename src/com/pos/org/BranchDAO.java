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
	      
	      public static void getBranch(Branch branch) {
	    	    try {
	    	      myConnect = Database.getDatabaseConnection();
	    	       prepareStatement = myConnect.prepareStatement("SELECT * FROM brach");
	    	    }catch(Exception e) {e.printStackTrace();}
	      }
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
 // a method which is called any time a branch need some information from a db.
 public static void readBranch(Branch branch) {
	  try {
		  myConnect = Database.getDatabaseConnection();
		   prepareStatement = myConnect.prepareStatement("select * from branch "
		    + "where branchID=?");
		        prepareStatement.setInt(1, branch.getBranchID());
		          result = prepareStatement.executeQuery();
		            while(result.next()) {
		               int brnID = branch.getBranchID();
		               String brnName = "branchName";
		            String brnAddr = "branchAddress";
		         String brnState = "branchState";
		       String brnLga = "branchLGA";
		    	 
		     System.out.println("Branch office information".toUpperCase()+"\n");
		     System.out.println("DATA \t VALUE \n");
		    	    System.out.println(
		    	    "Branch ID : " + result.getInt(brnID)+" "+ "\n"+
		    	    "Branch Name : " + result.getString(brnName)+" "+ "\n"+
		    	    "Branch Addrress : " + result.getString(brnAddr)+" "+ "\n"+
		    	    "Branch State : "+ result.getString(brnState)+" "+ "\n"+
		    	    "Branch local government Area : " + result.getString(brnLga));
		    	    prepareStatement.close();
		    	    myConnect.close();
		     }
		  
	  } catch(Exception error) {error.printStackTrace();} 
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
	    	   // prepared statement for Branch Name.
	    	       prepareStatement.setString(1, branch.getBranchName());
	    	        prepareStatement.setString(2, branch.getBranchAddr());
	    	         prepareStatement.setString(3, branch.getBranchState());
	    	          prepareStatement.setString(4, branch.getBranchLga());
	    	           prepareStatement.setInt(5, branch.getBranchID());
	    	            prepareStatement.executeUpdate();
	    	             prepareStatement.close();
	    	             myConnect.close();
	    	         
	  		    	            
	                   
	                     
	    	           
	    	             
	     }catch(Exception e) {
	    	  e.printStackTrace();
	     }  
 }
 
 public static void deleteBranch() {

 }
 

}
