package com.sapient.creditcard;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

import com.sapient.creditcard.model.CreditCardModel;

public class CreditCardServiceUnitTests {
	
	CreditCardManagementService creditCardManagementService;
	
	@Before
	public void init() {
		creditCardManagementService = new CreditCardManagementService();
		CreditCardManagementService.init();
	}


	@Test
	public void testDupliacteKeys() {
		String cardNumber = "9324567876873454";
		CreditCardModel cd = new CreditCardModel("Arun", cardNumber, 1000, 0);
		creditCardManagementService.saveCreditCard(cd);
		
		CreditCardModel found = creditCardManagementService.findAll()
				.stream()
				.filter(card -> card.getNumber().equals(cardNumber))
				.findFirst()
				.get();
		
		assertTrue( found.getNumber().equals(cd.getNumber()) );
		
		try {
			creditCardManagementService.saveCreditCard(cd);
			fail();
		} catch (DuplicateKeyException e) {}
	}
	
	@Test
	public void testLuhn10Algo() {
		String cardNumber = "9324-5678-7687-3454";
		CreditCardModel cd = new CreditCardModel("Arun", cardNumber, 100, 0);
		assertTrue( cd.validateLuhn10() );
		
	}
	
	@Test
	public void testLuhn10AlgoNG() {
				
		String invalidCardNumber = "9324-5678-7687-3434";
		CreditCardModel cd2 = new CreditCardModel("Arun", invalidCardNumber, 100, 0);
		assertFalse( cd2.validateLuhn10() );
	}

}
