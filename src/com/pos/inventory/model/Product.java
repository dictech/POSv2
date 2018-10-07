package com.pos.inventory.model;

import java.math.BigDecimal;

public class Product {
	private BigDecimal id;
	private String name;
	private BigDecimal categoryId;
	private String desc;
	private int cost;
	private int price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(BigDecimal categoryId) {
		this.categoryId = categoryId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
}
