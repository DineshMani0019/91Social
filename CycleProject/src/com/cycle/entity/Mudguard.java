package com.cycle.entity;

import java.util.HashMap;

/* 
* @author Dinesh Mani
*/
public class Mudguard {

	public static HashMap<String, Integer> guardTypes;
	public int price;

	public Mudguard() {
		if (guardTypes == null) {
			guardTypes = new HashMap<>();
			guardTypes.put("clip-on", 10);
			guardTypes.put("full-length", 20);
			guardTypes.put("micro", 30);
			guardTypes.put("fixed", 40);
		}
	}

	public Mudguard(String guardType) {
		this();
		this.price = guardType != null && !guardType.isEmpty()
				? (guardTypes.get(guardType) != null ? guardTypes.get(guardType) : 0)
				: 0;
	}

	public Mudguard(String key, Integer value) {
		this();
		guardTypes.put(key, value);
	}

	public HashMap<String, Integer> getGuardTypes() {
		return guardTypes;
	}

	public int getPrice() {
		return price;
	}

}
