package com.pos.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
	
<<<<<<< HEAD
	static String dbUrl = "jdbc:mysql://127.0.0.1:3306/posv2";
	static String dbUsername = "root";
	static String dbPassword = "Manchester81#";
||||||| merged common ancestors
	static String dbUrl = "jdbc:mysql://127.0.0.1:3306/posv2";
	static String dbUsername = "root";
	static String dbPassword = "esthermylove";
=======
	static String dbUrl = "jdbc:mysql://192.168.8.104:3306/posv2";
	static String dbUsername = "office";
	static String dbPassword = "1234567890";
>>>>>>> 08d9bc0ba38eb50b1ce4b8fa99709ebc676b5693
	static Connection conn = null;
	static PreparedStatement stmt;
	
	public static Connection getDatabaseConnection() {
				
		try {
			conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
	
	public static PreparedStatement getConnectedPreparedStatement(String sql) {
		
		try {
		    conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
			stmt = conn.prepareStatement(sql);

		}catch(Exception e) {
			System.out.println(e);
		}
		
		return stmt;
	}
	
	public static void deleteFromTable(String sql, BigDecimal id) {
		
		try {
			conn =  Database.getDatabaseConnection();
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    
		    stmt.setBigDecimal(1,id);
		    stmt.execute();
		    stmt.close();
		    conn.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeDatabaseConnection() {
		try {
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
