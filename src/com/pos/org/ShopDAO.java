package com.pos.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class ShopDAO {
	
	public static void createShop(Shop shop) {
		String sql = "INSERT INTO shop VALUES(?,?,?)";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, shop.getShopId());
			stmt.setString(2, shop.getShopName());
			stmt.setString(3, shop.getShopLocation());
			stmt.execute();
			stmt.close();
			cxtn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Shop> getShops() {
		List<Shop> listOfShops = new ArrayList<Shop>();
		String sql =  "SELECT * FROM shop";
		
		try {
			Connection cxtn =  Database.getDatabaseConnection();
			ResultSet rows =  cxtn.createStatement().executeQuery(sql);
			
			while(rows.next()) {
				Shop shop =  new Shop();
				shop.setShopId(rows.getBigDecimal(1));
				shop.setShopName(rows.getString(2));
				shop.setShopLocation(rows.getString(3));
				shop.setShopBranch(rows.getString(4));
				shop.setShopRCNo(rows.getString(5));
				shop.setShopMgr(rows.getString(6));
				
				listOfShops.add(shop);
			}
		}catch(Exception e) {
				 e.printStackTrace();
			 }
		
		listOfShops.forEach((shop)->{
			System.out.print(shop.getShopId());
			System.out.print(shop.getShopName());
			System.out.print(shop.getShopLocation());
			System.out.print(shop.getShopBranch());
			System.out.print(shop.getShopRCNo());
			System.out.print(shop.getShopMgr());
		});
		
		return listOfShops;
	}
	
	public static void updateShop(Shop shop) {
		String sql = "UPATE shop"
					+"SET   shop_name = ?,"
					+"      shop_location = ?,"
					+"      shop_branch   = ?,"
					+"      shop_rc_no    = ?,"
					+"      shop_owner    = ?"
					+"WHERE shop_id       = ?";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setString(1, shop.getShopName());
			stmt.setString(2, shop.getShopLocation());
			stmt.setString(3, shop.getShopBranch());
			stmt.setString(4, shop.getShopRCNo());
			stmt.setString(5, shop.getShopMgr());
			stmt.setBigDecimal(6, shop.getShopId());

		    stmt.executeUpdate();
		    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void deleteShop(Shop shop) {
		String sql = "DELETE FROM shop"
				    +"WHERE  shop_id = ?";
		
		try {
			
			Connection cxtn =  Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, shop.getShopId());
			stmt.execute();
			
			stmt.close();
			cxtn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
