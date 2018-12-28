package com.pos.payments;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pos.account.model.Attendant;
import com.pos.payment.model.Payment;
import com.pos.payment.model.paymentDAO;

public class TestPayment {
	
	Payment payment;
	boolean status;

	@Before
	public void setUp() throws Exception {
		
		payment = new Payment();
		payment.setRecipient(new Attendant());
		payment.setType("C");
		payment.setAmtPaid(1000);
		payment.setBalance(0);
	}

	@Test
	public void testCreatePayment() {	
		
		status = paymentDAO.createPayment(payment);
		assertTrue(status);
	}
	
	@Test
	public void testUpdatePayment() {	
		
		status = paymentDAO.updatePayment(payment);
		assertTrue(status);
	}

}
