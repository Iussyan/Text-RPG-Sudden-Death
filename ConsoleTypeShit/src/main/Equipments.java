package main;

import characters.Player;

public class Equipments {
	
	public static String[] armors = { "Wooden Armor", "Copper Armor", "Iron Armor", "Gold Armor", "Amethyst Armor",
			"Emerald Armor", "Sapphire Armor", "Ruby Armor", "Jade Armor", "Diamond Armor" };

	public static String[] weapons = { "Wooden Sword", "Copper Sword", "Iron Sword", "Gold Sword", "Amethyst Sword",
			"Emerald Sword", "Sapphire Sword", "Ruby Sword", "Jade Sword", "Diamond Sword" };

	public static String[] shields = { "Wooden Shield", "Copper Shield", "Iron Shield", "Gold Shield", "Amethyst Shield",
			"Emerald Shield", "Sapphire Shield", "Ruby Shield", "Jade Shield", "Diamond Shield" };

	public static String[] armorsDesc = { "Rudimentary wooden armor, offering basic protection for novice adventurers.",
			"Basic armor made of copper, offering modest protection in the heat of battle.",
			"Solid iron armor, providing reliable defense against a variety of attacks.",
			"Shimmering gold armor, a symbol of wealth and power on the battlefield.",
			"Enigmatic amethyst armor, known for its mystical properties and protective enchantments.",
			"Regal emerald armor, crafted with nature's blessings for enhanced resilience.",
			"Icy sapphire armor, offering frosty protection against elemental threats.",
			"Fiery ruby armor, emanating heat and strength to withstand intense combat.",
			"Earthen jade armor, blending with nature to provide sturdy defense in all terrains.",
			"Dazzling diamond armor, reflecting purity and invincibility in the face of danger." };

	public static String[] weaponsDesc = { "Simple wooden sword, a basic blade for early encounters in the wild.",
			"Rudimentary copper sword, a beginner's blade for early skirmishes.",
			"Tough iron sword, a reliable weapon favored by seasoned warriors.",
			"Gleaming gold sword, a majestic blade exuding power and prestige in battle.",
			"Enchanted amethyst sword, infused with magical energies for potent strikes.",
			"Nature-infused emerald sword, channeling the earth's vitality for swift attacks.",
			"Frosty sapphire sword, chilling enemies with icy precision and frosty slashes.",
			"Blazing ruby sword, engulfed in flames to scorch adversaries with fiery strikes.",
			"Harmonious jade sword, a balanced weapon embodying tranquility and strength.",
			"Exquisite diamond sword, a gleaming blade of unmatched brilliance and cutting edge." };

	public static String[] shieldsDesc = {
			"Simple wooden shield, a basic defense against early threats in the wilderness.",
			"Simple copper shield, a basic defense against incoming threats.",
			"Sturdy iron shield, a dependable barrier shielding warriors from harm.",
			"Luxurious gold shield, a symbol of opulence and impregnable defense on the battlefield.",
			"Enchanted amethyst shield, a mystical ward offering magical protection against dark forces.",
			"Nature's shield, the emerald shield, harmonizing with the earth for natural defense.",
			"Frosty sapphire shield, warding off icy attacks with a chilling shield of protection.",
			"Blazing ruby shield, ablaze with protective fire to repel enemies with fiery defense.",
			"Balanced jade shield, a shield of equilibrium, combining strength and serenity in defense.",
			"Pure diamond shield, a shield of unbreakable purity, reflecting the pristine essence of protection." };
	
	public static int[] armorAttributes = {
	        3, 5, 10, 15, 20, 25, 30, 35, 40, 45
	    };

	public static int[] weaponAttributes = {
	        3, 5, 10, 15, 20, 25, 30, 35, 40, 45
	    };

	public static int[] shieldAttributes = {
	        1, 3, 5, 7, 9, 11, 13, 15, 17, 19
	    };
	
	public static void equipments(Player player) {
		do {
			GameLogic.clearConsole();
			GameLogic.printHeading("Equipments:");
			GameLogic.statement("[1] Armor  : " + player.armor 
					      + "\n  [2] Weapon : " + player.weapon
				       	  + "\n  [3] Shield : " + player.shield 
					      + "\n  [4] Go Back \n");
			int input = GameLogic.readInput("Pick a number to show its detailed info.", 4);
			if (input == 1) {
				armorInfo(player);
			} else if (input == 2) {
				weaponInfo(player);
			} else if (input == 3) {
				shieldInfo(player);
			} else {
				break;
			}
		} while (true);
	}
	
	public static void armorInfo(Player player) {
		GameLogic.clearConsole();
		if (player.armor.equals(armors[0])) {
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[0]
						+ "\n\n  Attribute   : +" + armorAttributes[0] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[0]) + " +" + armorAttributes[0]);
			GameLogic.pressAnything();
			
		} else if (player.armor.equals(armors[1])) {
			if (player.armorAdded) {
				player.maxHp += armorAttributes[1];
				player.armorAdded = false;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[1]
						+ "\n\n  Attribute   : +" + armorAttributes[1] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[1]) + " +" + armorAttributes[1]);
			GameLogic.pressAnything();
		} else if (player.armor.equals(armors[2])) {
			if (!player.armorAdded) {
				player.maxHp += armorAttributes[2];
				player.armorAdded = true;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[2]
						+ "\n\n  Attribute   : +" + armorAttributes[2] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[2]) + " +" + armorAttributes[2]);
			GameLogic.pressAnything();
			
		} else if (player.armor.equals(armors[3])) {
			if (player.armorAdded) {
				player.maxHp += armorAttributes[3];
				player.armorAdded = false;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[3]
						+ "\n\n  Attribute   : +" + armorAttributes[3] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[3]) + " +" + armorAttributes[3]);
			GameLogic.pressAnything();
		} else if (player.armor.equals(armors[4])) {
			if (!player.armorAdded) {
				player.maxHp += armorAttributes[4];
				player.armorAdded = true;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[4]
						+ "\n\n  Attribute   : +" + armorAttributes[4] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[4]) + " +" + armorAttributes[4]);
			GameLogic.pressAnything();
			
		} else if (player.armor.equals(armors[5])) {
			if (player.armorAdded) {
				player.maxHp += armorAttributes[5];
				player.armorAdded = false;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[5]
						+ "\n\n  Attribute   : +" + armorAttributes[5] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[5]) + " +" + armorAttributes[5]);
			GameLogic.pressAnything();
		} else if (player.armor.equals(armors[6])) {
			if (!player.armorAdded) {
				player.maxHp += armorAttributes[6];
				player.armorAdded = true;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[6]
						+ "\n\n  Attribute   : +" + armorAttributes[6] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[6]) + " +" + armorAttributes[6]);
			GameLogic.pressAnything();
			
		} else if (player.armor.equals(armors[7])) {
			if (player.armorAdded) {
				player.maxHp += armorAttributes[7];
				player.armorAdded = false;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[7]
						+ "\n\n  Attribute   : +" + armorAttributes[7] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[7]) + " +" + armorAttributes[7]);
			GameLogic.pressAnything();
		} else if (player.armor.equals(armors[8])) {
			if (!player.armorAdded) {
				player.maxHp += armorAttributes[8];
				player.armorAdded = true;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[8]
						+ "\n\n  Attribute   : +" + armorAttributes[8] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[8]) + " +" + armorAttributes[8]);
			GameLogic.pressAnything();
			
		} else if (player.armor.equals(armors[9])){
			if (player.armorAdded) {
				player.maxHp += armorAttributes[9];
				player.armorAdded = false;
			}
			GameLogic.printHeading("Armor Information: ");
			GameLogic.statement("Armor       : " + player.armor
						  + "\n  Description : " + armorsDesc[9]
						+ "\n\n  Attribute   : +" + armorAttributes[9] + " MAX HP"
						  + "\n  Max HP      : " + (player.maxHp - armorAttributes[9]) + " +" + armorAttributes[9]);
			GameLogic.pressAnything();
		} 
	}
	
	public static void weaponInfo(Player player) {
		GameLogic.clearConsole();
		if (player.weapon.equals(weapons[0])) {
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[0]
						+ "\n\n  Attribute   : +" + weaponAttributes[0] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[0]) + " +" + weaponAttributes[0]);
			GameLogic.pressAnything();
			
		} else if (player.weapon.equals(weapons[1])) {
			if (player.weaponAdded) {
				player.atk += weaponAttributes[1];
				player.weaponAdded = false;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[1]
						+ "\n\n  Attribute   : +" + weaponAttributes[1] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[1]) + " +" + weaponAttributes[1]);
			GameLogic.pressAnything();
		} else if (player.weapon.equals(weapons[2])) {
			if (!player.weaponAdded) {
				player.atk += weaponAttributes[2];
				player.weaponAdded = true;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[2]
						+ "\n\n  Attribute   : +" + weaponAttributes[2] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[2]) + " +" + weaponAttributes[2]);
			GameLogic.pressAnything();
			
		} else if (player.weapon.equals(weapons[3])) {
			if (player.weaponAdded) {
				player.atk += weaponAttributes[3];
				player.weaponAdded = false;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[3]
						+ "\n\n  Attribute   : +" + weaponAttributes[3] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[3]) + " +" + weaponAttributes[3]);
			GameLogic.pressAnything();
		} else if (player.weapon.equals(weapons[4])) {
			if (!player.weaponAdded) {
				player.atk += weaponAttributes[4];
				player.weaponAdded = true;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[4]
						+ "\n\n  Attribute   : +" + weaponAttributes[4] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[4]) + " +" + weaponAttributes[4]);
			GameLogic.pressAnything();
			
		} else if (player.weapon.equals(weapons[5])) {
			if (player.weaponAdded) {
				player.atk += weaponAttributes[5];
				player.weaponAdded = false;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[5]
						+ "\n\n  Attribute   : +" + weaponAttributes[5] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[5]) + " +" + weaponAttributes[5]);
			GameLogic.pressAnything();
		} else if (player.weapon.equals(weapons[6])) {
			if (!player.weaponAdded) {
				player.atk += weaponAttributes[6];
				player.weaponAdded = true;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[6]
						+ "\n\n  Attribute   : +" + weaponAttributes[6] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[6]) + " +" + weaponAttributes[6]);
			GameLogic.pressAnything();
			
		} else if (player.weapon.equals(weapons[7])) {
			if (player.weaponAdded) {
				player.atk += weaponAttributes[7];
				player.weaponAdded = false;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[7]
						+ "\n\n  Attribute   : +" + weaponAttributes[7] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[7]) + " +" + weaponAttributes[7]);
			GameLogic.pressAnything();
		} else if (player.weapon.equals(weapons[8])) {
			if (!player.weaponAdded) {
				player.atk += weaponAttributes[8];
				player.weaponAdded = true;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[8]
						+ "\n\n  Attribute   : +" + weaponAttributes[8] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[8]) + " +" + weaponAttributes[8]);
			GameLogic.pressAnything();
			
		} else if (player.weapon.equals(weapons[9])){
			if (player.weaponAdded) {
				player.atk += weaponAttributes[9];
				player.weaponAdded = false;
			}
			GameLogic.printHeading("Weapon Information: ");
			GameLogic.statement("Weapon      : " + player.weapon
						  + "\n  Description : " + weaponsDesc[9]
						+ "\n\n  Attribute   : +" + weaponAttributes[9] + " ATTACK"
						  + "\n  Attack      : " + (player.atk - weaponAttributes[9]) + " +" + weaponAttributes[9]);
			GameLogic.pressAnything();
		} 
	}
	
	public static void shieldInfo(Player player) {
	    GameLogic.clearConsole();
	    if (player.shield.equals(shields[0])) {
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[0]
	                    + "\n\n  Attribute   : +" + shieldAttributes[0] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[0]) + " +" + shieldAttributes[0]);
	        GameLogic.pressAnything();    
	    } else if (player.shield.equals(shields[1])) {
	        if (player.shieldAdded) {
	            player.def += shieldAttributes[1];
	            player.shieldAdded = false;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[1]
	                    + "\n\n  Attribute   : +" + shieldAttributes[1] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[1]) + " +" + shieldAttributes[1]);
	        GameLogic.pressAnything();
	    } else if (player.shield.equals(shields[2])) {
	        if (!player.shieldAdded) {
	            player.def += shieldAttributes[2];
	            player.shieldAdded = true;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[2]
	                    + "\n\n  Attribute   : +" + shieldAttributes[2] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[2]) + " +" + shieldAttributes[2]);
	        GameLogic.pressAnything();
	    } else if (player.shield.equals(shields[3])) {
	        if (player.shieldAdded) {
	            player.def += shieldAttributes[3];
	            player.shieldAdded = false;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[3]
	                    + "\n\n  Attribute   : +" + shieldAttributes[3] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[3]) + " +" + shieldAttributes[3]);
	        GameLogic.pressAnything();
	    } else if (player.shield.equals(shields[4])) {
	        if (!player.shieldAdded) {
	            player.def += shieldAttributes[4];
	            player.shieldAdded = true;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[4]
	                    + "\n\n  Attribute   : +" + shieldAttributes[4] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[4]) + " +" + shieldAttributes[4]);
	        GameLogic.pressAnything();
	    } else if (player.shield.equals(shields[5])) {
	        if (player.shieldAdded) {
	            player.def += shieldAttributes[5];
	            player.shieldAdded = false;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[5]
	                    + "\n\n  Attribute   : +" + shieldAttributes[5] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[5]) + " +" + shieldAttributes[5]);
	        GameLogic.pressAnything();
	    } else if (player.shield.equals(shields[6])) {
	        if (!player.shieldAdded) {
	            player.def += shieldAttributes[6];
	            player.shieldAdded = true;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[6]
	                    + "\n\n  Attribute   : +" + shieldAttributes[6] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[6]) + " +" + shieldAttributes[6]);
	        GameLogic.pressAnything();
	    } else if (player.shield.equals(shields[7])) {
	        if (player.shieldAdded) {
	            player.def += shieldAttributes[7];
	            player.shieldAdded = false;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[7]
	                    + "\n\n  Attribute   : +" + shieldAttributes[7] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[7]) + " +" + shieldAttributes[7]);
	        GameLogic.pressAnything();
	    } else if (player.shield.equals(shields[8])) {
	        if (!player.shieldAdded) {
	            player.def += shieldAttributes[8];
	            player.shieldAdded = true;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[8]
	                    + "\n\n  Attribute   : +" + shieldAttributes[8] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[8]) + " +" + shieldAttributes[8]);
	        GameLogic.pressAnything();
	    } else if (player.shield.equals(shields[9])) {
	        if (player.shieldAdded) {
	            player.def += shieldAttributes[9];
	            player.shieldAdded = false;
	        }
	        GameLogic.printHeading("Shield Information: ");
	        GameLogic.statement("Shield      : " + player.shield
	                      + "\n  Description : " + shieldsDesc[9]
	                    + "\n\n  Attribute   : +" + shieldAttributes[9] + " DEFENSE"
	                      + "\n  Defense     : " + (player.def - shieldAttributes[9]) + " +" + shieldAttributes[9]);
	        GameLogic.pressAnything();
	    } 
	}
	
}
