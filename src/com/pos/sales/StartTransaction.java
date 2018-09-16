package com.pos.sales;

import java.util.Scanner;

import com.pos.account.Attendant;
import com.pos.org.Branch;
import com.pos.org.BranchDAO;

public class StartTransaction {
	
	public static void main(String args[]) {
     Branch branch = new Branch();
       Scanner i = new Scanner(System.in);
        System.out.println("please enter an id for the branch records you want to delete ");
         int bId = i.nextInt();
          BranchDAO.deleteBranch(bId);
	}
}
