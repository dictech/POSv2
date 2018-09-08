package com.pos.sales;

import java.sql.Statement;

import com.pos.database.Database;;

public class StartTransaction {
	
	Statement stmt = null;
	
    StartTransaction(){
    	stmt = Database.getDatabase();
    }
    
	public static void main(String[] args) {
		
	}
}
