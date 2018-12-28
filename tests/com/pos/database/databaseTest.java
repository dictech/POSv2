package com.pos.database;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

public class databaseTest {


	@Test
	public void getDatabaseConnectionTest() {
		Connection conn = Database.getDatabaseConnection();
		assertNotNull(conn);
	} 

}
