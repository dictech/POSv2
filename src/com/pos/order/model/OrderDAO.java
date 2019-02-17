package com.pos.order.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class OrderDAO {
	public static int createOrder(Order order) {
		String sql =   " INSERT INTO POSv2.order (order_id,order_date,order_time,order_no,order_price,order_attd_id) "
				+ "   VALUES (?,?,?,?,?,?) ";
				
		int pk =0;
		
		ResultSet rs = null;
		//Connection cxtn = Database.getDatabaseConnection();

		
		try {
		    PreparedStatement stmt = Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, order.getOrder_id());
			stmt.setBigDecimal(6, order.getOrder_attd_id());
			stmt.setDate(2, order.getOrder_date());
			stmt.setString(3,order.getOrder_time());
			stmt.setString(4,order.getOrder_no());
			stmt.setBigDecimal(5, order.getTotalPriceOfOrder());
			
			stmt.executeUpdate();
			
		    rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
		    rs.first();
		    pk = rs.getInt(1);
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pk;
	}
	public static List<Order> getAllOrders() {
		List<Order> listOfOrders =  new ArrayList<Order>();
		String sql = "SELECT * FROM order";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
			ResultSet records =  cxtn.createStatement().executeQuery(sql);
			
			while(records.next()) {
				Order order =  new Order();
				order.setOrder_id(records.getBigDecimal(1));
				order.setOrder_attd_id(records.getBigDecimal(2));
				order.setOrder_date(records.getDate(3));
				order.setOrder_time(records.getString(4));
				order.setOrder_no(records.getString(5));;
				order.setTotalPriceOfOrder(records.getBigDecimal(6));
				listOfOrders.add(order);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listOfOrders;
	}
	
	public static Order getOrder(BigDecimal id) {
		Order order =  new Order();
		String sql = " SELECT * FROM posv2.order "
				   + " WHERE order_id = ?";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
			PreparedStatement stmt =  cxtn.prepareStatement(sql);
			stmt.setBigDecimal(1, id);
			ResultSet record = stmt.executeQuery();
			
			if(record.next()) {
				order.setOrder_id(record.getBigDecimal(1));
				order.setOrder_attd_id(record.getBigDecimal(2));
				order.setOrder_date(record.getDate(3));
				order.setOrder_time(record.getString(4));
				order.setOrder_no(record.getString(5));
				order.setTotalPriceOfOrder(record.getBigDecimal(6));

			}else {
				System.out.println("No data is associated with this ID!");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return order;
	}
	public static void updateOrder(Order order) {
		String sql = "UPDATE order "
				   + "SET    order_attdt_id= ? "
				   + "       order_date = ?,"
				   + "       order_time = ?,"
				   + "       order_no = ?,"
				   + "       order_total_cost= ? "
				   + "WHERE  id = ?";
		
		try {
			
			Connection cxtn =  Database.getDatabaseConnection();
			PreparedStatement stmt =  cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, order.getOrder_id());
			stmt.setBigDecimal(2, order.getOrder_attd_id());
			stmt.setDate(3, order.getOrder_date());
			stmt.setString(4,order.getOrder_time());
			stmt.setString(5,order.getOrder_no());
			stmt.setBigDecimal(6, order.getTotalPriceOfOrder());
		    stmt.executeUpdate();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	public static void deleteOrder(BigDecimal id){
		String sql = "DELETE FROM order where id = ?";
		Database.deleteFromTable(sql, id);
	}
	
	
	public static ArrayList<BigDecimal> getAllOrderIds()throws Exception {
		
		ArrayList<BigDecimal> OrderIds = new ArrayList<BigDecimal>();
		 String sql = " SELECT order_id FROM posv2.order; ";
		 
		Connection cxtn =  Database.getDatabaseConnection();
		ResultSet records =  cxtn.createStatement().executeQuery(sql);
		
		while(records.next()) {
			int ids = records.getInt(1); 
			OrderIds.add(new BigDecimal(ids));
		}
		return OrderIds;	
	}
	
	
}
