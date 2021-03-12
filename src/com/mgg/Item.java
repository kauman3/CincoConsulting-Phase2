package com.mgg;

import java.util.List;

/**
 * This abstract class models a sales item with a code, type, name, and basePrice. Items can be
 * Products, Services, or Subscriptions.
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
public class Item {
	
	//private String code;
	private String type;
	private String name;
	private String price;
	
	public Item(String type, String name, String price) {
		//this.code = code;
		this.type = type;
		this.name = name;
		this.price = price;
	}
	
	public Item(String type, String name) {
		//this.code = code;
		this.type = type;
		this.name = name;
		this.price = "";
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the name
	 */
	public String getPrice() {
		return this.price;
	}
	
}
