package com.pos.payment.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.account.model.AttendantDAO;
import com.pos.database.Database;
import com.pos.order.model.OrderDAO;

public class paymentDAO {
	
	public static int executionStatus;
	public static boolean status;
	
	public static boolean createPayment(Payment payment) {
		
		String sql = "INSERT INTO payment VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
		    PreparedStatement stmt = Database.getConnectedPreparedStatement(sql);
		    
		    stmt.setBigDecimal(1, payment.getId());
		    stmt.setBigDecimal(2,payment.getRecipient().getId());
		    stmt.setBigDecimal(3,payment.getOrder().getId());
		    stmt.setInt(4, payment.getPrice());
		    stmt.setInt(5,payment.getAmtPaid());
		    stmt.setInt(6,payment.getBalance());
		    stmt.setString(7,payment.getType());
		    stmt.setDate(8,payment.getDate());
		    stmt.setString(9,payment.getTime());
		    
		    executionStatus = stmt.executeUpdate();
		    
		    if(executionStatus > 0){
		        status =  true;
		      }else{
		        status = false;
		        }
		    
		    Database.closeDatabaseConnection();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	
	
	public static List<Payment> getAllPayments() {
		 List<Payment> listOfPayments =  new ArrayList<Payment>();
		 Connection conn = Database.getDatabaseConnection();
		 
		 try {
			ResultSet records = conn.createStatement().executeQuery("select * from payment");
			while(records.next()) {
				Payment payment = new Payment();
				payment.setId(records.getBigDecimal(1));
				payment.setRecipient(AttendantDAO.getAttendant(records.getBigDecimal(2)));
				payment.setOrder(OrderDAO.getOrder(records.getBigDecimal(3)));
				payment.setPrice(records.getInt(4));
				payment.setAmtPaid(records.getInt(5));
				payment.setBalance(records.getInt(6));
				payment.setType(records.getString(7));
				payment.setDate(records.getDate(8));
				payment.setTime(records.getString(9));
				listOfPayments.add(payment);
			}
			
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		return listOfPayments;

	}
	
	public static Payment getPayment(BigDecimal id) {
		 Payment payment =  new Payment();
		 String sql = "select * from payment where id = ?";
		 
		 try {
			PreparedStatement stmt = Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, id);
			ResultSet record = stmt.executeQuery();
			
			if(record.next()) {
				payment.setId(record.getBigDecimal(1));
				payment.setRecipient(AttendantDAO.getAttendant(record.getBigDecimal(2)));
				payment.setOrder(OrderDAO.getOrder(record.getBigDecimal(3)));
				payment.setPrice(record.getInt(4));
				payment.setAmtPaid(record.getInt(5));
				payment.setBalance(record.getInt(6));
				payment.setType(record.getString(7));
				payment.setDate(record.getDate(8));
				payment.setTime(record.getString(9));
			}
			
           System.out.print("Amount Paid:" + payment.getAmtPaid());
           
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		return payment;
	}
	
	public static boolean updatePayment(Payment payment) {
		String sql = "UPDATE payment "
				   + "SET    pay_attdt_id = ?,"
				   + "       pay_order_id = ?,"
				   + "       pay_price    = ?,"
				   + "       pay_amt_price= ?,"
				   + "       pay_balance  = ?,"
				   + "       pay_type     = ?,"
				   + "       pay_date     = ?,"
				   + "       pay_time     = ? "
				   + "WHERE  pay_id       = ?";
		
		try {
			PreparedStatement stmt =  Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, payment.getRecipient().getId());
			stmt.setBigDecimal(2, payment.getOrder().getId());
			stmt.setInt(3, payment.getPrice());
			stmt.setInt(4, payment.getAmtPaid());
			stmt.setInt(5, payment.getBalance());
			stmt.setString(6, payment.getType());
			stmt.setDate(7, payment.getDate());
			stmt.setString(8, payment.getTime());
			stmt.setBigDecimal(9, payment.getId());
			executionStatus = stmt.executeUpdate();
			
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(executionStatus > 0) {
			status = true;
		}else {
			status = false;
		}
		
		return status;
	}
	
	public static void deletePayment(BigDecimal id) {
		String sql = "DELETE FROM payment where id = ?";
		Database.deleteFromTable(sql, id);
	}
}
