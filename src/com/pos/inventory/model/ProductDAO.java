package com.pos.inventory.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pos.database.Database;
import com.pos.order.model.Purchase;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductDAO {
	public static void createProduct(Product product) {
		String sql = "INSERT INTO product "
				   + "values(?,?,?,?,?,?)";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    PreparedStatement stmt = cxtn.prepareStatement(sql);
		    
		    stmt.setBigDecimal(1, product.getId());
		    stmt.setString(2,product.getName());
		    stmt.setBigDecimal(3,product.getCategory().getId());
		    stmt.setString(4,product.getDesc());
		    stmt.setBigDecimal(5,product.getCost());
		    stmt.setBigDecimal(6,product.getPrice());
		    stmt.execute();
		    stmt.close();
		    cxtn.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
	 }
	
	
	public static List<Product> getAllProducts() {
	    List<Product> listOfProducts = new ArrayList<Product>();
 		String sql = "SELECT * FROM product ";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    ResultSet rows  = cxtn.createStatement().executeQuery(sql);
		    
		    while(rows.next()) {
		    	Product product = new Product();
		    	product.setId(rows.getBigDecimal(1));
		    	product.setName(rows.getString(2));
		    	product.setCategory(ProductCategoryDAO.getCategory(rows.getBigDecimal(3)));
		    	product.setDesc(rows.getString(4));
		    	product.setCost(rows.getBigDecimal(5));
		    	product.setPrice(rows.getBigDecimal(6));

		    	listOfProducts.add(product);
		    }
		    
		    listOfProducts.forEach((product)->{
		    	System.out.println(product.getId());
		    	System.out.println(product.getName());
		    	System.out.println(product.getCategory());
		    	System.out.println(product.getDesc());
		    	System.out.println(product.getCost());
		    	System.out.println(product.getPrice());

		    });
		      
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listOfProducts;
	};
	
	
	
	public static Product getProduct(BigDecimal id) {
		
		    Product product = new Product();
	 		String sql = "SELECT * FROM product WHERE pro_id = ? ";
			
			try {
				Connection cxtn =  Database.getDatabaseConnection();
			    PreparedStatement stmt = cxtn.prepareStatement(sql);
			    
			    stmt.setBigDecimal(1, id);
			    ResultSet row  = stmt.executeQuery();
			    
			    if(row.next()) {
			    	product.setId(row.getBigDecimal(1));
			    	product.setName(row.getString(2));
			    	product.setCategory(ProductCategoryDAO.getCategory(row.getBigDecimal(3)));
			    	product.setDesc(row.getString(4));
			    	product.setCost(row.getBigDecimal(5));
			    	product.setPrice(row.getBigDecimal(6));
			    }
			    
			    	System.out.println(product.getId());
			    	System.out.println(product.getName());
			    	System.out.println(product.getCategory());
			    	System.out.println(product.getDesc());
			    	System.out.println(product.getCost());
			    	System.out.println(product.getPrice());

			      
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return product;
		};
			
	
	public static void updateProduct(Product product) {
		String sql = "UPDATE product "
				   + "SET    pro_name = ?,"
				   + "       pro_cat_id = ?,"
				   + "       pro_desc = ?,"
				   + "       pro_cost = ?,"
				   + "       pro_price = ? "
				   + "WHERE  pro_id = ? ";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    PreparedStatement stmt = cxtn.prepareStatement(sql);
		    
		    stmt.setString(1,product.getName());
		    stmt.setBigDecimal(2,product.getCategory().getId());
		    stmt.setString(3,product.getDesc());
		    stmt.setBigDecimal(4,product.getCost());
		    stmt.setBigDecimal(5,product.getPrice());
		    stmt.setBigDecimal(6, product.getId());

		    stmt.executeUpdate();
		    stmt.close();
		    cxtn.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
	};
	
	
	
	public static void deleteProduct(Product product) {
		String sql = "DELETE FROM product "
				   + "WHERE  pro_cat_id = ?";
		
		Database.deleteFromTable(sql, product.getId());
	};
	
	
	public static HashMap<String,TableColumn<Product, ?>> getProductTableCols() {
		  
		   HashMap<String,TableColumn<Product, ?>> mapOfTableColumns = new HashMap<String,TableColumn<Product, ?>>();
		
		   TableColumn<Product, String> nameCol               =   new  TableColumn<Product, String>();
		   TableColumn<Product, ProductCategory> categoryCol  =   new  TableColumn<Product, ProductCategory>();
		   TableColumn<Product, String> descCol               =   new  TableColumn<Product, String>();
		   TableColumn<Product, BigDecimal> costCol           =   new  TableColumn<Product, BigDecimal>();
		   TableColumn<Product, BigDecimal> priceCol          =   new  TableColumn<Product, BigDecimal>();
		   //TableColumn<Product, Boolean> checkbox             =   new  TableColumn<Product, Boolean>();
		   //TableColumn<Product, String> qtyOfProductCol       =   new  TableColumn<Product, String>();


		  
		   @SuppressWarnings("rawtypes")
		   TableColumn qtyCol =  new TableColumn("Quantity");
		   
		   nameCol.setText("Item");
		   categoryCol.setText("Category");
		   descCol.setText("Description");
		   costCol.setText("Cost");
		   priceCol.setText("Price");
		   //checkbox.setText("Select");
		   //qtyOfProductCol.setText("Quantity");

	
		   
		   nameCol.setPrefWidth(150);
		   categoryCol.setPrefWidth(250);
		   descCol.setPrefWidth(80);
		   costCol.setPrefWidth(80);
		   priceCol.setPrefWidth(80);
		   //checkbox.setPrefWidth(50);
		   //qtyOfProductCol.setPrefWidth(80);

	    
		    nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		    categoryCol.setCellValueFactory(new PropertyValueFactory<Product, ProductCategory>("category"));
		    descCol.setCellValueFactory(new PropertyValueFactory<Product, String>("desc"));
		    costCol.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("Cost"));
		    priceCol.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("Price"));
		    
		    //checkbox.setCellValueFactory(new PropertyValueFactory<Product, Boolean>("isSelected"));
		    //qtyOfProductCol.setCellValueFactory(new PropertyValueFactory<Product, String>("qty"));
		    	    	
			
			mapOfTableColumns.put("nameCol",nameCol);
			mapOfTableColumns.put("categoryCol",categoryCol);
			mapOfTableColumns.put("descCol",descCol);
			mapOfTableColumns.put("costCol",costCol);
			mapOfTableColumns.put("priceCol",priceCol);
		   // mapOfTableColumns.put("checkbox",checkbox);
            //mapOfTableColumns.put("qtyOfProductCol",qtyOfProductCol);

	    	return mapOfTableColumns;

	    }
}
