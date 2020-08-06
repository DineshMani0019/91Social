package com.cycle.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
public class ReadInput {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("***************** Welcome to Price Engine *****************\n");
		System.out.println(
				"1 - Calculate Price based on Specification\t 2 - Price Updates\t Q - Exit\nEnter your choice: ");
		Scanner sc = new Scanner(System.in);

		String option = "";
		while (!option.equalsIgnoreCase("1") || !option.equalsIgnoreCase("2")) {
			option = sc.next();
			if (option.equalsIgnoreCase("1")) {
				System.out.println("Reading specs from path: src/resources/input-file.json");
				calculatePrice();
				System.out.println(
						"1 - Calculate Price based on Specification\t 2 - Price Updates\t Q - Exit\nEnter your choice: ");
			} else if (option.equalsIgnoreCase("2")) {
				System.out.println("Reading Prices from path: src/resources/input-price-file.json");
				updatePrice();
				System.out.println(
						"1 - Calculate Price based on Specification\t 2 - Price Updates\t Q - Exit\nEnter your choice: ");
			} else if (option.equalsIgnoreCase("q")) {
				System.out.println("Exiting Application !!\n");
				break;
			} else {
				System.out.println("Invalid choice. Try again !!\n");
			}
		}
		sc.close();

	}

	public static void calculatePrice() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("src/resources/input-file.json")) {
			Object cycleObj = jsonParser.parse(reader);
			JSONArray cycleInputList = (JSONArray) cycleObj;
			ExecutorService service = Executors.newFixedThreadPool(10);
			for (int i = 0; i < cycleInputList.size(); i++) {
				service.submit(new PriceEngine((JSONObject) cycleInputList.get(i)));
			}
			service.shutdown();
			while (!service.isTerminated()) {
			}
			writeOutputToFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void writeOutputToFile() {
		try {
			FileWriter file = new FileWriter("src/resources/output-file.json");
			file.write(PriceEngine.cycleOutputList.toJSONString());
			file.close();
			PriceEngine.cycleOutputList.clear();
			System.out.println(
					"Completed Price Calculation.\nOutput generated in path: src/resources/output-file.json\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updatePrice() {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("src/resources/input-price-file.json")) {
			Object specObj = jsonParser.parse(reader);
			JSONArray specInputList = (JSONArray) specObj;
			for (int i = 0; i < specInputList.size(); i++) {
				JSONObject obj = (JSONObject) specInputList.get(i);
				try {
					if (obj.get("spec").toString().equalsIgnoreCase("frame")) {
						Frame frame = new Frame((String) obj.get("key"), Integer.valueOf(obj.get("value").toString()));
					} else if (obj.get("spec").toString().equalsIgnoreCase("handlebar")) {
						HandleBar handleBar = new HandleBar((String) obj.get("key"),
								Integer.valueOf(obj.get("value").toString()));
					} else if (obj.get("spec").toString().equalsIgnoreCase("mudguard")) {
						Mudguard mudGuard = new Mudguard((String) obj.get("key"),
								Integer.valueOf(obj.get("value").toString()));
					} else if (obj.get("spec").toString().equalsIgnoreCase("seat")) {
						Seat seat = new Seat((String) obj.get("key"), Integer.valueOf(obj.get("value").toString()));
					} else if (obj.get("spec").toString().equalsIgnoreCase("sticker")) {
						Sticker sticker = new Sticker((String) obj.get("key"),
								Integer.valueOf(obj.get("value").toString()));
					} else if (obj.get("spec").toString().equalsIgnoreCase("wheel")) {
						Wheel wheel = new Wheel((String) obj.get("key"), Integer.valueOf(obj.get("value").toString()));
					} else if (obj.get("spec").toString().equalsIgnoreCase("chain-assembly")) {
						ChainAssembly chainAssembly = new ChainAssembly((String) obj.get("key"),
								Integer.valueOf(obj.get("value").toString()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Prices Updated Successfully !!\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
