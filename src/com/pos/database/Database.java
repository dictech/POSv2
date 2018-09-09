package com.pos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
