package com.pos.inventory.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class ProductCategoryDAO {
	
	public static void createCategory(ProductCategory category) {
		String sql = "INSERT INTO product_category "
				   + "values(?,?,?)";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    PreparedStatement stmt = cxtn.prepareStatement(sql);
		    
		    stmt.setBigDecimal(1, category.getId());
		    stmt.setString(2,category.getName());
		    stmt.setString(3,category.getDesc());
		    stmt.execute();
		    stmt.close();
		    cxtn.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<ProductCategory> getAllCategory() {
	    List<ProductCategory> listOfCategories = new ArrayList<ProductCategory>();
 		String sql = "SELECT * FROM product_category order by cat_id desc ";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    ResultSet rows  = cxtn.createStatement().executeQuery(sql);
		    
		    while(rows.next()) {
		    	ProductCategory category = new ProductCategory();
		    	category.setId(rows.getBigDecimal(1));
		    	category.setName(rows.getString(2));
		    	category.setDesc(rows.getString(3));
		    	listOfCategories.add(category);
		    }
		    
		    listOfCategories.forEach((category)->{
		    	System.out.println(category.getId());
		    	System.out.println(category.getName());
		    	System.out.println(category.getDesc());
		    });
		      
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listOfCategories;
	}
	
	
	public static ProductCategory getCategory(BigDecimal id) {
    	ProductCategory category = new ProductCategory();
 		String sql = "SELECT * "
 				   + "FROM product_category "
 				   + "WHERE cat_id = ?";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    PreparedStatement stmt  = cxtn.prepareStatement(sql);
		    stmt.setBigDecimal(1, id);
		    ResultSet row = stmt.executeQuery();
		    
		    if(row.next()) {
		    	category.setId(row.getBigDecimal(1));
		    	category.setName(row.getString(2));
		    	category.setDesc(row.getString(3));
		    }
		    
	    	System.out.println(category.getId());
	    	System.out.println(category.getName());
	    	System.out.println(category.getDesc());
		      
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return category;
	}
	
	
	public static void updateCategory(ProductCategory category) {
		String sql = "UPDATE product_category "
				   + "SET    cat_name = ?, "
				   + "       cat_desc = ?  "
				   + "WHERE  cat_id   = ?";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    PreparedStatement stmt = cxtn.prepareStatement(sql);
		    
		    stmt.setString(1,category.getName());
		    stmt.setString(2,category.getDesc());
		    stmt.setBigDecimal(3, category.getId());

		    stmt.executeUpdate();
		    stmt.close();
		    cxtn.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void deleteCategory(ProductCategory category) {
		String sql = "DELETE FROM product_category "
				   + "WHERE  cat_id = ?";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
		    PreparedStatement stmt = cxtn.prepareStatement(sql);
		    
		    stmt.setBigDecimal(1,category.getId());
		    stmt.execute();
		    stmt.close();
		    cxtn.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
