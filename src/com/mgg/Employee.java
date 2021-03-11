package com.mgg;

import java.util.Collections;
import java.util.List;

public class Employee extends Person {
	
	private int numSales;
	private double salesGrandTotal;
	private String employeeCode;
	private String type;
	private String firstName;
	private String lastName;
	private Address address;
	private List<String> emails;
	
	public Employee(String employeeCode,
					String type,
					String firstName,
					String lastName,
					Address address,
					List<String> emails) {
		
		super(employeeCode, type, firstName, lastName, address, emails);
	}
	
	public Employee() {
		this.employeeCode = "";
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

	/**
	 * @return the numSales
	 */
	public int getNumSales() {
		return numSales;
	}

	/**
	 * @param numSales the numSales to set
	 */
	public void setNumSales(int numSales) {
		this.numSales = numSales;
	}

	/**
	 * @return the salesGrandTotal
	 */
	public double getSalesGrandTotal() {
		return salesGrandTotal;
	}

	/**
	 * @param salesGrandTotal the salesGrandTotal to set
	 */
	public void setSalesGrandTotal(double salesGrandTotal) {
		this.salesGrandTotal = salesGrandTotal;
	}

}
