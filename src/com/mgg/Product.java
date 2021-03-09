package com.mgg;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models a product with a code, type, name, and basePrice and implements the Item class.
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
public class Product extends Item {
	
	public Product(String code, String type, String name, String basePrice) {
		super(code, type, name, basePrice);
	}
	
	public Product(String code, String type, String name) {
		super(code, type, name, "0.0");
	}

	@Override
	public List<String> getItemInfo() {
		List<String> itemInfo = new ArrayList<>();
		itemInfo.add(this.getCode());
		itemInfo.add(this.getPrice());
		return itemInfo;
	}
	
}
