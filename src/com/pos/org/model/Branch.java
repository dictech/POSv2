package com.pos.org.model;


import com.pos.database.Database;

public class Branch {
       private int branchID;
	    private String branchName;
	     private String branchAddr;
	      private String branchState;
	       private String branchLga;

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
