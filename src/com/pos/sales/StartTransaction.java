package com.pos.sales;

import java.util.Scanner;

import com.pos.account.Attendant;
import com.pos.org.Branch;
import com.pos.org.BranchDAO;

public class StartTransaction {
	
	public static void main(String args[]) {

		      Branch branch = new Branch();
		        Scanner input,chosedNumber,newName; 
		        input = new Scanner(System.in);
		        newName = new Scanner(System.in);
		         chosedNumber = new Scanner(System.in);
		    /*            
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
		               */       
		                  
		        System.out.println(" \t welcome administrator! \n please enter a branch id to make changes .");
		      
		             int brnID = input.nextInt();
		             branch.setBranchID(brnID);
		              System.out.println("what detail do you want to change ?");
		               System.out.println("1.Branch Name. \n 2.Branch Addrress.\n 3.Branch State.\n 4.Branch local government.");   
		                String Name;
		                  String Addrress;
		                   String State;
		                    String LGA;
		                     int Number = chosedNumber.nextInt();
		                   
		                   if(Number == 1) {
		                  
		                System.out.println("Please Enter the new Branch Name.");
		            	  Name = newName.nextLine();
		            	    BranchDAO.getBranch(branch);
		            	    branch.setBranchName(Name);
		            	     BranchDAO.updateBranch(branch);
		            	      System.out.println("the branch Name for ID "+brnID+" has been changed to "
		            	       +Name.toUpperCase());
		            	       input.close();
		            	       newName.close();
		            	       chosedNumber.close();
		            	       
		            	       
		               }else if(Number == 2)
		                 {
		            	   
		            	      System.out.println(" please Enter the new Branch Addrress.");
		            	       Addrress = newName.nextLine();
		            	       branch.setBranchAddr(Addrress);
		            	        BranchDAO.updateBranch(branch);
		            	        System.out.println("the branch Addrress for ID "+brnID+" has been changed to "
		 		            	       +Addrress.toUpperCase());
		 		            	       input.close();
		 		            	       newName.close();
		 		            	       chosedNumber.close();}
		                   
		               else if(Number == 3)
		               {
		            	     System.out.println("please Enter a new and current state of this Branch.");
		            	     State = newName.nextLine();
		            	     branch.setBranchState(State);
		            	     BranchDAO.updateBranch(branch);
		            	     System.out.println("the branch State for ID "+brnID+" has been changed to "
				            	       +State.toUpperCase());
				            	       input.close();
				            	       newName.close();
				            	       chosedNumber.close();}
		                   
		               else if(Number == 4)
		               {
		            	    System.out.println("please Enter a new local government Area for this Branch.");
		            	    LGA = newName.nextLine();
		            	    branch.setBranchLga(LGA);
		            	    BranchDAO.updateBranch(branch);
		            	    System.out.println("the local government Area for Branch ID "+brnID+" has been changed to "
				            	       +LGA.toUpperCase());
				            	       input.close();
				            	       newName.close();
				            	       chosedNumber.close();}
		                   
		               else {
		            	   
		            	   System.out.println(" sorry no match was found !".toUpperCase());
		            	   
		               }
		              
		                   
	}
}
