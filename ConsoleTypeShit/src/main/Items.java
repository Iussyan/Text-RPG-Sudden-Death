package main;

import java.util.Map;

import characters.Player;

public class Items {
	
	public static void showInv(Player player) {
		int inv = 1;
		
		GameLogic.printHeading(player.name + "'s Inventory: ");
		for (Map.Entry<String, Integer> entry : player.playerInv.entrySet()) {
			String key = entry.getKey();
			Integer val = entry.getValue();
			System.out.println("   [" + inv + "] " + key + "     : " + val);
			inv++;
		}

		System.out.println("   [" + inv + "] " + "Go back");
	}
	
	public static void useItem(String item, int itemC, Player player) {
		int effect = 0;
		
		if (item.equals("Low HP Potion")) {
			GameLogic.clearConsole();
			GameLogic.printHeading(item + " heals you for 25% of your current max hp.\n  You have : " + itemC);
			int i = GameLogic.readInput("Use this item?\n\n  [1] Sure, I want to use it.\n  [2] No, maybe later.\n", 2);
			
			if (i == 1) {
				if (player.hp < player.maxHp) {
					effect = player.maxHp / 4;

					player.hp += effect;
					
					if (player.hp >= player.maxHp) {
						int excess = 0;
						excess = player.hp - player.maxHp;
						effect -= excess;
						player.hp = player.maxHp;
					}
					
					if (itemC > 0) {
						itemC--;
						player.playerInv.replace(item, itemC);
						
						if (itemC <= 0) {
							player.playerInv.remove(item);
						}
						
					} else {
						itemC = 0;
						player.playerInv.remove(item);
					}
					
					GameLogic.clearConsole();
					
					if (itemC > 0) {
						GameLogic.printHeading("You used " + item + ".\n  You still have : " + itemC);
						System.out.println("  You recovered " + effect + " HP!" + "\n\n  HP      : " + player.hp + "/"
								+ player.maxHp);
					} else {
						GameLogic.printHeading("You used " + item + ".\n  You have no " + item + " left!");
						System.out.println("  You recovered " + effect + " HP!" + "\n\n  HP      : " + player.hp + "/"
								+ player.maxHp);
					}
					
					GameLogic.lineSeparator(35);
					GameLogic.pressAnything();
				}
				else {
					GameLogic.clearConsole();
					GameLogic.printHeading("You couldn't see any use for the potion, so you decided to leave it in your inventory.");
					GameLogic.pressAnything();
				}
			}
			
		}
		else if (item.equals("Medium HP Potion")) {
			GameLogic.clearConsole();
			GameLogic.printHeading(item + " heals you for 50% of your current max hp.\n  You have : " + itemC);
			int i = GameLogic.readInput("Use this item?\n\n  [1] Sure, I want to use it.\n  [2] No, maybe later.\n", 2);
			
			if (i == 1) {
				if (player.hp < player.maxHp) {
					effect = player.maxHp / 2;

					player.hp += effect;
					
					if (player.hp >= player.maxHp) {
						int excess = 0;
						excess = player.hp - player.maxHp;
						effect -= excess;
						player.hp = player.maxHp;
					}
					
					if (itemC > 0) {
						itemC--;
						player.playerInv.replace(item, itemC);

						if (itemC <= 0) {
							player.playerInv.remove(item);
						}
						
					} else {
						itemC = 0;
						player.playerInv.remove(item);
					}
					
					GameLogic.clearConsole();
					
					if (itemC > 0) {
						GameLogic.printHeading("You used " + item + ".\n  You still have : " + itemC);
						System.out.println("  You recovered " + effect + " HP!"
								       + "\n\n  HP      : " + player.hp + "/" + player.maxHp);
						} else {
							GameLogic.printHeading("You used " + item + ".\n  You have no " + item + " left!");
							System.out.println("  You recovered " + effect + " HP!"
									       + "\n\n  HP      : " + player.hp + "/" + player.maxHp);
						}
					
					GameLogic.lineSeparator(35);
					GameLogic.pressAnything();
				}
				else {
					GameLogic.clearConsole();
					GameLogic.printHeading("You couldn't see any use for the potion, so you decided to leave it in your inventory.");
					GameLogic.pressAnything();
				}
			}
			
		}
		else if (item.equals("High HP Potion")) {
			GameLogic.clearConsole();
			GameLogic.printHeading(item + " heals you for 100% of your current max hp.\n  You have : " + itemC);
			int i = GameLogic.readInput("Use this item?\n\n  [1] Sure, I want to use it.\n  [2] No, maybe later.\n", 2);
			
			if (i == 1) {
				if (player.hp < player.maxHp) {
					effect = player.maxHp;

					player.hp += effect;
					
					if (player.hp >= player.maxHp) {
						int excess = 0;
						excess = player.hp - player.maxHp;
						effect -= excess;
						player.hp = player.maxHp;
					}
					
					if (itemC > 0) {
						itemC--;
						player.playerInv.replace(item, itemC);

						if (itemC <= 0) {
							player.playerInv.remove(item);
						}
						
					} else {
						itemC = 0;
						player.playerInv.remove(item);
					}
					
					GameLogic.clearConsole();
					
					if (itemC > 0) {
						GameLogic.printHeading("You used " + item + ".\n  You still have : " + itemC);
						System.out.println("  You recovered " + effect + " HP!"
								       + "\n\n  HP      : " + player.hp + "/" + player.maxHp);
						} else {
							GameLogic.printHeading("You used " + item + ".\n  You have no " + item + " left!");
							System.out.println("  You recovered " + effect + " HP!"
									       + "\n\n  HP      : " + player.hp + "/" + player.maxHp);
						}
					
					GameLogic.lineSeparator(35);
					GameLogic.pressAnything();
				}
				else {
					GameLogic.clearConsole();
					GameLogic.printHeading("You couldn't see any use for the potion, so you decided to leave it in your inventory.");
					GameLogic.pressAnything();
				}
			}
			
		}
		else if (item.equals("Low ATK Potion")) {
			GameLogic.clearConsole();
			GameLogic.printHeading(item + " temporarily gives +2 attack in the current battle. Useless outside the battle.\n  You have : " + itemC);
			int i = GameLogic.readInput("Use this item?\n\n  [1] Sure, I want to use it.\n  [2] No, maybe later.\n", 2);
			
			if (i == 1) {
				if (GameLogic.inBattle) {
					effect = 2;
					GameLogic.tempAtk += effect;
					
					if (GameLogic.inBattle) {
						player.atk += effect;
						GameLogic.atkBuff = true;
					}
					
					if (itemC > 0) {
						itemC--;
						player.playerInv.replace(item, itemC);

						if (itemC <= 0) {
							player.playerInv.remove(item);
						}
						
					} else {
						itemC = 0;
						player.playerInv.remove(item);
					}
					
					GameLogic.clearConsole();
					
					if (itemC > 0) {
						GameLogic.printHeading("You used " + item + ".\n  You still have : " + itemC);
						System.out.println("  You gained " + effect + " Attack!"
								       + "\n\n  Attack      : " + player.atk + " +" + effect);
						} else {
							GameLogic.printHeading("You used " + item + ".\n  You have no " + item + " left!");
							System.out.println("  You gained " + effect + " Attack!"
									       + "\n\n  Attack      : " + player.atk + " +" + effect);
						}
					
					GameLogic.lineSeparator(35);
					GameLogic.pressAnything();
				}
				else {
					GameLogic.clearConsole();
					GameLogic.printHeading("This potion is only usable during battle!");
					GameLogic.pressAnything();
				}
			}
			
		}
		else if (item.equals("Medium ATK Potion")) {
			GameLogic.clearConsole();
			GameLogic.printHeading(item + " temporarily gives +4 attack in the current battle. Useless outside the battle.\n  You have : " + itemC);
			int i = GameLogic.readInput("Use this item?\n\n  [1] Sure, I want to use it.\n  [2] No, maybe later.\n", 2);
			
			if (i == 1) {
				if (GameLogic.inBattle) {
					effect = 4;
					GameLogic.tempAtk += effect;
					
					if (GameLogic.inBattle) {
						player.atk += effect;
						GameLogic.atkBuff = true;
					}
					
					if (itemC > 0) {
						itemC--;
						player.playerInv.replace(item, itemC);

						if (itemC <= 0) {
							player.playerInv.remove(item);
						}
						
					} else {
						itemC = 0;
						player.playerInv.remove(item);
					}
					
					GameLogic.clearConsole();
					
					if (itemC > 0) {
						GameLogic.printHeading("You used " + item + ".\n  You still have : " + itemC);
						System.out.println("  You gained " + effect + " Attack!"
								       + "\n\n  Attack      : " + player.atk + " +" + effect);
						} else {
							GameLogic.printHeading("You used " + item + ".\n  You have no " + item + " left!");
							System.out.println("  You gained " + effect + " Attack!"
									       + "\n\n  Attack      : " + player.atk + " +" + effect);
						}
					
					GameLogic.lineSeparator(35);
					GameLogic.pressAnything();
				}
				else {
					GameLogic.clearConsole();
					GameLogic.printHeading("This potion is only usable during battle!");
					GameLogic.pressAnything();
				}
			}
			
		}
		else if (item.equals("High ATK Potion")) {
			GameLogic.clearConsole();
			GameLogic.printHeading(item + " temporarily gives +6 attack in the current battle. Useless outside the battle.\n  You have : " + itemC);
			int i = GameLogic.readInput("Use this item?\n\n  [1] Sure, I want to use it.\n  [2] No, maybe later.\n", 2);
			
			if (i == 1) {
				if (GameLogic.inBattle) {
					effect = 6;
					GameLogic.tempAtk += effect;
					
					if (GameLogic.inBattle) {
						player.atk += effect;
						GameLogic.atkBuff = true;
					}
					
					if (itemC > 0) {
						itemC--;
						player.playerInv.replace(item, itemC);

						if (itemC <= 0) {
							player.playerInv.remove(item);
						}
						
					} else {
						itemC = 0;
						player.playerInv.remove(item);
					}
					
					GameLogic.clearConsole();
					
					if (itemC > 0) {
						GameLogic.printHeading("You used " + item + ".\n  You still have : " + itemC);
						System.out.println("  You gained " + effect + " Attack!"
								       + "\n\n  Attack      : " + player.atk + " +" + effect);
						} else {
							GameLogic.printHeading("You used " + item + ".\n  You have no " + item + " left!");
							System.out.println("  You gained " + effect + " Attack!"
									       + "\n\n  Attack      : " + player.atk + " +" + effect);
						}
					
					GameLogic.lineSeparator(35);
					GameLogic.pressAnything();
				}
				else {
					GameLogic.clearConsole();
					GameLogic.printHeading("This potion is only usable during battle!");
					GameLogic.pressAnything();
				}
			}
		}
	}
}
