package com.pos.org;

import java.util.ArrayList;

import com.pos.account.Attendant;

public class Shop {
	private String name;
	private String location;
	private ArrayList<Attendant> shopAttendant;
	
	public void open() {}
	public void close() {}
	
	public ArrayList<Attendant> getShopAttendant() {
		return shopAttendant;
	}
	public void setShopAttendant(ArrayList<Attendant> shopAttendant) {
		this.shopAttendant = shopAttendant;
	}
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
	
	
	
}
