package com.pos.order.model;

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
			stmt.setBigDecimal(2, purchase.getProduct().getId());
			stmt.setInt(3, purchase.getQty());
			stmt.setInt(4, purchase.getTotalPriceOfPurchase());
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
				purchase.setProduct(ProductDAO.getProduct(records.getBigDecimal(2)));
				purchase.setQty(records.getInt(3));
				purchase.setTotalPriceOfPurchase(records.getInt(4));
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
				purchase.setProduct(ProductDAO.getProduct(records.getBigDecimal(2)));
				purchase.setQty(records.getInt(3));
				purchase.setTotalPriceOfPurchase(records.getInt(4));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return purchase;
	}
	
	
	public static void updatePurchase(Purchase purchase){
		String sql = "UPDATE purchases "
				   + "SET    pur_pro_id = ?,"
				   + "       pur_qty = ?,"
				   + "       pur_tot_price = ? "
				   + "WHERE  pur_id = ?";
		try {
			PreparedStatement stmt =  Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, purchase.getProduct().getId());
			stmt.setInt(2, purchase.getQty());
			stmt.setInt(3, purchase.getTotalPriceOfPurchase());
			stmt.setBigDecimal(4, purchase.getId());
			
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
