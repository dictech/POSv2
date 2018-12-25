package com.pos.inventory.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductInventoryDAO {
	
	public static void createInventory(ProductInventory inventory) {
		String sql = "INSERT INTO inventory VALUES(?,?,?,?,?,?,?,?,?)";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, inventory.getId());
			stmt.setBigDecimal(2, inventory.getProduct().getId());
			stmt.setString(3, inventory.getProduct().getName());
			stmt.setBigDecimal(4, inventory.getNoOfUnits());
			stmt.setBigDecimal(5, inventory.getQtyPerUnit());
			stmt.setBigDecimal(6, inventory.getTotalQty());
			stmt.setBigDecimal(7, inventory.getNoOfOrdered());
			stmt.setBigDecimal(8, inventory.getReorderLvl());
			stmt.setString(9, inventory.getProduct().getName());
			stmt.execute();
			stmt.close();
			cxtn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void updateInventory(ProductInventory inventory) {
		
		String sql = "UPDATE inventory "
				   + "SET    inv_pro_id = ?,"
				   + "       inv_desc   = ?,"
				   + "       inv_no_of_units = ?,"
				   + "       inv_qty_per_unit= ?,"
				   + "       inv_total_qty = ?,"
				   + "       inv_total_ordered = ?,"
				   + "       inv_reorder_lvl = ? ,"
				   + "       inv_pro_name = ?"
				   + "WHERE  inv_id = ?";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, inventory.getProduct().getId());
			stmt.setString(2, inventory.getProDesc());
			stmt.setBigDecimal(3, inventory.getNoOfUnits());
			stmt.setBigDecimal(4, inventory.getQtyPerUnit());
			stmt.setBigDecimal(5, inventory.getTotalQty());
			stmt.setBigDecimal(6, inventory.getNoOfOrdered());
			stmt.setBigDecimal(7, inventory.getReorderLvl());
			stmt.setBigDecimal(8, inventory.getId());
			stmt.setString(9, inventory.getProduct().getName());
			
			stmt.executeUpdate();
			stmt.close();
			cxtn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<ProductInventory> getAllInventories() {
		List<ProductInventory> listOfInventories =  new ArrayList<ProductInventory>();
		String sql = "SELECT * FROM inventory";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			ResultSet rows = cxtn.createStatement().executeQuery(sql);
			
			while(rows.next()) {
				ProductInventory inventory =  new ProductInventory();
				inventory.setId(rows.getBigDecimal(1));
				inventory.setProduct(ProductDAO.getProduct(rows.getBigDecimal(2)));
				inventory.setProDesc(rows.getString(3));
				inventory.setNoOfUnits(rows.getBigDecimal(4));
				inventory.setQtyPerUnit(rows.getBigDecimal(5));
				inventory.setTotalQty(rows.getBigDecimal(6));
				inventory.setNoOfOrdered(rows.getBigDecimal(7));
				inventory.setReorderLvl(rows.getBigDecimal(8));
				inventory.setProName(ProductDAO.getProduct(rows.getBigDecimal(2)).getName());
				listOfInventories.add(inventory);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listOfInventories;
	}
	
	
	
    public static ProductInventory getInventory(BigDecimal id){
		ProductInventory inventory =  new ProductInventory();
		String sql = "SELECT * FROM inventory where inv_id = ? ";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt =  cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, id);
			ResultSet row = stmt.executeQuery();
			
			if(row.next()) {
				inventory.setId(row.getBigDecimal(1));
				inventory.setProduct(ProductDAO.getProduct(row.getBigDecimal(2)));
				inventory.setProDesc(row.getString(3));
				inventory.setNoOfUnits(row.getBigDecimal(4));
				inventory.setQtyPerUnit(row.getBigDecimal(5));
				inventory.setTotalQty(row.getBigDecimal(6));
				inventory.setNoOfOrdered(row.getBigDecimal(7));
				inventory.setReorderLvl(row.getBigDecimal(8));
				inventory.setProName(ProductDAO.getProduct(row.getBigDecimal(2)).getName());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return inventory;
	}
   
	public static void deleteInventory(ProductInventory inventory) {
		String sql = "DELETE FROM inventory where inv_id = ?";
		Database.deleteFromTable(sql, inventory.getId());
	}



	public static ObservableList<ProductInventory> getInventoryByProduct(String productName) {
		String sql = "SELECT * FROM inventory WHERE inv_desc like ?";
		List<ProductInventory> listOfInventories = new ArrayList<ProductInventory>();
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt =  cxtn.prepareStatement(sql);
			
			stmt.setString(1, "%" + productName + "%");
			ResultSet row = stmt.executeQuery();
			
			while(row.next()) {
				ProductInventory inventory =  new ProductInventory();
				inventory.setId(row.getBigDecimal(1));
				inventory.setProduct(ProductDAO.getProduct(row.getBigDecimal(2)));
				inventory.setProName(ProductDAO.getProduct(row.getBigDecimal(2)).getName());
				inventory.setProDesc(row.getString(3));
				inventory.setNoOfUnits(row.getBigDecimal(4));
				inventory.setQtyPerUnit(row.getBigDecimal(5));
				inventory.setTotalQty(row.getBigDecimal(6));
				inventory.setNoOfOrdered(row.getBigDecimal(7));
				inventory.setReorderLvl(row.getBigDecimal(8));
				listOfInventories.add(inventory);
			}
			
			listOfInventories.forEach(i ->{
				System.out.println(i.getProduct());
				System.out.println(i.getProDesc());				
				System.out.println(i.getProName());				
			});
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return FXCollections.observableArrayList(listOfInventories);
	}

}
