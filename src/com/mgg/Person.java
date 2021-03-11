package com.mgg;

import java.util.Collections;
import java.util.List;


/**
 * This class models a Person with a personCode, type, firstName, lastName, and address.
 * 
 * @author kauman<br \>
 * Kyle Auman<br \>
 * kauman3@huskers.unl.edu<br \>
 * CSCE156<br \><br \>
 * @author zmain<br \>
 * Zach Main<br \>
 * zmain2@huskers.unl.edu<br \>
 * CSCE156<br \>
 *
 */
public class Person {
	
	private String personCode;
	private String type;
	private String firstName;
	private String lastName;
	private Address address;
	private List<String> emails;
	
	public Person(String personCode, String type, String firstName, String lastName, Address address, List<String> emails) {
		this.personCode = personCode;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emails = emails;
	}
	
	public Person(String personCode, String type, String firstName, String lastName, Address address) {
		this(personCode, type, firstName, lastName, address, Collections.emptyList());
	}
	
	public Person() {
		this.personCode = "";
		this.type = "";
		this.firstName = "";
		this.lastName = "";
		this.address = null;
		this.emails = null;
	}
	
//	public void setPersonValues(Person p) {
//		this.personCode = p.getPersonCode();
//		this.type = p.getType();
//		this.firstName = p.getFirstName();
//		this.lastName = p.getLastName();
//		this.address = p.getAddress();
//		this.emails = p.getEmails();
//	}
	
	public void setPersonValues(Person i, Person j) {
		i.setPersonCode(j.getPersonCode());
		i.setType(j.getType());
		i.setFirstName(j.getFirstName());
		i.setLastName(j.getLastName());
		i.setAddress(j.getAddress());
		i.setEmails(j.getEmails());
	}
	
	/**
	 * @return the personCode
	 */
	public String getPersonCode() {
		return personCode;
	}

	/**
	 * @param personCode the personCode to set
	 */
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the emails
	 */
	public List<String> getEmails() {
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	
}
