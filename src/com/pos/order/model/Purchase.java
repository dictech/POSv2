package com.pos.order.model;

import java.math.BigDecimal;

import com.pos.database.PurchaseCache;
import com.pos.inventory.model.Product;
import com.pos.order.controller.OrderTransactionCtrl;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Purchase {
	private BigDecimal id;
	private String orderId;
	private Product product;
	private BigDecimal qty;
	
	private Button delButton;
	
	public Purchase() {
		delButton =  new Button("X");
		delButton.setOnAction(e->{
			OrderTransactionCtrl.clearExistingSelectedPurchaseFromCache();
		});
	}
	
	
	public Button getDelButton() {
		return delButton;
	}


	public void setDelButton(Button delButton) {
		this.delButton = delButton;
	}


	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	private BigDecimal totalPriceOfPurchase;
	private TextField qtyTextField;

	

 BigDecimal getId() {
		return id;
	}
	public TextField getQtyTextField() {
		return qtyTextField;
	}
	
	public void setQtyTextField(TextField qtyTextField) {
		this.qtyTextField = qtyTextField;
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
	
	
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	
	public BigDecimal getTotalPriceOfPurchase() {
		return totalPriceOfPurchase;
	}
	
	public void setTotalPriceOfPurchase(BigDecimal totalPriceOfPurchase) {
		this.totalPriceOfPurchase = totalPriceOfPurchase;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getProduct().getName();
	}
	
}
