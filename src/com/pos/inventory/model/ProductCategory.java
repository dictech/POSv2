package com.pos.inventory.model;

import java.math.BigDecimal;

public class ProductCategory{
	private BigDecimal id;
	private String 	   name;
	private String     desc;
	
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

}
