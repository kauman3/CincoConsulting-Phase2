package com.mgg;

import java.util.List;

public class Sale {
		
	private String saleCode;
	private String storeCode;
	private String customerCode;
	private String salespersonCode;
	private List <Item> itemsPurchased;
	private String str1;
	private String str2;
	private List<String> itemInfo;

	public Sale(String saleCode,
			String storeCode, 
			String customerCode, 
			String salespersonCode, 
			List <Item> itemsPurchased,
			List<String> itemInfo) {
	this.saleCode = saleCode;
	this.storeCode = storeCode;
	this.customerCode = customerCode;
	this.salespersonCode = salespersonCode;
	this.itemsPurchased = itemsPurchased;
	this.itemInfo = itemInfo;
}
	
//	public Sale(String saleCode,
//				String storeCode, 
//				String customerCode, 
//				String salespersonCode, 
//				List <Item> itemsPurchased,
//				String str1,
//				String str2) {
//		this.saleCode = saleCode;
//		this.storeCode = storeCode;
//		this.customerCode = customerCode;
//		this.salespersonCode = salespersonCode;
//		this.itemsPurchased = itemsPurchased;
//		this.str1 = str1;
//		this.str2 = str2;
//	}
//	
//	public Sale(String saleCode,
//			String storeCode, 
//			String customerCode, 
//			String salespersonCode, 
//			List <Item> itemsPurchased,
//			String str1) {
//	this.saleCode = saleCode;
//	this.storeCode = storeCode;
//	this.customerCode = customerCode;
//	this.salespersonCode = salespersonCode;
//	this.itemsPurchased = itemsPurchased;
//	this.str1 = str1;
//}
	
	
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
