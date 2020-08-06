package com.cycle.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.cycle.designpattern.CycleBuilder;
import com.cycle.entity.ChainAssembly;
import com.cycle.entity.Frame;
import com.cycle.entity.HandleBar;
import com.cycle.entity.Mudguard;
import com.cycle.entity.Seat;
import com.cycle.entity.Sticker;
import com.cycle.entity.Wheel;

public class PriceEngineTest {
	
	static JSONArray cycleOutputList;
	@Test
	public void sampleTest() throws JSONException {
		String expected = "{\r\n" + 
				"		\"carbon-frame\": 20,\r\n" + 
				"		\"marvel-sticker\": 20,\r\n" + 
				"		\"aero-handlebar\": 30,\r\n" + 
				"		\"micro-mudguard\": 30,\r\n" + 
				"		\"TOTAL-PRICE\": 160,\r\n" + 
				"		\"4-chain-assembly\": 10,\r\n" + 
				"		\"racing-seat\": 10,\r\n" + 
				"		\"tubeless-wheel\": 40\r\n" + 
				"	}";
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader("src/resources/input-file.json")) {
			Object cycleObj = jsonParser.parse(reader);
			JSONArray cycleInputList = (JSONArray) cycleObj;
			 cycleOutputList = new JSONArray();
			for (int i = 0; i < cycleInputList.size(); i++) {
				parseCycleObject((JSONObject) cycleInputList.get(i), cycleOutputList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String actual = cycleOutputList.get(0).toString();
		JSONAssert.assertEquals(expected,actual, true);
	}
	
	@SuppressWarnings("unchecked")
	private static void parseCycleObject(JSONObject cycle, JSONArray cycleOutputList) {
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

		cycleOutputList.add(cycleOutputObj);
	}

}
