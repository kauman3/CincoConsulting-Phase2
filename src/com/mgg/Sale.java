package com.mgg;

public class Sale {
		
	private String saleCode;
	private String storeCode;
	private String customerCode;
	private String salespersonCode;
	//private List<Item>		will need to be a list of lists
		//create a getter in the main item class that gets the info stored in each individual items class
	
	public Sale(String saleCode, String storeCode, String customerCode, String salespersonCode) {
		this.saleCode = saleCode;
		this.storeCode = storeCode;
		this.customerCode = customerCode;
		this.salespersonCode = salespersonCode;
	}
	
}
