package com.pos.sales;

import java.math.BigDecimal;
import java.sql.Date;

import com.pos.account.Attendant;

public class Payment {
	private BigDecimal id;
	private Attendant  recipient;
	private Order  order;
	private int    price;
	private int    amtPaid;
	private int    balance;
	private String type;
	private Date   date; 
	private String time;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public Attendant getRecipient() {
		return recipient;
	}
	public void setRecipient(Attendant recipient) {
		this.recipient = recipient;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(int amtPaid) {
		this.amtPaid = amtPaid;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
}
