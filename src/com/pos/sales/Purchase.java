package com.pos.sales;

import java.math.BigDecimal;

import com.pos.inventory.Product;

public class Purchase {
	private BigDecimal id;
	private Order order;
	private Product product;
	
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
