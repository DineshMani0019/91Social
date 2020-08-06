package com.cycle.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cycle.designpattern.CycleBuilder;
import com.cycle.entity.ChainAssembly;
import com.cycle.entity.Frame;
import com.cycle.entity.HandleBar;
import com.cycle.entity.Mudguard;
import com.cycle.entity.Seat;
import com.cycle.entity.Sticker;
import com.cycle.entity.Wheel;

/* 
* @author Dinesh Mani
*/
public class PriceEngine extends Thread {

	JSONObject input;
	static JSONArray cycleOutputList = new JSONArray();

	public PriceEngine(JSONObject input) {
		this.input = input;
	}

	public void run() {
		parseCycleObject(input);
	}

	@SuppressWarnings("unchecked")
	private static void parseCycleObject(JSONObject cycle) {
		JSONObject cycleObj = (JSONObject) cycle.get("cycle");
		Frame frame = new Frame((String) cycleObj.get("frame"));
		HandleBar handlebar = new HandleBar((String) cycleObj.get("handlebar"));
		Sticker sticker = new Sticker((String) cycleObj.get("sticker"));
		Mudguard mudguard = new Mudguard((String) cycleObj.get("mudguard"));
		Wheel wheel = new Wheel((String) cycleObj.get("wheel"));
		Seat seat = new Seat((String) cycleObj.get("seat"));
		ChainAssembly chainAssembly = new ChainAssembly((String) cycleObj.get("chain-assembly"));

		CycleBuilder cycleBuilder = new CycleBuilder(frame, chainAssembly, handlebar, mudguard, seat, sticker, wheel);

		JSONObject cycleOutputObj = new JSONObject();
		if (cycleObj.get("frame") != null)
			cycleOutputObj.put(
					cycleObj.get("frame") + (cycleObj.get("frame").toString().isEmpty() ? "" : "-") + "frame",
					cycleBuilder.getFrameObj().price);
		if (cycleObj.get("handlebar") != null)
			cycleOutputObj.put(cycleObj.get("handlebar") + (cycleObj.get("handlebar").toString().isEmpty() ? "" : "-")
					+ "handlebar", cycleBuilder.getHandleBarObj().price);
		if (cycleObj.get("sticker") != null)
			cycleOutputObj.put(
					cycleObj.get("sticker") + (cycleObj.get("sticker").toString().isEmpty() ? "" : "-") + "sticker",
					cycleBuilder.getStickerObj().price);
		if (cycleObj.get("mudguard") != null)
			cycleOutputObj.put(
					cycleObj.get("mudguard") + (cycleObj.get("mudguard").toString().isEmpty() ? "" : "-") + "mudguard",
					cycleBuilder.getMudguardObj().price);
		if (cycleObj.get("wheel") != null)
			cycleOutputObj.put(
					cycleObj.get("wheel") + (cycleObj.get("wheel").toString().isEmpty() ? "" : "-") + "wheel",
					cycleBuilder.getWheelObj().price);
		if (cycleObj.get("seat") != null)
			cycleOutputObj.put(cycleObj.get("seat") + (cycleObj.get("seat").toString().isEmpty() ? "" : "-") + "seat",
					cycleBuilder.getSeatingObj().price);
		if (cycleObj.get("chain-assembly") != null)
			cycleOutputObj.put(cycleObj.get("chain-assembly")
					+ (cycleObj.get("chain-assembly").toString().isEmpty() ? "" : "-") + "chain-assembly",
					cycleBuilder.getChainAssemblyObj().price);
		cycleOutputObj.put("TOTAL-PRICE", cycleBuilder.getCyclePrice());

		addOutput(cycleOutputObj);

	}

	@SuppressWarnings("unchecked")
	public static synchronized void addOutput(JSONObject cycleOutputObj) {
		cycleOutputList.add(cycleOutputObj);
	}

}
