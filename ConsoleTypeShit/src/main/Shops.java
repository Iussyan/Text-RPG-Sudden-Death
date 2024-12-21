package main;

import java.util.*;

import characters.Player;

public class Shops {
	public static Map<String, Integer> shop = new HashMap<>();;
	
	public static void potionShop(Player player) {
		
		switch (player.act) {
		case 0:
			
			shop.clear();
			
			shop.put("Lower EXP Potion", 60);
			shop.put("Low HP Potion", 50);
			shop.put("Medium HP Potion", 75);
			
			break;
			
		case 1:
			
			shop.clear();
			
			shop.put("Lower EXP Potion", 70);
			shop.put("Low EXP Potion", 95);
			shop.put("Low HP Potion", 55);
			shop.put("Medium HP Potion", 80);
			shop.put("High HP Potion", 105);
			shop.put("Low ATK Potion", 60);
			shop.put("Medium ATK Potion", 85);			
			
			break;
			
		case 2:
			
			shop.clear();
			
			shop.put("Lower EXP Potion", 80);
			shop.put("Low EXP Potion", 105);
			shop.put("Medium EXP Potion", 120);
			shop.put("Low HP Potion", 60);
			shop.put("Medium HP Potion", 85);
			shop.put("High HP Potion", 110);
			shop.put("Low ATK Potion", 65);
			shop.put("Medium ATK Potion", 90);
			shop.put("High ATK Potion", 115);
			shop.put("Low DEF Potion", 70);
			
			break;
			
		case 3:
			
			shop.clear();
			
			shop.put("Lower EXP Potion", 100);
			shop.put("Low EXP Potion", 120);
			shop.put("Medium EXP Potion", 130);
			shop.put("High EXP Potion", 150);
			shop.put("Low HP Potion", 75);
			shop.put("Medium HP Potion", 90);
			shop.put("High HP Potion", 115);
			shop.put("Low ATK Potion", 80);
			shop.put("Medium ATK Potion", 95);
			shop.put("High ATK Potion", 120);
			shop.put("Low DEF Potion", 85);
			shop.put("Medium DEF Potion", 100);
			shop.put("High DEF Potion", 125);
			
			break;
			
		}
	}
}
