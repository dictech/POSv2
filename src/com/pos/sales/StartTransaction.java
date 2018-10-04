package com.pos.sales;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import com.pos.account.model.SystemAccountDAO;


public class StartTransaction {
	
	public static void main(String args[]) {
		      
     SystemAccountDAO.logInSystemAccount("#danny", "smallboy");
     // next up up i have to figure out a way to make an attendant sign in just ones everyday.
                   
		
	}
}
