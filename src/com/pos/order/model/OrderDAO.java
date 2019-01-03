package com.pos.order.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class OrderDAO {
	public static void createOrder(Order order) {
		String sql =   "INSERT INTO order VALUES(?,?,?,?,?,?)";
		Connection cxtn = Database.getDatabaseConnection();
		
		try {
			
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			stmt.setBigDecimal(1, order.getOrder_id());
			stmt.setDate(2, order.getOrder_date());
			stmt.setString(3,order.getOrder_time());
			stmt.setString(4,order.getOrder_no());
			stmt.setBigDecimal(5, order.getTotalPriceOfOrder());
			stmt.setBigDecimal(6, order.getOrder_attd_id());
			stmt.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static List<Order> getAllOrders() {
		List<Order> listOfOrders =  new ArrayList<Order>();
		String sql = "SELECT * FROM order";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
			ResultSet records =  cxtn.createStatement().executeQuery(sql);
			
			while(records.next()) {
				Order order =  new Order();
				order.setOrder_attd_id(records.getBigDecimal(1));
				order.setOrder_date(records.getDate(2));
				order.setOrder_time(records.getString(3));
				order.setOrder_no(records.getString(4));;
				order.setTotalPriceOfOrder(records.getBigDecimal(5));
				order.setOrder_attd_id(records.getBigDecimal(6));
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
				order.setOrder_date(record.getDate(2));
				order.setOrder_time(record.getString(3));
				order.setOrder_no(record.getString(4));
				order.setTotalPriceOfOrder(record.getBigDecimal(5));
				order.setOrder_attd_id(record.getBigDecimal(6));
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
				   + "SET    order_date = ?,"
				   + "       order_time = ?,"
				   + "       order_no = ?,"
				   + "       order_total_cost= ? "
				   + "       order_attdt_id= ? "
				   + "WHERE  id = ?";
		
		try {
			
			Connection cxtn =  Database.getDatabaseConnection();
			PreparedStatement stmt =  cxtn.prepareStatement(sql);
			
			stmt.setDate(1, order.getOrder_date());
			stmt.setString(2, order.getOrder_time());
			stmt.setString(3,order.getOrder_no());
			stmt.setBigDecimal(4, order.getTotalPriceOfOrder());
			stmt.setBigDecimal(5,order.getOrder_attd_id());
			stmt.setBigDecimal(5,order.getOrder_id());
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
