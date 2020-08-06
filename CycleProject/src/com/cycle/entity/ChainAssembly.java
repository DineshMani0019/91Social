package com.cycle.entity;

import java.util.HashMap;

/* 
* @author Dinesh Mani
*/
public class ChainAssembly {

	public static HashMap<String, Integer> gearTypes;
	public int price;

	public ChainAssembly() {
		if (gearTypes == null) {
			gearTypes = new HashMap<>();
			gearTypes.put("4", 10);
			gearTypes.put("5", 20);
			gearTypes.put("6", 30);
		}
	}

	public ChainAssembly(String gearType) {
		this();
		this.price = gearType != null && !gearType.isEmpty()
				? (gearTypes.get(gearType) != null ? gearTypes.get(gearType) : 0)
				: 0;
	}

	public ChainAssembly(String key, Integer value) {
		this();
		gearTypes.put(key, value);
	}

	public static HashMap<String, Integer> getGearTypes() {
		return gearTypes;
	}

	public int getPrice() {
		return price;
	}

}
