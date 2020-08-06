package com.cycle.entity;

import java.util.HashMap;

/* 
* @author Dinesh Mani
*/
public class HandleBar {

	public static HashMap<String, Integer> handleTypes;
	public int price;

	public HandleBar() {
		if (handleTypes == null) {
			handleTypes = new HashMap<>();
			handleTypes.put("riser", 10);
			handleTypes.put("bull-horn", 20);
			handleTypes.put("aero", 30);
		}
	}

	public HandleBar(String handleType) {
		this();
		this.price = handleType != null && !handleType.isEmpty()
				? (handleTypes.get(handleType) != null ? handleTypes.get(handleType) : 0)
				: 0;
	}

	public HandleBar(String key, Integer value) {
		this();
		handleTypes.put(key, value);
	}

	public HashMap<String, Integer> getHandleTypes() {
		return handleTypes;
	}

	public int getPrice() {
		return price;
	}

}
