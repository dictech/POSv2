package com.pos.org;

import java.util.ArrayList;

import com.pos.sales.Seller;

public class Shop {
	private String name;
	private String location;
	private ArrayList<Seller> sellers;
	
	public void open() {}
	public void close() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ArrayList<Seller> getSellers() {
		return sellers;
	}
	public void setSellers(ArrayList<Seller> sellers) {
		this.sellers = sellers;
	}
	
	
}
