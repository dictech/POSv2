package com.pos.org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pos.database.Database;

public class OrganizationDAO {
	
	
	public static void createOrg(Organization org){
		String sql = "insert into organization values(?,?,?,?,?,?)";
		try {
			Connection cxtn=  Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, org.getOrg_id());
			stmt.setString(2, org.getOrg_name());
			stmt.setString(3, org.getOrg_addrs());
			stmt.setString(4, org.getOrg_phone());
			stmt.setString(5, org.getOrg_email());
			stmt.setString(6, org.getOrg_logo());
			stmt.execute();	
			stmt.close();
			cxtn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Organization> getOrg(){
		String sql="select * from organization";
		List<Organization> listOfOrgs =  new ArrayList<Organization>();
		
		try {
			Connection cxtn=  Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			ResultSet row = stmt.executeQuery();
			while(row.next()) {
				Organization org =  new Organization();
				org.setOrg_id(row.getBigDecimal(1));
				org.setOrg_name(row.getString(2));
				org.setOrg_addrs(row.getString(3));
				org.setOrg_phone(row.getString(4));
				org.setOrg_email(row.getString(5));
				org.setOrg_logo(row.getString(6));
				listOfOrgs.add(org); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		listOfOrgs.forEach((org)->{
			       System.out.println(org.getOrg_name());
			       System.out.println(org.getOrg_addrs());
			       System.out.println(org.getOrg_phone());
			       System.out.println(org.getOrg_email());
			       System.out.println(org.getOrg_logo());
		});
		
		return listOfOrgs;
	}
	
	public static void updateOrg(Organization org) {
		String sql = "UPDATE organization "
				   + "SET    org_name  = ?,"
				   + "       org_addrs = ?,"
				   + "       org_phone = ?,"
				   + "       org_email = ?,"
				   + "       org_logo  = ?"
				   + "WHERE  org_id    = ?";
		
		try {
			System.out.println("Updating ..." + org.getOrg_id() + " with " + org.getOrg_name());

			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setString(1,org.getOrg_name());
			stmt.setString(2,org.getOrg_addrs());
			stmt.setString(3,org.getOrg_phone());
			stmt.setString(4,org.getOrg_email());
			stmt.setString(5,org.getOrg_logo());
			stmt.setBigDecimal(6, org.getOrg_id());
			stmt.executeUpdate();
			stmt.close();
			cxtn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteOrg(Organization org) {
		String sql = "DELETE FROM organization "
				   + "WHERE  org_id    = ?";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			PreparedStatement stmt = cxtn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, org.getOrg_id());
			stmt.execute();
			stmt.close();
			cxtn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Shop> getShops(){
		List<Shop> listOfShops =  new ArrayList<Shop>();
		String sql = "SELECT * FROM shop";
		
		try {
			Connection cxtn = Database.getDatabaseConnection();
			ResultSet rows = cxtn.createStatement().executeQuery(sql);
			
			while(rows.next()) {
				Shop shop =  new Shop();
				shop.setShopId(rows.getBigDecimal(1));
				shop.setShopName(rows.getString(2));
				shop.setShopLocation(rows.getString(3));
				
				listOfShops.add(shop);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		listOfShops.forEach((shop)->{
			System.out.println(shop.getShopId());
			System.out.println(shop.getShopName());
			System.out.println(shop.getShopLocation());
		});
		
		return listOfShops;
	}
}
