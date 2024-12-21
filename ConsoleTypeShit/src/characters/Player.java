package characters;

import java.util.*;

import main.GameLogic;

public class Player extends Character {
	
	private static final long serialVersionUID = 1;

	public int numAtkUps, numDefUps, gold;
	
	public String[] atkUps = {"Strength", "Power", "Might", "Godlike Speed"};
	public String[] defUps = {"Thickskin", "Stoneskin", "Ironbone", "Holy Aura"};
	
	public String armor, weapon, shield;
	public boolean armorAdded, weaponAdded, shieldAdded;
	
	public int place, act, location, saveCount;
	
	public Map<String, Integer> playerInv;
	public List<String> saves;
	public List<String> playerSkills;
	public List<String> playerTraits;

	public Player(String name) {
		super(name, "[PAWN]", 25, 25, 1, 0, 100, 1, 0);
		
		this.numAtkUps = 0;
		this.numDefUps = 0;
		this.gold = 50;
		
		this.place = 0;
		this.act = 0;
		this.location = 0;
		this.saveCount = 0;
		
		this.armorAdded = false;
		this.weaponAdded = false;
		this.shieldAdded = false;
		
		armor = "Wooden Armor";
		weapon = "Wooden Sword";
		shield = "Wooden Shield";
		
		playerInv = new HashMap<>();
		saves = new ArrayList<>();
		playerSkills = new ArrayList<>();
		playerTraits = new ArrayList<>(); 
		
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return atk;
	}

	@Override
	public int escape() {
		// TODO Auto-generated method stub
		return (int) (Math.random() * 100);
	}

	@Override
	public int defend() {
		// TODO Auto-generated method stub
		return (int) (Math.random() * 100);
	}
	
	public void chooseTrait() {
		GameLogic.clearConsole();
		GameLogic.printHeading("Choose a trait:");
		System.out.println("  [1] (Offensive) " + atkUps[numAtkUps]);
		System.out.println("  [2] (Defensive) " + defUps[numDefUps]);
		GameLogic.lineSeparator(35);
		int input = GameLogic.readInput("Pick a number.", 2);
		GameLogic.clearConsole();
		
		if (input == 1) {
			GameLogic.printHeading("You chose " + atkUps[numAtkUps] + "!");
			playerTraits.add(atkUps[numAtkUps]);
			numAtkUps++;
		} else {
			GameLogic.printHeading("You chose " + defUps[numDefUps] + "!");
			playerTraits.add(defUps[numDefUps]);
			numDefUps++;
		}
		GameLogic.pressAnything();
	}
	
	public void baseInventory() {
		playerInv.put("Low HP Potion", 3);
		playerInv.put("High ATK Potion", 3);
		
		GameLogic.clearConsole();
		GameLogic.printHeading("As a starting gift, You have gained: ");
		for (Map.Entry<String, Integer> entry : playerInv.entrySet()) {
			String key = entry.getKey();
			Integer val = entry.getValue();
			
			GameLogic.statement(key + " : " + val);
		}
		GameLogic.pressAnything();
	}
	
}
