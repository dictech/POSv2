package com.pos.account;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Attendance {

	       // An activity that records all the daily attendance of an attendant.
	private int attenc_ID;
	 private int attenc_attendt_Id;
	  private String attendcFirstName;
	   private String attendcLastName;
	    private boolean HasSignedIn;
	     private boolean hasSignedOut;
	   private String signOutTime;
	 private String signInTime;
	private Date date;
	
	
	
	
public Attendance() {
		
	}
	


	public String getAttendcFirstName() {
		return attendcFirstName;
	}

	public void setAttendcFirstName(String attendcFirstName) {
		this.attendcFirstName = attendcFirstName;
	}


	public String getAttendcLastName() {
		return attendcLastName;
	}



	public void setAttendcLastName(String attendcLastName) {
		this.attendcLastName = attendcLastName;
	}




	public int getAttenc_attendt_Id() {
		return attenc_attendt_Id;
	}



	public void setAttenc_attendt_Id(int attenc_attendt_Id) {
		this.attenc_attendt_Id = attenc_attendt_Id;
	}





	public int getAttenc_ID() {
		return attenc_ID;
	}





	public void setAttenc_ID(int attenc_ID) {
		this.attenc_ID = attenc_ID;
	}


	




	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public boolean isHasSignedIn() {
		return HasSignedIn;
	}



	public void setHasSignedIn(boolean hasSignedIn) {
		HasSignedIn = hasSignedIn;
	}



	public boolean isHasSignedOut() {
		return hasSignedOut;
	}



	public void setHasSignedOut(boolean hasSignedOut) {
		this.hasSignedOut = hasSignedOut;
	}



	public String getSignOutTime() {
		return signOutTime;
	}



	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}



	public String getSignInTime() {
		return signInTime;
	}



	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}









	
	
}
