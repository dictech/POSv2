package com.pos.sales.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;
import com.pos.inventory.model.ProductDAO;

public class purchaseDAO {
	public static void createPurchase(Purchase purchase) {
		String sql = "INSERT INTO purchases VALUES(?,?,?,?)";
		
		try {
			PreparedStatement stmt = Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, purchase.getId());
			stmt.setBigDecimal(2, purchase.getOrder().getId());
			stmt.setBigDecimal(3, purchase.getProduct().getId());
			stmt.execute();
			
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Purchase> getAllPurchases(){
		List<Purchase> listOfPurchases =  new ArrayList<Purchase>();
		String sql = "SELECT * FROM purchases";
		try {
			Connection cxtn =  Database.getDatabaseConnection();
			ResultSet records  = cxtn.createStatement().executeQuery(sql);
			
			while(records.next()) {
				Purchase purchase =  new Purchase();
				purchase.setId(records.getBigDecimal(1));
				purchase.setOrder(OrderDAO.getOrder(records.getBigDecimal(2)));
				purchase.setProduct(ProductDAO.getProduct(records.getBigDecimal(3)));
				listOfPurchases.add(purchase);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listOfPurchases;
	}
	
	
	public static Purchase getPurchase(BigDecimal id){
		Purchase purchase =  new Purchase();
		String sql = "SELECT * FROM purchases WHERE id = ?";
		try {
			PreparedStatement stmt =  Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, id);
			ResultSet records  = stmt.executeQuery();
			
			if(records.next()) {
				purchase.setId(records.getBigDecimal(1));
				purchase.setOrder(OrderDAO.getOrder(records.getBigDecimal(2)));
				purchase.setProduct(ProductDAO.getProduct(records.getBigDecimal(3)));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return purchase;
	}
	
	
	public static void getPurchase(Purchase purchase){
		String sql = "UPDATE purchases "
				   + "SET    pur_order_id = ?,"
				   + "       pur_pro_id = ? "
				   + "AND    pur_id = ?";
		try {
			PreparedStatement stmt =  Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, purchase.getProduct().getId());
			stmt.setBigDecimal(2, purchase.getOrder().getId());
			stmt.setBigDecimal(3, purchase.getId());
			
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deletePurchase(BigDecimal id) {
		String sql = "DELETE FROM purchases WHERE id = ?";
		Database.deleteFromTable(sql, id);
	}

}
