package com.pos.account.model;

import java.math.BigDecimal;

public class SystemAccount {

	 private BigDecimal accountID;
	 private BigDecimal actAttendantID;
	 private String userName;
	 private String password;
	
	
	 
	public SystemAccount() {
	
	}
	



	public BigDecimal getAccountID() {
		return accountID;
	}




	public void setAccountID(BigDecimal accountID) {
		this.accountID = accountID;
	}




	public BigDecimal getActAttendantID() {
		return actAttendantID;
	}




	public void setActAttendantID(BigDecimal actAttendantID) {
		this.actAttendantID = actAttendantID;
	}




	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	

	
	
	    
}
