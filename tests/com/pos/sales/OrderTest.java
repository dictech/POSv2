package com.pos.sales;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pos.order.model.Order;
import com.pos.order.model.Purchase;

public class OrderTest {
	
	Order order;
	List<Purchase> listOfItems;
	
	@Before
	public void setUp() throws Exception {
		
		listOfItems = new ArrayList<Purchase>();
		listOfItems.add(new Purchase());
		listOfItems.add(new Purchase());
		listOfItems.add(new Purchase());
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createOrderTest() {
		
	}

}
