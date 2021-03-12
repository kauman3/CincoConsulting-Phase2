package com.mgg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //TODO: implement
 * //TODO: add documentation
 * @author kylea
 *
 */
public class SalesReport {
	
	/**
	 * TODO: add documentation
	 * @param persons
	 * @param idToItem
	 * @param sales
	 */
	public static void salespersonReport(List<Person> persons,
										 Map<String, Item> idToItem,
										 List<Sale> sales) {
		
		List<Person> salespersons = new ArrayList<>();
		for(Person p : persons) {
			if(p.getType().contentEquals("E")) {
				salespersons.add(p);
			}
		}
		
		Map<Person, List<Sale>> salespersonToSales = new HashMap<>();
		for(Person p : salespersons) {
			List<Sale> individualSales = new ArrayList<>();
			for(Sale s : sales) {
				if(s.getSalespersonCode().contentEquals(p.getCode())) {
					individualSales.add(s);
				}
			}
			salespersonToSales.put(p, individualSales);
		}
		
		int numCompanySales = 0;
		double companyGrandTotal = 0;
		System.out.println("Salesperson Summary Report");
		System.out.printf("%-20s %-10s %-10s\n", "Salesperons", "# Sales", "Grand Total");
		for(Person p : salespersonToSales.keySet()) {
			int numSales = salespersonToSales.get(p).size();
			numCompanySales += numSales;
			double salespersonGrandTotal = getGrandTotal(idToItem, salespersonToSales.get(p));
			companyGrandTotal += salespersonGrandTotal;
			System.out.printf("%-20s %-10d $%10.2f\n", p.getFullName(), numSales, salespersonGrandTotal);
		}
		System.out.printf("%22d %20.2f\n\n", numCompanySales, companyGrandTotal);
		return;
	}
	
	/**
	 * TODO: add documentation
	 * @param stores
	 * @param persons
	 * @param idToItem
	 * @param sales
	 */
	private static void storeSalesReport(List<Store> stores,
										 List<Person> persons, 
										 Map<String, Item> idToItem,
										 List<Sale> sales) {
		
		Map<Store, List<Sale>> storeToSales = new HashMap<>();
		for(Store store : stores) {
			List<Sale> individualStoreSales = new ArrayList<>();
			for(Sale s : sales) {
				if(s.getStoreCode().contentEquals(store.getCode())) {
					individualStoreSales.add(s);
				}
			}
			storeToSales.put(store, individualStoreSales);
		}
		
		int numCompanySales = 0;
		double companyGrandTotal = 0;
		System.out.println("Store Sales Summary Report");
		System.out.printf("%-10s %-20s %-10s %-10s\n", "Store", "Manager", "# Sales", "Grand Total");
		for(Store s : storeToSales.keySet()) {
			int numSales = storeToSales.get(s).size();
			numCompanySales += numSales;
			double salespersonGrandTotal = getGrandTotal(idToItem, storeToSales.get(s));
			companyGrandTotal += salespersonGrandTotal;
			System.out.printf("%-10s %-20s %-10d $%10.2f\n", s.getCode(), s.getManager().getFullName(), numSales, salespersonGrandTotal);
		}
		System.out.printf("%33d %20.2f\n\n", numCompanySales, companyGrandTotal);
		return;
	}
	
	/**
	 * TODO: add documentation
	 * @param idToItem
	 * @param sales
	 * @return
	 */
	public static double getGrandTotal(Map<String, Item> idToItem, List<Sale> sales) {
		double grandTotal = 0;
		for(Sale s : sales) {
			for(int i=0; i<s.getSaleDetails().size(); i++) {
				if(idToItem.containsKey(s.getSaleDetails().get(i))) {
					if(idToItem.get(s.getSaleDetails().get(i)).getType().contentEquals("PN")) {
						int quantity = Integer.parseInt(s.getSaleDetails().get(i+1));
						grandTotal += Double.parseDouble(idToItem.get(s.getSaleDetails().get(i)).getPrice()) * quantity;
					} else if (idToItem.get(s.getSaleDetails().get(i)).getType().contentEquals("PU")) {
						int quantity = Integer.parseInt(s.getSaleDetails().get(i+1));
						grandTotal += Double.parseDouble(idToItem.get(s.getSaleDetails().get(i)).getPrice()) * quantity * 0.8;
					} else if(idToItem.get(s.getSaleDetails().get(i)).getType().contentEquals("PG")) {
						grandTotal += Double.parseDouble(s.getSaleDetails().get(i+1));
					} else if (idToItem.get(s.getSaleDetails().get(i)).getType().contentEquals("SB")) {
						int startYear = Integer.parseInt(s.getSaleDetails().get(i+1).substring(0,4));
						int endYear = Integer.parseInt(s.getSaleDetails().get(i+2).substring(0,4));
						int subscriptionLength = endYear - startYear;
						grandTotal += Double.parseDouble(idToItem.get(s.getSaleDetails().get(i)).getPrice()) * subscriptionLength;
					} else {
						int hours = Integer.parseInt(s.getSaleDetails().get(i+2));
						grandTotal += Double.parseDouble(idToItem.get(s.getSaleDetails().get(i)).getPrice()) * hours;
					}
				}
			}
		}
		
		return grandTotal;
	}
	
	public static void main(String[] args) {
		
		String personsFile = "data/Persons.csv";
        List<Person> persons = DataConverter.loadPersonData(personsFile);
        String storesFile = "data/Stores.csv";
        List<Store> stores = DataConverter.loadStoreData(storesFile, persons);
        String itemsFile = "data/Items.csv";
        Map<String, Item> idToItem = DataConverter.loadItemData(itemsFile);
        String salesFile = "data/Sales.csv";
        List<Sale> sales = DataConverter.loadSaleData(salesFile, persons, stores, idToItem);
        
		salespersonReport(persons, idToItem, sales);
		storeSalesReport(stores, persons, idToItem, sales);
	}
	
}
