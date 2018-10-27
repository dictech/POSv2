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
		
		

       
        
    SystemAccountDAO.newAccount("willaims", "gray", "benson", "5 villa estate", "male", "09-02-1982",
    		 "danim@gmail.com", "008128787383", "Store Attendant", "willi", "willtana");

	}
}
