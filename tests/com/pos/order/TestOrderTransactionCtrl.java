package com.pos.order;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pos.account.model.Attendant;
import com.pos.order.model.Order;
import com.pos.order.model.OrderDAO;
import com.pos.order.model.Purchase;

public class TestOrderTransactionCtrl {
	
	Attendant attendant;
	Order order;
	List<Purchase> listOfPurchases;
	Purchase purchase;
	
	@Before
	public void setup() {
		attendant =  new Attendant();
		attendant.setId(new BigDecimal(33));
		
		listOfPurchases =  new ArrayList<Purchase>();
		
		purchase = new Purchase();
		purchase.setId(new BigDecimal(100));
		listOfPurchases.add(purchase);
		
		
		order =  new Order();
		order.setListOfPurchasedItems(listOfPurchases);
		order.setOrder_attd_id(attendant.getId());
		order.setOrder_date(Date.valueOf(LocalDate.now()));
		order.setOrder_id(new BigDecimal(8000));
		order.setOrder_no("abcd");
		order.setOrder_time(Time.valueOf(LocalTime.now()).toString());
		order.setTotalPriceOfOrder(new BigDecimal(8000));
	}

	@Test
	public void shouldSearchForProduct() {
		
	}
	
    @Test
    public void shouldCreateOrder() {
    	
    	Boolean t = false;
    	int isCreated =  OrderDAO.createOrder(order);
    	if(isCreated != 1) {
    		t = true;
    	}
		assertTrue(t);

    }

}
