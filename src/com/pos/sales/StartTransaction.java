package com.pos.sales;



import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.text.DateFormatter;

import com.pos.account.model.SystemAccountDAO;


public class StartTransaction {
	
	public static void main(String args[]) {
		      
     Date date1 = new Date();
	 SimpleDateFormat date = new SimpleDateFormat("E dd/MM/yyyy ");
	 String l = date.format(date1);
	  System.out.println(l);
	     
		
	}
}
