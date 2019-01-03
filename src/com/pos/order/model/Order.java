package com.pos.order.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Order {
	private BigDecimal order_id;
	private String order_no; 
	private BigDecimal order_attd_id;
	private Date   order_date;
	private String order_time; 
	private List<Purchase> listOfPurchasedItems;
	private BigDecimal totalPriceOfOrder;
	
     
	
	public BigDecimal getOrder_id() {
		return order_id;
	}
	public void setOrder_id(BigDecimal order_id) {
		this.order_id = order_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public BigDecimal getOrder_attd_id() {
		return order_attd_id;
	}
	public void setOrder_attd_id(BigDecimal order_attd_id) {
		this.order_attd_id = order_attd_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
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
