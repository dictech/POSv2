package com.pos.account.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

import javafx.scene.image.Image;






public class AttendantDAO {

private static String AttendantmageUri;
	
	
public static String getAttendantmageUri() {
	return AttendantmageUri;
}

public static void setAttendantmageUri(String attendantmageUri) {
	AttendantmageUri = attendantmageUri;
}

public static void createNewAttendant(Attendant attendant) throws Exception{
	 
		  File file = new File(getAttendantmageUri());
		  FileInputStream fis = new FileInputStream(file); 

		Connection dbConnection =  Database.getDatabaseConnection();
		String sqlQuery = "insert into "
					      + "attendant(attdt_id,attdt_first_name,attdt_mid_name,"
					      			  +"attdt_surname,attdt_gender,attdt_dob,"
					      			  +"attdt_address,attdt_phone_no,attdt_email,"
					      			  +"attdt_dt_emp,attdt_position,attdt_image)"
						  + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = dbConnection.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS);
			stmt.setBigDecimal(1, attendant.getId());
			stmt.setString(2, attendant.getfName());
			stmt.setString(3, attendant.getmName());
			stmt.setString(4, attendant.getSurname());
			stmt.setString(5, attendant.getGender());
			stmt.setObject(6,attendant.getDob());
			stmt.setString(7, attendant.getAddress());
			stmt.setString(8, attendant.getPhoneNo());
			stmt.setString(9, attendant.getEmail());
			stmt.setObject(10,attendant.getDoe());
			stmt.setString(11,attendant.getPosition());
			stmt.setBinaryStream(12,(InputStream)fis,(int)file.length());
			stmt.executeUpdate();
		    ResultSet result = stmt.getGeneratedKeys();
		    if(result.next()) {
		    	attendant.setId(result.getBigDecimal(1));
		    }
			
		
			stmt.close();
			dbConnection.close();
			
			             
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
 
 public static List<Attendant> getAllAttendants() {
	 List<Attendant> listOfAttendants =  new ArrayList<Attendant>();
	 Connection conn = Database.getDatabaseConnection();
	 try {
		    
		ResultSet records = conn.createStatement().executeQuery("select * from attendant");
		while(records.next()) {

			Attendant attendant = new Attendant();
			attendant.setId(records.getBigDecimal(1));
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
			attendant.setImage(new Image(records.getBinaryStream(12)));
			listOfAttendants.add(attendant);
		}
		
		listOfAttendants.forEach((attd)-> System.out.println(attd.getfName() + " " + attd.getSurname()));
		
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 
	return listOfAttendants;
 }
 
 
 public static Attendant getAttendant(BigDecimal id) {
	 Attendant attendant =  new Attendant();
	 Connection conn = Database.getDatabaseConnection();
	 
	 try {
		
		PreparedStatement stmt = conn
								 .prepareStatement("select * from attendant where attdt_id = ?");
		stmt.setBigDecimal(1, id);
		ResultSet record = stmt.executeQuery();
		if(record.next()) {
			attendant.setId(record.getBigDecimal(1));
			attendant.setfName(record.getString(2));
			attendant.setmName(record.getString(3));
			attendant.setSurname(record.getString(4));
			attendant.setGender(record.getString(5));
			attendant.setDob(record.getDate(6));
			attendant.setAddress(record.getString(7));
			attendant.setPhoneNo(record.getString(8));
			attendant.setEmail(record.getString(9));
			attendant.setDoe(record.getDate(10));
			attendant.setPosition(record.getString(11));
			attendant.setImage(new Image(record.getBinaryStream(12)));
		}
		
		
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 
	return attendant;
 }
 public static void updateAttendant(Attendant attendant) {
	 
	    String sql = "UPDATE attendant"
    		+ " SET attdt_first_name=?,"
    		+ "     attdt_mid_name=?,"
    		+ "     attdt_surname=?,"
    		+ "     attdt_gender=?,"
    		+ "     attdt_dob=?,"
    		+ "     attdt_address=?,"
    		+ "     attdt_phone_no=?,"
    		+ "     attdt_email=?,"
    		+ "     attdt_dt_emp=?,"    
    		+ "     attdt_position=?, "
    		+ "     attdt_image = ? "
    		+ " WHERE attdt_id=?";
	     try {
	    	 

	     Connection myConnect = Database.getDatabaseConnection();
	     PreparedStatement prepareStatement = myConnect.prepareStatement(sql);
		  File file = new File(getAttendantmageUri());
		  FileInputStream fis = new FileInputStream(file); 
		  
	    prepareStatement.setString(1, attendant.getfName());
	    prepareStatement.setString(2, attendant.getmName());
	    prepareStatement.setString(3, attendant.getSurname());
	    prepareStatement.setString(4, attendant.getGender());
	    prepareStatement.setDate(5,   attendant.getDob());
	    prepareStatement.setString(6, attendant.getAddress());
	    prepareStatement.setString(7, attendant.getPhoneNo());
	    prepareStatement.setString(8, attendant.getEmail());
	    prepareStatement.setDate(9,   attendant.getDoe());
	    prepareStatement.setString(10, attendant.getPosition());
	    prepareStatement.setBinaryStream(11, (InputStream)fis,(int)file.length());
	    prepareStatement.setBigDecimal(12, attendant.getId());
	    prepareStatement.executeUpdate();
	    prepareStatement.close();
	    myConnect.close();
	      
	    
	     }catch(Exception e) {
	    	 
	    	 e.printStackTrace();
	     }
 }
 
 
 public static void deleteAttendant(BigDecimal id) {
	    String sql="DELETE FROM Attendant WHERE id = ?";
		Database.deleteFromTable(sql, id);
	}

}
