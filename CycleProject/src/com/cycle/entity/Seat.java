package com.cycle.entity;

import java.util.HashMap;

/* 
* @author Dinesh Mani
*/
public class Seat {

	public static HashMap<String, Integer> seatTypes;
	public int price;

	public Seat() {
		if (seatTypes == null) {
			seatTypes = new HashMap<>();
			seatTypes.put("racing", 10);
			seatTypes.put("mountain", 20);
			seatTypes.put("gel-saddle", 30);
		}
	}

	public Seat(String seatType) {
		this();
		this.price = seatType != null && !seatType.isEmpty()
				? (seatTypes.get(seatType) != null ? seatTypes.get(seatType) : 0)
				: 0;
	}

	public Seat(String key, Integer value) {
		this();
		seatTypes.put(key, value);
	}

	public static HashMap<String, Integer> getSeatTypes() {
		return seatTypes;
	}

	public int getPrice() {
		return price;
	}

}
