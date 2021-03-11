package com.mgg;

import java.util.List;

public class Sale {
		
	private String saleCode;
	private String storeCode;
	private Person customer;
	private Person salesperson;
	//private List<Item> items;	//will need to be a list of lists
		//create a getter in the main item class that gets the info stored in each individual items class
	
	public Sale(String saleCode, String storeCode, Person customer, Person salesperson) {
		this.saleCode = saleCode;
		this.storeCode = storeCode;
		this.customer = customer;
		this.salesperson = salesperson;
		//this.items = items;
	}
	
//	public static String saleToString(Sale sale) {
//		return this.name + " (" + this.age + ")";
//	}
	
	//TODO: implement employeeSalesReport
	//TODO: add documentation
//	public static void employeeSalesReport(List<Sale> sales) {
//		for(Sale s : sales) {
//			//add 1 for every sale
//			//add the cost of that sale every time
//			
//			s.salesperonCode;
//		}
//	}
	
	//TODO: implement storeSalesReport
	//TODO: add documentation
	public static void storeSalesReport(List<Sale> sales) {
		
	}
}
