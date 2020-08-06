package com.cycle.entity;

import java.util.HashMap;

/* 
* @author Dinesh Mani
*/
public class Wheel {

	public static HashMap<String, Integer> wheelTypes;
	public int price;

	public Wheel() {
		if (wheelTypes == null) {
			wheelTypes = new HashMap<>();
			wheelTypes.put("spokes", 10);
			wheelTypes.put("rim", 20);
			wheelTypes.put("tube", 30);
			wheelTypes.put("tubeless", 40);
		}
	}

	public Wheel(String wheelType) {
		this();
		this.price = wheelType != null && !wheelType.isEmpty()
				? (wheelTypes.get(wheelType) != null ? wheelTypes.get(wheelType) : 0)
				: 0;
	}

	public Wheel(String key, Integer value) {
		this();
		wheelTypes.put(key, value);
	}

	public static HashMap<String, Integer> getWheelTypes() {
		return wheelTypes;
	}

	public int getPrice() {
		return price;
	}

}
