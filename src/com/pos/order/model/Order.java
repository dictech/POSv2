package com.pos.order.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Order {
	private BigDecimal id;
	private String code; 
	private Date   date;
	private String time; 
	private List<Purchase> listOfPurchasedItems;
	private BigDecimal totalPriceOfOrder;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String no) {
		this.code = no;
	}
	public List<Purchase> getListOfPurchasedItems() {
		return listOfPurchasedItems;
	}
	public void setListOfPurchasedItems(List<Purchase> listOfPurchasedItems) {
		this.listOfPurchasedItems = listOfPurchasedItems;
	}
	public BigDecimal getTotalPriceOfOrder() {
		return totalPriceOfOrder;
	}

	public void setTotalPriceOfOrder(BigDecimal totalPriceOfOrder) {
		this.totalPriceOfOrder = totalPriceOfOrder;
	}
}
