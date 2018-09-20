package com.pos.account;

import java.util.Date;

public class SystemAccount {

	 private int accountID;
	 private String fName;
	 private String mName;
	 private String lastName;
	 private Date dateCreated;
	
	
	 
	public SystemAccount() {
		
		
	}



	public int getAccountID() {
		return accountID;
	}



	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}



	public String getfName() {
		return fName;
	}



	public void setfName(String fName) {
		this.fName = fName;
	}



	public String getmName() {
		return mName;
	}



	public void setmName(String mName) {
		this.mName = mName;
	}


	

	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	

	
	
	    
}
