package com.pos.inventory.model;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import com.pos.order.controller.OrderTransactionCtrl;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Product {
	
	private BigDecimal id;
	private String name;
	

	private ProductCategory category;
	private String desc;
	private BigDecimal cost;
	private BigDecimal price;
	//private CheckBox isSelected;
	//private TextField qty;
	
	public Product() {
//		this.qty = new TextField();
//		this.isSelected = new CheckBox();
//		isSelected.setOnAction(event ->{
//			OrderTransactionCtrl ctrl =  new OrderTransactionCtrl();
//			CheckBox chx = (CheckBox)event.getSource();
//			
//			if(chx.isSelected()) {
//				try {
//					ctrl.addToCache();
//				} catch (ExecutionException e) {
//					e.printStackTrace();
//				}
//			}else {
//				ctrl.removeFromCache();
//				
//			}
//		});
	}

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
