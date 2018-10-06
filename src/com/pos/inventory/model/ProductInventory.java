package com.pos.inventory.model;

import java.math.BigDecimal;

public class ProductInventory {
	
	private BigDecimal id;
	private int proId;
	private String proDesc;
	private int noOfUnits;
	private int qtyPerUnit;
	private int totalQty;
	private int noOfOrdered;
	private int reorderLvl;
	
	
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public int getNoOfUnits() {
		return noOfUnits;
	}
	public void setNoOfUnits(int noOfUnits) {
		this.noOfUnits = noOfUnits;
	}
	public int getQtyPerUnit() {
		return qtyPerUnit;
	}
	public void setQtyPerUnit(int qtyPerUnit) {
		this.qtyPerUnit = qtyPerUnit;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public int getNoOfOrdered() {
		return noOfOrdered;
	}
	public void setNoOfOrdered(int noOfOrdered) {
		this.noOfOrdered = noOfOrdered;
	}
	public int getReorderLvl() {
		return reorderLvl;
	}
	public void setReorderLvl(int reorderLvl) {
		this.reorderLvl = reorderLvl;
	}
		
}
