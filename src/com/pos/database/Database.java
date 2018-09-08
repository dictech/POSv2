package com.pos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	
	static String db = "jdbc:mysql://localhost:3306/posv2";
	static String dbDriver = "com.mysql.jdbc.Driver";
	static String dbUsername = "root";
	static String dbPassword = "Manchester81#";
	
	public static Statement getDatabase() {
		Statement stmt = null;
		
		try {
			Class.forName(dbDriver);
			Connection conn = DriverManager.getConnection(db,dbUsername,dbPassword);
			stmt = conn.createStatement();
		}catch(Exception e) {
			System.out.println(e);
		}
		return stmt;
	}
	public static ResultSet askDatabase(String question) {
		
		ResultSet result = null;
		try {
			Statement stmt = getDatabase();
			result = stmt.executeQuery(question);		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return result;

	}
}
