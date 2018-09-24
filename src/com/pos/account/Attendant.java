package com.pos.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class Attendant {
	
	 private BigDecimal id;
	 private String fName;
	 private String mName;
	 private String surname;
	 private String gender;
	 private LocalDate   dob;
	 private String address; 
	 private String phoneNo; 
	 private String email; 
	 private LocalDate   doe; 
	 private String position;
	 
	 private static void createNewAttendant(Attendant attendant) {
			boolean isCreated;
			
			//get database connection
			Connection dbConnection =  Database.getDatabaseConnection();
			//set SQL query
			String sqlQuery = "insert into "
						      + "attendant(attdt_id,attdt_first_name,attdt_mid_name,"
						      			  +"attdt_surname,attdt_gender,attdt_dob,"
						      			  +"attdt_address,attdt_phone_no,attdt_email,"
						      			  +"attdt_dt_emp,attdt_position)"
							  + "values(?,?,?,?,?,?,?,?,?,?,?)";
			//execute SQL query
			try {
				PreparedStatement stmt = dbConnection.prepareStatement(sqlQuery);
				stmt.setInt(1, attendant.getId());
				stmt.setString(2, attendant.getfName());
				stmt.setString(3, attendant.getmName());
				stmt.setString(4, attendant.getSurname());
				stmt.setString(5, attendant.getGender());
				stmt.setDate(6,(java.sql.Date) attendant.getDob());
				stmt.setString(7, attendant.getAddress());
				stmt.setString(8, attendant.getPhoneNo());
				stmt.setString(9, attendant.getEmail());
				stmt.setDate(10,(java.sql.Date) attendant.getDoe());
				stmt.setString(11,attendant.getPosition());
				
				isCreated = stmt.execute();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	 
     private static void readAllAttendants() {
    	 List<Attendant> listOfAttendants =  new ArrayList<Attendant>();
    	 Connection conn = Database.getDatabaseConnection();
    	 try {
    		ResultSet records = conn.createStatement().executeQuery("select * from attendant");
    		while(records.next()) {
    			Attendant attendant = new Attendant();
    			attendant.setId(records.getInt(1));
    			attendant.setfName(records.getString(2));
    			attendant.setmName(records.getString(3));
    			attendant.setSurname(records.getString(4));
    			attendant.setGender(records.getString(5));
    			attendant.setDob(records.getDate(6));
    			attendant.setAddress(records.getString(7));
    			attendant.setPhoneNo(records.getString(8));
    			attendant.setEmail(records.getString(9));
    			attendant.setDoe(records.getDate(10));
    			attendant.setPosition(records.getString(11));
    			listOfAttendants.add(attendant);
    		}
    		
    		listOfAttendants.forEach((attd)-> System.out.println(attd.getfName() + " " + attd.getSurname()));
    		
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }


	 private static void updateAttendant(Attendant attendant) {
		// TODO Auto-generated method stub
		
	}
	 private static void deleteAttendant(Attendant attendant) {
			
		}
		
	public int getId() {
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
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
	public LocalDate getDoe() {
		return doe;
	}
	public void setDoe(LocalDate doe) {
		this.doe = doe;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

}

	
