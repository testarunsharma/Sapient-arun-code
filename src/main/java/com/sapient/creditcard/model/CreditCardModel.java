package com.sapient.creditcard.model;

public class CreditCardModel {
	
	private String cardHolder;
	private String cardNumber;
	private int limit;
	private int balance;
	
	public CreditCardModel(String cardHolder, String number, int limit, int balance) {
		this.cardHolder = cardHolder;
		this.cardNumber = number;
		this.limit = limit;
		this.balance = balance;
	}
	
	public CreditCardModel() {}

	public String getcardHolder() {
		return cardHolder;
	}
	public void setcardHolder(String userName) {
		this.cardHolder = userName;
	}
	public String getNumber() {
		return cardNumber;
	}
	public void setNumber(String number) {
		this.cardNumber = number;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public boolean validateLuhn10() {
		cardNumber = cardNumber.replaceAll("[ -]", "");
	    int sum = 0;
	    for (int i=0; i<cardNumber.length(); i++){
	    int digit = (int) cardNumber.charAt(i) - '0';
	      if (i % 2 == 0) {
	        digit *= 2;
	        if (digit > 9)
	          digit -= 9;
	      }
	      sum += digit;
	    }
	    return (sum % 10) == 0;
	}
	
}
