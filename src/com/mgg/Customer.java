package com.mgg;

import java.util.List;

public class Customer extends Person {
	
	private String customerCode;
	private String type;
	private String firstName;
	private String lastName;
	private Address address;
	private List<String> emails;
	
	public Customer(String customerCode,
					String type,
					String firstName,
					String lastName,
					Address address,
					List<String> emails) {
		
		super(customerCode, type, firstName, lastName, address, emails);
	}
	
	public Customer() {
		this.customerCode = "";
		this.type = "";
		this.firstName = "";
		this.lastName = "";
		this.address = null;
		this.emails = null;
	}
	
	public void setPersonValues(Person i, Person j) {
		i.setPersonCode(j.getPersonCode());
		i.setType(j.getType());
		i.setFirstName(j.getFirstName());
		i.setLastName(j.getLastName());
		i.setAddress(j.getAddress());
		i.setEmails(j.getEmails());
	}

}
