package com.mgg;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This is the main driver program that loads CSV files containing person, store, and item data, 
 * creates and ArrayList for each respective data type, and uses these ArrayLists to output XML 
 * files containing the data from the original CSV input files.
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
public class DataConverter {
	
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
    public static Map<String, Item> loadItemData(String file) {
    	
    	Map<String, Item> itemsMap = new HashMap<>();
        try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i = 0; i<n; i++) {
                String line = s.nextLine();
                String tokens[] = line.split(",");
                if(tokens.length < 4) {
                	itemsMap.put(tokens[0], new Item(tokens[1], tokens[2]));
                } else {
                	itemsMap.put(tokens[0], new Item(tokens[1], tokens[2], tokens[3]));
                }
            }
            
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
        return itemsMap;
    }
    
    /**
     * Takes a CSV file with Sales data and stores it in an ArrayList of Sales
     * @param file
     * @return
     */
    public static List<Sale> loadSaleData(String file, List<Person> persons, List <Store> stores, Map<String, Item> itemsMap) {
    	
    	List<Sale> sales = new ArrayList<>();
    	try {
            Scanner s = new Scanner(new File(file));
            String firstLine = s.nextLine();
            String token[] = firstLine.split(",");
            int n = Integer.parseInt(token[0]);
            
            for(int i=0; i<n; i++) {
            	String line = s.nextLine();
                String tokens[] = line.split(",");
                List<Item> items = new ArrayList<>();
                List<String> itemsSold = new ArrayList<>();
                String str1 = null;
//                String str2 = "";
                
                for(int j=4; j<tokens.length; j++) {
                	if(itemsMap.containsKey(tokens[j])) {
                		items.add(itemsMap.get(tokens[j]));
                		for(int k=4; k<tokens.length; k++) {
                			str1 = tokens[k];
                			itemsSold.add(str1);
                		}
//                		str1 = tokens[j+1];
//                		if(j+2<tokens.length && !(itemsMap.containsKey(tokens[j+2]))) {
//                			str2 = tokens[j+2];
//                		}
                	}
                }
//                if(str2.isEmpty()) {
//                	sales.add(new Sale(tokens[0], tokens[1], tokens[2], tokens[3], items, str1));
//                } else {
//                	sales.add(new Sale(tokens[0], tokens[1], tokens[2], tokens[3], items, str1, str2));
//                }
                sales.add(new Sale(tokens[0], tokens[1], tokens[2], tokens[3], items, itemsSold));
            }
            s.close();
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
    	return sales;
    }

    public static void main(String[] args) {

        String personsFile = "data/Persons.csv";
        List<Person> persons = loadPersonData(personsFile);
        String storesFile = "data/Stores.csv";
        List<Store> stores = loadStoreData(storesFile, persons);
        String itemsFile = "data/Items.csv";
        Map<String, Item> itemsMap = loadItemData(itemsFile);
        String salesFile = "data/Sales.csv";
        List<Sale> sales = loadSaleData(salesFile, persons, stores, itemsMap);
        
        PrintXML.personsToXML(persons);
        PrintXML.storesToXML(stores);
        PrintXML.itemsToXML(itemsMap);
        
        //TODO: produce reports of sales
        //Sale.employeeSalesReport(sales);
    }
    
}
