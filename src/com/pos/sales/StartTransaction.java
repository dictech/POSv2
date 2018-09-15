package com.pos.sales;

import java.util.Scanner;

import com.pos.account.Attendant;
import com.pos.org.Branch;

public class StartTransaction {
	public static void main(String args[]) {

		      Branch branch = new Branch();
		        Scanner input = new Scanner(System.in);
		          System.out.println("Create new branch. \nPlease fill in the account details below.".toUpperCase()); 
		  
		         System.out.println("Enter the Branch name.");
		            String brnName = input.nextLine();
		        
		            System.out.println(" Enter the address of your Branch");
		               String brnAddress = input.nextLine();
		           
		              System.out.println("Enter which state Your Branch is located.");
		                 String brnState = input.nextLine();
		              
		                 System.out.println("Enter the state local government Area of your Branch.");
		                    String brnLga = input.nextLine();
		         
		               branch.setBranchName(brnName);
		                branch.setBranchAddr(brnAddress);
		                 branch.setBranchState(brnState);
		                  branch.setBranchLga(brnLga);
		                   branch.createBranch();
		      
	}
}
