package com.mgg;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public static void salespersonReport(Map<String, Person> persons, Map<String, Item> idToItem, List<Sale> sales) {

		List<String> salespersons = new ArrayList<>();
		for (String str : persons.keySet()) {
			if (persons.get(str).getType().contentEquals("E")) {
				salespersons.add(str);
			}
		}

		Map<String, List<Sale>> salespersonToSales = new HashMap<>();
		for (String str : salespersons) {
			List<Sale> individualSales = new ArrayList<>();
			for (Sale s : sales) {
				if (s.getSalespersonCode().contentEquals(str)) {
					individualSales.add(s);
				}
			}
			salespersonToSales.put(str, individualSales);
		}

		int numCompanySales = 0;
		double companyGrandTotal = 0;
		System.out.println("+----------------------------+");
        System.out.println("| Salesperson Summary Report |");
        System.out.println("+----------------------------+\n");
		System.out.printf("%-20s %-10s %-10s\n", "Salesperons", "# Sales", "Grand Total");
		System.out.println("+-----------------------------------------------------+");
		for (String str : salespersonToSales.keySet()) {
			int numSales = salespersonToSales.get(str).size();
			numCompanySales += numSales;
			double salespersonGrandTotal = getGrandTotal(idToItem, salespersonToSales.get(str));
			companyGrandTotal += salespersonGrandTotal;
			System.out.printf("%-20s %-10d $%10.2f\n", persons.get(str).getFullName(), numSales, salespersonGrandTotal);
		}
		System.out.printf("%22d %20.2f\n", numCompanySales, companyGrandTotal);
		System.out.println("-----------------------------------------------------------------");

		return;
	}
	
	/**
	 * TODO: add documentation
	 * @param stores
	 * @param persons
	 * @param idToItem
	 * @param sales
	 */
	private static void storeSalesReport(List<Store> stores, Map<String, Item> idToItem, List<Sale> sales) {

		Map<Store, List<Sale>> storeToSales = new HashMap<>();
		for (Store store : stores) {
			List<Sale> individualStoreSales = new ArrayList<>();
			for (Sale s : sales) {
				if (s.getStoreCode().contentEquals(store.getCode())) {
					individualStoreSales.add(s);
				}
			}
			storeToSales.put(store, individualStoreSales);
		}

		int numCompanySales = 0;
		double companyGrandTotal = 0;
		System.out.println("+----------------------------+");
        System.out.println("| Store Sales Summary Report |");
        System.out.println("+----------------------------+\n");
		System.out.printf("%-10s %-20s %-10s %-10s\n", "Store", "Manager", "# Sales", "Grand Total");
		System.out.println("+-----------------------------------------------------+");
		for (Store s : storeToSales.keySet()) {
			int numSales = storeToSales.get(s).size();
			numCompanySales += numSales;
			double salespersonGrandTotal = getGrandTotal(idToItem, storeToSales.get(s));
			companyGrandTotal += salespersonGrandTotal;
			System.out.printf("%-10s %-20s %-10d $%10.2f\n", s.getCode(), s.getManager().getFullName(), numSales,
					salespersonGrandTotal);
		}
		System.out.printf("%33d %20.2f\n", numCompanySales, companyGrandTotal);
		System.out.println("-----------------------------------------------------------------");
		return;
	}
	
	
	public static void detailedSalesReport(Map<String, Person> persons, Map<String, Item> idToItem, List<Sale> sales) {

		for (Sale s : sales) {
			System.out.println("Sale   #" + s.getSaleCode());
			System.out.println("Store  #" + s.getStoreCode());
			System.out.println("Customer:");
			System.out.print(persons.get(s.getCustomerCode()).getFullName() + " ([");
			for(String e : persons.get(s.getCustomerCode()).getEmails()) {
				
				System.out.print(e);
			}
			System.out.print("])\n");
			System.out.printf("%s\n%10s %s %s %s\n", persons.get(s.getCustomerCode()).getAddress().getStreet(),
							   					 	 persons.get(s.getCustomerCode()).getAddress().getCity(),
							   					 	 persons.get(s.getCustomerCode()).getAddress().getState(),
							   					 	 persons.get(s.getCustomerCode()).getAddress().getZip(),
							   					 	 persons.get(s.getCustomerCode()).getAddress().getCountry());
			System.out.printf("%-50s%s\n", "Item(s)", "Total");
			
			double totalPrice = 0;
			for(String str : s.getSaleDetails()) {
				if(idToItem.containsKey(str)) {
					double price = 0;
					double quantity = 1;
					System.out.println(idToItem.get(str).getName());
					if(idToItem.get(str).getType().contentEquals("PN")) {
						price = Double.parseDouble(idToItem.get(str).getPrice());
						quantity = Double.parseDouble(s.getFirstValue(str));
						System.out.printf("     (New Item #%s @$%.2f/ea)%30.2f\n", str, price, price * quantity);
					} else if(idToItem.get(str).getType().contentEquals("PU")) {
						price = Double.parseDouble(idToItem.get(str).getPrice()) * 0.8;
						quantity = Double.parseDouble(s.getFirstValue(str));
						System.out.printf("     (Used Item #%s @$%.2f/ea)%30.2f\n", str, price, price * quantity);
					} else if(idToItem.get(str).getType().contentEquals("PG")) {
						price = Double.parseDouble(s.getFirstValue(str));
						System.out.printf("     (Gift Card #%s)%30.2f\n", str, price);
					} else if(idToItem.get(str).getType().contentEquals("SV")) {
						price = Double.parseDouble(idToItem.get(str).getPrice());
						quantity = Double.parseDouble(s.getSecondValue(str));
						System.out.printf("     (Service #%s by %s %.1fhrs @$%.2f/hr)%30.2f\n", str, persons.get(s.getFirstValue(str)).getFullName(), quantity, price, price * quantity);
					} else {
						price = Double.parseDouble(idToItem.get(str).getPrice());
						quantity = s.getDays(str) / 365.0;
						System.out.printf("     (Subscription #%s %d days@$%.2f/year)%30.2f\n", str, (int) (quantity * 365), price, price * quantity);
					}
					totalPrice += price * quantity;
				}
			}
			System.out.printf("%.2f\n", totalPrice);
		}
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
		for (Sale s : sales) {
			for (int i = 0; i < s.getSaleDetails().size(); i++) {
				if (idToItem.containsKey(s.getSaleDetails().get(i))) {
					if (idToItem.get(s.getSaleDetails().get(i)).getType().contentEquals("PN")) {
						int quantity = Integer.parseInt(s.getSaleDetails().get(i + 1));
						grandTotal += Double.parseDouble(idToItem.get(s.getSaleDetails().get(i)).getPrice()) * quantity;
					} else if (idToItem.get(s.getSaleDetails().get(i)).getType().contentEquals("PU")) {
						int quantity = Integer.parseInt(s.getSaleDetails().get(i + 1));
						grandTotal += Double.parseDouble(idToItem.get(s.getSaleDetails().get(i)).getPrice()) * quantity
								* 0.8;
					} else if (idToItem.get(s.getSaleDetails().get(i)).getType().contentEquals("PG")) {
						grandTotal += Double.parseDouble(s.getSaleDetails().get(i + 1));
					} else if (idToItem.get(s.getSaleDetails().get(i)).getType().contentEquals("SB")) {
						int startYear = Integer.parseInt(s.getSaleDetails().get(i + 1).substring(0, 4));
						int endYear = Integer.parseInt(s.getSaleDetails().get(i + 2).substring(0, 4));
						int subscriptionLength = endYear - startYear;
						grandTotal += Double.parseDouble(idToItem.get(s.getSaleDetails().get(i)).getPrice())
								* subscriptionLength;
					} else {
						double hours = Double.parseDouble(s.getSaleDetails().get(i + 2));
						grandTotal += Double.parseDouble(idToItem.get(s.getSaleDetails().get(i)).getPrice()) * hours;
					}
				}
			}
		}
		return grandTotal;
	}
	
	public static void main(String[] args) {

		String personsFile = "data/Persons.csv";
		Map<String, Person> persons = DataConverterV2.loadPersonData(personsFile);
		String storesFile = "data/Stores.csv";
		List<Store> stores = DataConverterV2.loadStoreData(storesFile, persons);
		String itemsFile = "data/Items.csv";
		Map<String, Item> idToItem = DataConverterV2.loadItemData(itemsFile);
		String salesFile = "data/Sales.csv";
		List<Sale> sales = DataConverterV2.loadSaleData(salesFile);

		salespersonReport(persons, idToItem, sales);
		storeSalesReport(stores, idToItem, sales);
		detailedSalesReport(persons, idToItem, sales);
	}
	
}
