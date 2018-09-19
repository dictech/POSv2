package com.pos.sales;

import java.util.Date;
import java.util.Scanner;

import com.pos.account.Attendance;
import com.pos.account.AttendanceDAO;
import com.pos.account.Attendant;
import com.pos.org.Branch;
import com.pos.org.BranchDAO;

public class StartTransaction {
	
	public static void main(String args[]) {
   
		Attendance john = new Attendance();
		Attendant info = new Attendant();
	
		     System.out.println("PLease wait..... loging you in");
		      System.out.println("verification successfull !");
		         john.setAttenc_attendt_Id(1);
		         john.setAttendcFirstName("Rose");
		          john.setAttendcLastName("Essien");
		           john.setHasSignedIn(true);
		            john.setLastSeen(john.getLastSeen());
		              AttendanceDAO.createAttendance(john);
		               info.getGender();
		                if(info.getGender() == "F") {
		              
		                	System.out.print("Welcome MRS "+john.getAttendcLastName().toUpperCase());
		            		System.out.print("you have been added to the Attendance list ! Date is : "+ john.getLastSeen());
		            		
		                	
		                }else {
		                	

		                	System.out.println("Welcome MR "+john.getAttendcLastName().toUpperCase());
		            		System.out.print("you have been added to the Attendance list ! Date is : "+ john.getLastSeen());
		            		
		                }
		                
		                
		
		  
		
		
	}
}
