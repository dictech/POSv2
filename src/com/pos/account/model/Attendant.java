package com.pos.account.model;

import java.math.BigDecimal;
import java.sql.Date;
import javafx.scene.image.Image;




public class Attendant {
	
	 private BigDecimal id;
	 private String fName;
	 private String mName;
	 private String surname;
	 private String gender;
	 private Date   dob;
	 private String address; 
	 private String phoneNo; 
	 private String email; 
	 private  Date   doe; 
	 private Image image;
	 
	 
	 public Date getDoe() {
		return doe;
	}
	private String position;
	 
	
		
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public  Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public void setDoe(Date doe) {
		this.doe = doe;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}

	
	
}

	
