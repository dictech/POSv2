package com.pos.order.model;

import java.math.BigDecimal;

import com.pos.inventory.model.Product;

public class Purchase {
	private BigDecimal id;
	private Product product;
	private int qty;
	private int totalPriceOfPurchase;
	
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public int getTotalPriceOfPurchase() {
		return totalPriceOfPurchase;
	}
	
	public void setTotalPriceOfPurchase(int totalPriceOfPurchase) {
		this.totalPriceOfPurchase = totalPriceOfPurchase;
	}
	
	
}
