package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class loads CSV files containing person, store, and item data, 
 * creates and ArrayList for each respective data type.
 * 
 * @author kauman<br \>
 * Kyle Auman<br \>
 * kauman3@huskers.unl.edu<br \>
 * CSCE156<br \><br \>
 * @author zmain<br \>
 * Zach Main<br \>
 * zmain2@huskers.unl.edu<br \>
 * CSCE156<br \>
 */
public class LoadCSVData {
	
	/**
	 * Takes a CSV file with Person data and stores it in an ArrayList of Persons
	 * @param file
	 * @return
	 */
    public static List<Person> loadPersonData(String file) {

        List<Person> persons = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i=0; i<n; i++) {
                String line = s.nextLine();
                String tokens[] = line.split(",");
                Address address = new Address(tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]);
                if(tokens.length <= 9) {
                    persons.add(new Person(tokens[0], tokens[1], tokens[2], tokens[3], address));
                } else { 
                	List<String> emailList = new ArrayList<>();
                    for(int j=9; j<tokens.length; j++) {
                        emailList.add(tokens[j]);
                    }
                    persons.add(new Person(tokens[0], tokens[1], tokens[2], tokens[3], address, emailList));
                }
            }
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
        return persons;
    }
    
    /**
     * Takes a CSV file with Store data and an ArrayList of Persons and stores it in an ArrayList of Stores
     * @param file
     * @return
     */
    public static List<Store> loadStoreData(String file, List<Person> persons) {
    	
    	List<Store> stores = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i =0; i<n; i++) {
                String line = s.nextLine();
                String tokens[] = line.split(",");
                Address address = new Address(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                for(Person p : persons) {
                	if(tokens[1].contentEquals(p.getPersonCode())) {
                		stores.add(new Store(tokens[0], p, address));
                	}
                }
            }
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
        return stores;
    }
    
    /**
     * Takes a CSV file with Item data and stores it in an ArrayList of Items
     * @param file
     * @return
     */
    public static List<Item> loadItemData(String file) {
    	
    	List<Item> items = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i =0; i<n; i++) {
                String line = s.nextLine();
                String tokens[] = line.split(",");
                if(tokens[1] == "SV") {
                    items.add(new Service(tokens[0], tokens[1], tokens[2], tokens[3]));
                } else if(tokens[1] == "SB") {
                	items.add(new Subscription(tokens[0], tokens[1], tokens[2], tokens[3]));
                } else if(tokens.length < 4) {
                	items.add(new Product(tokens[0], tokens[1], tokens[2]));
                } else {
                	items.add(new Product(tokens[0], tokens[1], tokens[2], tokens[3]));
                }
            }
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
        return items;
    }
    
    /**
     * Takes a CSV file with Sales data and stores it in an ArrayList of Sales
     * @param file
     * @return
     */
    public static List<Sale> loadSaleData(String file, List<Person> persons, List <Store> stores, List<Item> items) {
    	
    	List<Sale> sales = new ArrayList<>();
    	try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i=0; i<n; i++) {
            	String line = s.nextLine();
                String tokens[] = line.split(",");
                Employee customer = new Employee();
                Customer salesperson = new Customer();
            	for(Person p : persons) {
            		if(p.getPersonCode().contentEquals(tokens[2])) {
            			customer.setPersonValues(customer, p);
            		}
            		if(p.getPersonCode().contentEquals(tokens[3])) {
            			salesperson.setPersonValues(customer, p);
            			//salesperson.setNumSales(salesperson.getNumSales() + 1);
            			//salesperson.setSalesGrandTotal(salesperson.getSalesGrandTotal() + item.getPrice());
            		}
            	}
        		sales.add(new Sale(tokens[0], tokens[1], customer, salesperson));
//            	List<Item> salesItems = new ArrayList<>();
//                String line = s.nextLine();
//                String tokens[] = line.split(",");
//                for(Item item : items) {
//                	if(line.length() > 7 && item.getCode().contentEquals(tokens[4])) {
//                		salesItems.add(item);
//                		sales.add(new Sale(tokens[0], tokens[1], tokens[2], tokens[3], salesItems));
//                	}
//                }
//                //sales.add(new Sale(tokens[0], tokens[1], tokens[2], tokens[3]));
            }
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
    	return sales;
    }
	
}
