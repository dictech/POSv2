package com.pos.sales.model;



import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import com.pos.account.model.SystemAccountDAO;
import com.pos.org.model.Branch;
import com.pos.org.model.BranchDAO;

public class StartTransaction {
	
	public static void main(String args[]) {
<<<<<<< HEAD:src/com/pos/sales/StartTransaction.java
		
		
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate txt = LocalDate.parse("23-12-1993", fmt);
		System.out.println(txt);
=======


>>>>>>> d892b6fdbda990f8e61943a877b4de0a1ced94a9:src/com/pos/sales/model/StartTransaction.java
	}
}
