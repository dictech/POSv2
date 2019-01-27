package com.pos.inventory.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.pos.database.Database;
import com.pos.order.model.Purchase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;


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



	public static List<ProductInventory> getInventoryByProduct(String productName) {
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
		
		return listOfInventories;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static HashMap<String,TableColumn<ProductInventory, ?>> getProductInventoryCol() {
				  
		   HashMap<String,TableColumn<ProductInventory, ?>> mapOfTableColumns = new HashMap<String,TableColumn<ProductInventory, ?>>();
		
		   TableColumn<ProductInventory, String> inventoryitemCol      =   new  TableColumn<ProductInventory, String>();
		   TableColumn<ProductInventory, String> productDescCol        =   new  TableColumn<ProductInventory, String>();
		   TableColumn<ProductInventory, BigDecimal> noOfUnitsCol      =   new  TableColumn<ProductInventory, BigDecimal>();
		   TableColumn<ProductInventory, BigDecimal> qtyPerUnitCol     =   new  TableColumn<ProductInventory, BigDecimal>();
		   TableColumn<ProductInventory, BigDecimal> totalNoOfItemsCol =   new  TableColumn<ProductInventory, BigDecimal>();
		   TableColumn<ProductInventory, BigDecimal> totalOrderedCol   =   new  TableColumn<ProductInventory, BigDecimal>();
		   TableColumn<ProductInventory, BigDecimal> reOrderLvlCol     =   new  TableColumn<ProductInventory, BigDecimal>();
		   TableColumn<ProductInventory, CheckBox> selectedItem        =   new  TableColumn<ProductInventory, CheckBox>(); 
		   @SuppressWarnings("rawtypes")
		   TableColumn qtyCol =  new TableColumn("Quantity");
		   
		   inventoryitemCol.setText("Item");
		   productDescCol.setText("Description");
		   noOfUnitsCol.setText("Units");
		   qtyPerUnitCol.setText("Qty");
		   totalNoOfItemsCol.setText("Total No.");
		   totalOrderedCol.setText("Total Sold");
		   reOrderLvlCol.setText("Re-Order");
		   selectedItem.setText("zSelected");
		   
		   inventoryitemCol.setPrefWidth(150);
		   productDescCol.setPrefWidth(250);
		   noOfUnitsCol.setPrefWidth(80);
		   qtyPerUnitCol.setPrefWidth(80);
		   totalNoOfItemsCol.setPrefWidth(80);
		   totalOrderedCol.setPrefWidth(80);
		   reOrderLvlCol.setPrefWidth(80);

	    
	    	inventoryitemCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, String>("proName"));
	    	productDescCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, String>("proDesc"));
	    	noOfUnitsCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("noOfUnits"));
	    	qtyPerUnitCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("qtyPerUnit"));
	    	totalNoOfItemsCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("totalQty"));
	    	totalOrderedCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("noOfOrdered"));
	    	reOrderLvlCol.setCellValueFactory(new PropertyValueFactory<ProductInventory, BigDecimal>("reorderLvl"));
	    	selectedItem.setCellValueFactory(new PropertyValueFactory<ProductInventory,CheckBox>("selectedItem"));
	    	qtyCol.setCellValueFactory(new PropertyValueFactory<Purchase,TextField>("qty"));
			
			mapOfTableColumns.put("inventoryitemCol",inventoryitemCol);
			mapOfTableColumns.put("productDescCol",productDescCol);
			mapOfTableColumns.put("noOfUnitsCol",noOfUnitsCol);
			mapOfTableColumns.put("qtyPerUnitCol",qtyPerUnitCol);
			mapOfTableColumns.put("totalNoOfItemsCol",totalNoOfItemsCol);
			mapOfTableColumns.put("totalOrderedCol",totalOrderedCol);
			mapOfTableColumns.put("reOrderLvlCol",reOrderLvlCol);
			mapOfTableColumns.put("zselectedItem",selectedItem);
			mapOfTableColumns.put("qtyCol",qtyCol);
			
			
	    		    	
	    	return mapOfTableColumns;

	    }
	
	
	
	
}
