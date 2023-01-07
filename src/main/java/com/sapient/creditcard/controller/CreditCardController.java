package com.sapient.creditcard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.creditcard.CreditCardManagementService;
import com.sapient.creditcard.model.CreditCardModel;

@RestController
public class CreditCardController {
	
	@Autowired
	private CreditCardManagementService creditCardManagementService;

	@GetMapping("/creditcarddetails/getall")
	public List<CreditCardModel> getAllCreditCards() {
		return creditCardManagementService.findAll();
	}
	
	@PostMapping("/creditcards")
	public ResponseEntity<?> createCreditCard(@RequestBody CreditCardModel creditCard) {
		if (creditCard.validateLuhn10()) {
			try {
				creditCardManagementService.saveCreditCard(creditCard);
				return new ResponseEntity<Object>(HttpStatus.OK);
			} catch (DuplicateKeyException e) {
				return new ResponseEntity<Error>(HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}