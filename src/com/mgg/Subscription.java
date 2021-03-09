package com.mgg;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models a Subscription with a code, type, name, and annualFee and implements the Item class.
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
public class Subscription extends Item {
	
	public Subscription(String code, String type, String name, String annualFee) {
		super(code, type, name, annualFee);
	}

	@Override
	public List<String> getItemInfo() {
		List<String> itemInfo = new ArrayList<>();
		itemInfo.add(this.getCode());
		//itemInfo.add(beginDate);
		//itemInfo.add(endDate);
			//doing it like this would involve giving the Subscription class dates as well
		return itemInfo;
	}

}
