package com.pos.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pos.database.Database;

public class Branch {
       private int branchID;
	    private String branchName;
	     private String branchAddr;
	      private String branchState;
	       private String branchLga;
	   
	   
	   // a method which is called any time a branch needs to be created.
	   public void createBranch() {
		    Connection myConnect = null;
		     PreparedStatement prepareStatement = null;
		    
		   try {
			   myConnect = Database.getDatabaseConnection();
			     prepareStatement = myConnect.prepareStatement("insert into branch "
			   	   + "(branchName,branchAddress,branchState,branchLGA)"
			   		+ " values(?,?,?,?);");
			          prepareStatement.setString(1,this.branchName);
			           prepareStatement.setString(2,this.branchAddr);
			            prepareStatement.setString(3,this.branchState);
			             prepareStatement.setString(4,this.branchLga);
			               prepareStatement.execute();
			                 System.out.println("Branch Succesfully created!");
			                  prepareStatement.close();
			                    myConnect.close();}
		   
		    catch(Exception error){
			    error.printStackTrace();}
		   
		   // try and catch stops here.	   
	   }

	   // a method which is called any time a branch need some information from a db.
	   public void readBranch() {
		   
	   }
	   
	   public void updateBranch() {
		   
	   }
	   
	   public void deleteBranch() {
  
	   }
	   

	public int getBranchID() {
		return branchID;
	}
	

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}
	

	public String getBranchName() {
		return branchName;
	}
	

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	

	public String getBranchAddr() {
		return branchAddr;
	}
	

	public void setBranchAddr(String branchAddr) {

		this.branchAddr = branchAddr;
	}
	

	public String getBranchState() {
		return branchState;
	}
	

	public void setBranchState(String branchState) {

		this.branchState = branchState;
	}
	

	public String getBranchLga() {

		return branchLga;
	}
	

	public void setBranchLga(String branchLga) {
		this.branchLga = branchLga;
	}

	


	   
}
