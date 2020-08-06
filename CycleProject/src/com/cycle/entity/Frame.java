package com.cycle.entity;

import java.util.HashMap;

/* 
* @author Dinesh Mani
*/
public class Frame {

	public static HashMap<String, Integer> materialTypes;
	public int price;

	public Frame() {
		if (materialTypes == null) {
			materialTypes = new HashMap<>();
			materialTypes.put("aluminium", 10);
			materialTypes.put("carbon", 20);
			materialTypes.put("steel", 30);
		}
		this.price = materialTypes.get("aluminium");
	}

	public Frame(String materialType) {
		this();
		this.price = materialType != null && !materialType.isEmpty()
				? (materialTypes.get(materialType) != null ? materialTypes.get(materialType) : 0)
				: 0;
	}

	public Frame(String key, Integer value) {
		this();
		materialTypes.put(key, value);
	}

	public HashMap<String, Integer> getMaterialTypes() {
		return materialTypes;
	}

	public int getPrice() {
		return price;
	}

}
