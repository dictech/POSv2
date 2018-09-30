package com.pos.sales;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

import com.pos.account.model.Attendance;
import com.pos.account.model.AttendanceDAO;
import com.pos.account.model.Attendant;
import com.pos.account.model.SystemAccount;
import com.pos.account.model.SystemAccountDAO;
import com.pos.org.Branch;
import com.pos.org.BranchDAO;

public class StartTransaction {
	
	public static void main(String args[]) {
   
	   Attendance attendance = new Attendance();
	      Attendant attendant = new Attendant();
           
	        attendant.setfName("john");
	         attendant.setmName("jackson");
	          attendant.setSurname("crazy code");
	           attendant.setAddress("ikotun");
	            attendant.setDob(LocalDateTime.now());
	             attendant.setDoe(new Date());
	              attendant.setEmail("jakson87676@gmail.com");
	               attendant.setGender("male");
	                attendant.setPhoneNo("0906679733");
	                 Attendant.manageAttendant(attendant, action);
	              
	
	          
	     AttendanceDAO.viewAllAttendance(attendance);
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           
		
	}
}
