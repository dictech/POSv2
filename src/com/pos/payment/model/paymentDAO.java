package com.pos.payment.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pos.account.model.AttendantDAO;
import com.pos.database.Database;
import com.pos.order.model.OrderDAO;

public class paymentDAO {
	
	public static int executionStatus;
	public static boolean status;
	
	public static boolean createPayment(Payment payment) {
		
		String sql = "INSERT INTO payment VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Connection cnt = Database.getDatabaseConnection();
		    PreparedStatement stmt = cnt.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		    
		    stmt.setBigDecimal(1, payment.getId());
		    stmt.setBigDecimal(2,payment.getRecipient().getId());
		    stmt.setBigDecimal(3,payment.getOrder().getOrder_id());
		    stmt.setInt(4, payment.getPrice());
		    stmt.setInt(5,payment.getAmtPaid());
		    stmt.setInt(6,payment.getBalance());
		    stmt.setString(7,payment.getType());
		    stmt.setString(8, payment.getDescription());
		    stmt.setDate(9,payment.getDate());
		    stmt.setString(10,payment.getTime());
		    stmt.executeUpdate();
		    ResultSet record = stmt.getGeneratedKeys();
		    if(record.next()) {
		    	stmt.setBigDecimal(1, record.getBigDecimal(1));
		    	payment.setId( record.getBigDecimal(1));
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
				payment.setDescription(records.getString(8));
				payment.setDate(records.getDate(9));
				payment.setTime(records.getString(10));
				listOfPayments.add(payment);
			}
			
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		return listOfPayments;

	}
	
	public static Payment getPayment(BigDecimal id) {
		 Payment payment =  new Payment();
		 String sql = " select * from posv2.payment"
		 		    + "  where pay_id = ?";
		 
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
				payment.setDescription(record.getString(8));
				payment.setDate(record.getDate(9));
				payment.setTime(record.getString(10));
			}
			
           
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		return payment;
	}
	
	public static boolean updatePayment(Payment payment) {
		String sql = "UPDATE payment "
				   + "SET    pay_attdt_id = ?,  "
				   + "       pay_order_id = ?,  "
				   + "       pay_price    = ?,  "
				   + "       pay_amt_paid= ?,  "
				   + "       pay_balance  = ?,  "
				   + "       pay_type     = ?,  "
				   + "       pay_description =?,"
				   + "       pay_date     = ?,  "
				   + "       pay_time     = ?   "
				   + "WHERE  pay_id       = ?   ";
		
		try {
			PreparedStatement stmt =  Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, payment.getRecipient().getId());
			stmt.setBigDecimal(2, payment.getOrder().getOrder_id());
			stmt.setInt(3, payment.getPrice());
			stmt.setInt(4, payment.getAmtPaid());
			stmt.setInt(5, payment.getBalance());
			stmt.setString(6, payment.getType());
			stmt.setString(7, payment.getDescription());
			stmt.setDate(8, payment.getDate());
			stmt.setString(9, payment.getTime());
			stmt.setBigDecimal(10, payment.getOrder().getOrder_id());
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
	
	public static ArrayList<BigDecimal> getAllpayOrderIds()throws Exception {
		
		ArrayList<BigDecimal> payOrderIds = new ArrayList<BigDecimal>();
		 String sql = " SELECT pay_order_id FROM posv2.payment ;";
		 
		Connection cxtn =  Database.getDatabaseConnection();
		ResultSet records =  cxtn.createStatement().executeQuery(sql);
		
		while(records.next()) {
			 
			payOrderIds.add(records.getBigDecimal(1));
		}
		return payOrderIds;	
	}
	
	
	public static Payment getMaxPayment(BigDecimal pay_orderID) throws Exception{
		
		Payment payment = new Payment();
	
		 String sql = " select max(pay_id)pay_id,pay_attdt_id, "
		 		+ " pay_order_id, pay_price, pay_amt_paid, pay_balance, "
		 		+ " pay_type, pay_description, pay_date, pay_time " + 
		 		" from   posv2.payment " + 
		 		" where  pay_order_id = ? ";
		 
		  PreparedStatement stmt = Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, pay_orderID);
			 ResultSet record = stmt.executeQuery();
			 if(record.next()) {	
				 
					payment.setId(record.getBigDecimal(1));
					payment.setRecipient(AttendantDAO.getAttendant(record.getBigDecimal(2)));
					payment.setOrder(OrderDAO.getOrder(record.getBigDecimal(3)));
					payment.setPrice(record.getInt(4));
					payment.setAmtPaid(record.getInt(5));
					payment.setBalance(record.getInt(6));
					payment.setType(record.getString(7));
					payment.setDescription(record.getString(8));
					payment.setDate(record.getDate(9));
					payment.setTime(record.getString(10));
			}
			return payment;
			
			}
	            
	
			
}
