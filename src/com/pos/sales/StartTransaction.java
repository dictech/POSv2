package com.pos.sales;



import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import com.pos.account.model.SystemAccountDAO;


public class StartTransaction {
	
	public static void main(String args[]) {
		
		
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate txt = LocalDate.parse("23-12-1993", fmt);
		System.out.println(txt);
	}
}
