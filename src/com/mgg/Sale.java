package com.mgg;

import java.util.List;

/**
 * TODO: add documentation
 * @author kylea
 *
 */
public class Sale {
		
	private String saleCode;
	private String storeCode;
	private String customerCode;
	private String salespersonCode;
//	private List <Item> itemsPurchased;
	private List<String> saleDetails;

	public Sale(String saleCode,
			String storeCode, 
			String customerCode, 
			String salespersonCode,
			List<String> saleDetails) {
	this.saleCode = saleCode;
	this.storeCode = storeCode;
	this.customerCode = customerCode;
	this.salespersonCode = salespersonCode;
	this.saleDetails = saleDetails;
	}
	
	/**
	 * @return the saleCode
	 */
	public String getSaleCode() {
		return saleCode;
	}

	/**
	 * @return the storeCode
	 */
	public String getStoreCode() {
		return storeCode;
	}
	
	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * @return the salespersonCode
	 */
	public String getSalespersonCode() {
		return salespersonCode;
	}

	/**
	 * @return the saleDetails
	 */
	public List<String> getSaleDetails() {
		return saleDetails;
	}	
	
}
