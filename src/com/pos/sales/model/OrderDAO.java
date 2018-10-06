package com.pos.sales.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class OrderDAO {
	public static void createOrder(Order order) {
		String sql =   "INSERT INTO order VALUES(?,?,?,?,?)";
		Connection cxtn = Database.getDatabaseConnection();
		
		try {
			
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			stmt.setBigDecimal(1, order.getId());
			stmt.setDate(2, order.getDate());
			stmt.setString(3,order.getTime());
			stmt.setString(4,order.getNo());
			stmt.setInt(5, order.getPrice());
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
				order.setId(records.getBigDecimal(1));
				order.setDate(records.getDate(2));
				order.setTime(records.getString(3));
				order.setNo(records.getString(4));
				order.setPrice(records.getInt(5));
				listOfOrders.add(order);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listOfOrders;
	}
	public static Order getOrder(BigDecimal id) {
		Order order =  new Order();
		String sql = "SELECT * "
				   + "FROM  order "
				   + "WHERE id = ?";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
			PreparedStatement stmt =  cxtn.prepareStatement(sql);
			stmt.setBigDecimal(1, id);
			ResultSet record = stmt.executeQuery();
			
			if(record.next()) {
				order.setId(record.getBigDecimal(1));
				order.setDate(record.getDate(2));
				order.setTime(record.getString(3));
				order.setNo(record.getString(4));
				order.setPrice(record.getInt(5));
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
				   + "WHERE  id = ?";
		
		try {
			
			Connection cxtn =  Database.getDatabaseConnection();
			PreparedStatement stmt =  cxtn.prepareStatement(sql);
			
			stmt.setDate(1, order.getDate());
			stmt.setString(2, order.getTime());
			stmt.setString(3,order.getNo());
			stmt.setInt(4, order.getPrice());
			stmt.setBigDecimal(5,order.getId());
		    stmt.executeUpdate();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	public static void deleteOrder(BigDecimal id){
		String sql = "DELETE FROM order where id = ?";
		Database.deleteFromTable(sql, id);
	}
	
}
