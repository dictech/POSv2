package com.pos.inventory.model;

import java.math.BigDecimal;

public class ProductInventory {
	
	private BigDecimal id;
	
	private Product product;
	private String proDesc;
	private String proName;
	private BigDecimal noOfUnits;
	private BigDecimal qtyPerUnit;
	private BigDecimal totalQty;
	private BigDecimal noOfOrdered;
	private BigDecimal reorderLvl;
	
	
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return this.product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public BigDecimal getNoOfUnits() {
		return noOfUnits;
	}
	public void setNoOfUnits(BigDecimal noOfUnits) {
		this.noOfUnits = noOfUnits;
	}
	public BigDecimal getQtyPerUnit() {
		return qtyPerUnit;
	}
	public void setQtyPerUnit(BigDecimal qtyPerUnit) {
		this.qtyPerUnit = qtyPerUnit;
	}
	public BigDecimal getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}
	public BigDecimal getNoOfOrdered() {
		return noOfOrdered;
	}
	public void setNoOfOrdered(BigDecimal noOfOrdered) {
		this.noOfOrdered = noOfOrdered;
	}
	public BigDecimal getReorderLvl() {
		return reorderLvl;
	}
	public void setReorderLvl(BigDecimal reorderLvl) {
		this.reorderLvl = reorderLvl;
	}
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}	
}
