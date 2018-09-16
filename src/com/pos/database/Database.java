package com.pos.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
	
	static String dbUrl = "jdbc:mysql://localhost:3306/posv2";
	static String dbUsername = "root";
	static String dbPassword = "Manchester81#";
	static Connection conn = null;
	
	public static Connection getDatabaseConnection() {
				
		try {
			conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
	
	public static void deleteFromTable(String sql, BigDecimal id) {
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    PreparedStatement stmt = cxtn.prepareStatement(sql);
		    
		    stmt.setBigDecimal(1,id);
		    stmt.execute();
		    stmt.close();
		    cxtn.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
