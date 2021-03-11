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
public abstract class Item {
	
	private String code;
	private String type;
	private String name;
	private String price;
	
	public Item(String code, String type, String name, String price) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.price = price;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
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
	
	/**
	 * TODO: documentation
	 * @return
	 */
	public abstract List<String> getItemInfo();
}
