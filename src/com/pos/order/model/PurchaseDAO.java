package com.pos.order.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.pos.database.Database;
import com.pos.inventory.model.Product;
import com.pos.inventory.model.ProductDAO;
import com.pos.inventory.model.ProductInventory;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class PurchaseDAO {
		
	public static void createPurchase(Purchase purchase) {
		String sql = "INSERT INTO purchases VALUES(?,?,?,?)";
		
		try {
			PreparedStatement stmt = Database.getConnectedPreparedStatement(sql);
			stmt.setBigDecimal(1, purchase.getId());
			stmt.setBigDecimal(2, purchase.getProduct().getId());
			stmt.setBigDecimal(3, purchase.getQty());
			stmt.setBigDecimal(4, purchase.getTotalPriceOfPurchase());
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
				purchase.setQty(records.getBigDecimal(3));
				purchase.setTotalPriceOfPurchase(records.getBigDecimal(4));
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
				purchase.setQty(records.getBigDecimal(3));
				purchase.setTotalPriceOfPurchase(records.getBigDecimal(4));
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
			stmt.setBigDecimal(2, purchase.getQty());
			stmt.setBigDecimal(3, purchase.getTotalPriceOfPurchase());
			stmt.setBigDecimal(4, purchase.getId());
			
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deletePurchase(BigDecimal id) {

	
	}

	
	
	public static HashMap<String,TableColumn<Purchase, ?>> getPurchaseCols() {
		  
		   HashMap<String,TableColumn<Purchase, ?>> mapOfPurchaseTableCols = new HashMap<String,TableColumn<Purchase, ?>>();
		
		   TableColumn<Purchase, Product>    productCol  =   new  TableColumn<Purchase, Product>();
		   TableColumn<Purchase, BigDecimal> qtyPurchasedCol   =   new  TableColumn<Purchase, BigDecimal>();
		   TableColumn<Purchase, BigDecimal> totalPriceCol     =   new  TableColumn<Purchase, BigDecimal>();
		   TableColumn<Purchase, CheckBox> selectedProductCol     =   new  TableColumn<Purchase, CheckBox>();
		   
		   productCol.setText("Item");
		   qtyPurchasedCol.setText("Qty");
		   totalPriceCol.setText("Total");
		   selectedProductCol.setText("");
		   
		   productCol.setPrefWidth(150);
		   qtyPurchasedCol.setPrefWidth(80);
		   totalPriceCol.setPrefWidth(80);
		   

	    
		   productCol.setCellValueFactory(new PropertyValueFactory<Purchase, Product>("product"));
		   qtyPurchasedCol.setCellValueFactory(new PropertyValueFactory<Purchase, BigDecimal>("qty"));
		   totalPriceCol.setCellValueFactory(new PropertyValueFactory<Purchase, BigDecimal>("totalPriceOfPurchase"));
		   selectedProductCol.setCellValueFactory(new PropertyValueFactory<Purchase,CheckBox>("selectedItem"));

			
		   mapOfPurchaseTableCols.put("productCol",productCol);
		   mapOfPurchaseTableCols.put("qtyPurchasedCol",qtyPurchasedCol);
		   mapOfPurchaseTableCols.put("totalPriceCol",totalPriceCol);
		   mapOfPurchaseTableCols.put("selectedProductCol",selectedProductCol);
	
	    	return mapOfPurchaseTableCols;

	    }
	
	
  }
