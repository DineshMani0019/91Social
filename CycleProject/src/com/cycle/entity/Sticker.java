package com.cycle.entity;

import java.util.HashMap;

/* 
* @author Dinesh Mani
*/
public class Sticker {

	public static HashMap<String, Integer> stickerTypes;
	public int price;

	public Sticker() {
		if (stickerTypes == null) {
			stickerTypes = new HashMap<>();
			stickerTypes.put("pokemon", 10);
			stickerTypes.put("marvel", 20);
			stickerTypes.put("disney", 20);
			stickerTypes.put("fluorescent", 40);
		}
	}

	public Sticker(String stickerType) {
		this();
		this.price = stickerType != null && !stickerType.isEmpty()
				? (stickerTypes.get(stickerType) != null ? stickerTypes.get(stickerType) : 0)
				: 0;
	}

	public Sticker(String key, Integer value) {
		this();
		stickerTypes.put(key, value);
	}

	public HashMap<String, Integer> getStickerTypes() {
		return stickerTypes;
	}

	public int getPrice() {
		return price;
	}

}
