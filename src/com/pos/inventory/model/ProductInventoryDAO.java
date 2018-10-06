package com.pos.inventory.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class ProductInventoryDAO {
	
	public static void createInventory(ProductInventory inventory) {
		String sql = "INSERT INTO inventory VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, inventory.getId());
			stmt.setInt(2, inventory.getProId());
			stmt.setString(3, inventory.getProDesc());
			stmt.setInt(4, inventory.getNoOfUnits());
			stmt.setInt(5, inventory.getQtyPerUnit());
			stmt.setInt(6, inventory.getTotalQty());
			stmt.setInt(7, inventory.getNoOfOrdered());
			stmt.setInt(8, inventory.getReorderLvl());
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
				   + "       inv_reorder_lvl = ? "
				   + "WHERE  inv_id = ?";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, inventory.getId());
			stmt.setInt(2, inventory.getProId());
			stmt.setString(3, inventory.getProDesc());
			stmt.setInt(4, inventory.getNoOfUnits());
			stmt.setInt(5, inventory.getQtyPerUnit());
			stmt.setInt(6, inventory.getTotalQty());
			stmt.setInt(7, inventory.getNoOfOrdered());
			stmt.setInt(8, inventory.getReorderLvl());
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
				inventory.setProId(rows.getInt(2));
				inventory.setProDesc(rows.getString(3));
				inventory.setNoOfUnits(rows.getInt(4));
				inventory.setQtyPerUnit(rows.getInt(5));
				inventory.setTotalQty(rows.getInt(6));
				inventory.setNoOfOrdered(rows.getInt(7));
				inventory.setReorderLvl(rows.getInt(8));
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
				inventory.setProId(row.getInt(2));
				inventory.setProDesc(row.getString(3));
				inventory.setNoOfUnits(row.getInt(4));
				inventory.setQtyPerUnit(row.getInt(5));
				inventory.setTotalQty(row.getInt(6));
				inventory.setNoOfOrdered(row.getInt(7));
				inventory.setReorderLvl(row.getInt(8));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return inventory;
	}
   
	public static void deleteInventory(BigDecimal id) {
		String sql = "DELETE FROM inventory where inv_id = ?";
		Database.deleteFromTable(sql, id);
	}

}
