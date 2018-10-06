package com.pos.sales;



import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.text.DateFormatter;

import com.pos.account.model.SystemAccountDAO;
import com.pos.org.model.Branch;
import com.pos.org.model.BranchDAO;
import com.pos.org.Branch;

public class StartTransaction {
	
	public static void main(String args[]) {

   
	   Attendance attendance = new Attendance();
	      Attendant attendant = new Attendant();
           
	        attendant.setfName("john");
	         attendant.setmName("jackson");
	          attendant.setSurname("crazy code");
	           attendant.setAddress("ikotun");
	            attendant.setDob(new Date());
	             attendant.setDoe(new Date());
	              attendant.setEmail("jakson87676@gmail.com");
	               attendant.setGender("male");
	                attendant.setPhoneNo("0906679733");
	                 Attendant.manageAttendant(attendant, action);
	              
	
	          
	     AttendanceDAO.viewAllAttendance(attendance);
	           
	   
		      
       ArrayList<int> contacts = new ArrayList<int>();
       
		
	}
}
