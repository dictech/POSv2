package com.pos.account.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



public class Attendance {

	       // An activity that records all the daily attendance of an attendant.
	private BigDecimal attenc_ID;
	 private BigDecimal attenc_attendt_Id;
	  private String attendcFirstName;
	   private String attendcLastName;
	   private Time signOutTime;
	 private Time signInTime;
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




	public BigDecimal getAttenc_attendt_Id() {
		return attenc_attendt_Id;
	}



	public void setAttenc_attendt_Id(BigDecimal attenc_attendt_Id) {
		this.attenc_attendt_Id = attenc_attendt_Id;
	}







	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	


	public BigDecimal getAttenc_ID() {
		return attenc_ID;
	}



	public void setAttenc_ID(BigDecimal attenc_ID) {
		this.attenc_ID = attenc_ID;
	}



	public Time getSignOutTime() {
		return signOutTime;
	}



	public void setSignOutTime(Time signOutTime) {
		this.signOutTime = signOutTime;
	}



	public Time getSignInTime() {
		return signInTime;
	}



	public void setSignInTime(Time signInTime) {
		this.signInTime = signInTime;
	}




	
}
