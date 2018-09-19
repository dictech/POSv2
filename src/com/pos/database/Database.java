package com.pos.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	static String dbUrl = "jdbc:mysql://127.0.0.1:3306/posv2";
	static String dbUsername = "Andy";
	static String dbPassword = "Andy";
	static Connection conn = null;
	
	public static Connection getDatabaseConnection() {
				
		try {
			conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
}
