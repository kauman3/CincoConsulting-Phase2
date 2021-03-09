package com.mgg;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models a Service with a code, type, name, and hourlyRate and implements the Item class.
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
public class Service extends Item {

	public Service(String code, String type, String name, String hourlyRate) {
		super(code, type, name, hourlyRate);
	}

	@Override
	public List<String> getItemInfo() {
		List<String> itemInfo = new ArrayList<>();
		itemInfo.add(this.getCode());
		//itemInfo.add(employeeCode);
		//itemInfo.add(numberOfHours);
		return itemInfo;
	}
	
}
