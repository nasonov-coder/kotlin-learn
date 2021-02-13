package tests;

import A.GettingStarted.Customer;

public class Buyer {
	private Customer c;

	public void setCustomer(Customer c) {
		System.out.println(c.toString());
		this.c = c;
	}

}

