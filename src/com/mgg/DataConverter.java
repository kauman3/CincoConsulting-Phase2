package com.mgg;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
	
	//can be changed later to seperate differnt customer types as well
	public static List<Employee> getEmployeeList (List<Person> persons) {
		List<Employee> employees = new ArrayList<>();
			
		for(Person p : persons) {
			if(p.getType().contentEquals("E")) {
				employees.add((Employee) p);
			}
		}
		return employees;
	}

    public static void main(String[] args) {

        String personsFile = "data/Persons.csv";
        List<Person> persons = LoadCSVData.loadPersonData(personsFile);
        String storesFile = "data/Stores.csv";
        List<Store> stores = LoadCSVData.loadStoreData(storesFile, persons);
        String itemsFile = "data/Items.csv";
        List<Item> items = LoadCSVData.loadItemData(itemsFile);
        String salesFile = "data/Sales.csv";
        List<Sale> sales = LoadCSVData.loadSaleData(salesFile, persons, stores, items);
        
        PrintXML.personsToXML(persons);
        PrintXML.storesToXML(stores);
        PrintXML.itemsToXML(items);
        
        //TODO: produce reports of sales
        //Sale.employeeSalesReport(sales);
    }
    
}
