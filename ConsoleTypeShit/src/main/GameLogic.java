package main;

import java.io.*;
import java.util.*;

import characters.Player;
import enemies.Enemy;

public class GameLogic implements Serializable {

	private static final long serialVersionUID = 1;
	private static final File gameFolder = new File(
			"C:\\Users\\QCU\\EclipseProjects\\projects\\ConsoleTypeShit\\src\\savedGames");

	public static Scanner scan = new Scanner(System.in);
	static Player player;
	static Random random = new Random();

	public static boolean isRunning, inBattle = false, invOpen = true, atkBuff = false, defBuff = false,
			brandNew = true, go = true, play = true, inventory = false;

	public static int[] lvlThreshold = { 20, 50, 80 };

	public static int tempAtk = 0, tempDef = 0, prevEsc = 0, prevDef = 0, defend = 0, escape = 0;

	public static String[] encounters = { "Battle", "Rest", "Battle", "Rest", "Battle" };
	public static String[] enemies = { "Rock Golem", "Mountain Troll", "Mountain Wolf", "Bat", "Goblin" };
	public static String[] elites = { "Wolf General", "Spectral Guardian", "Corrupted Gnome", "Horned Serpent" };
	public static String[] boss = { "Wolf King", "Undead Knight", "Corrupted Dryad", "Demon Lord Azreal" };

	public static String[] events = { "Low HP Potion", "Medium HP Potion", "Nothing" };

	public static String[] enemyRanks = { "[SEMI-EVIL]", "[EVIL]", "[DEVIOUS]", "[DEMI-DEMON]", "[DEMON]",
			"[DEMON LORD]" };
	public static String[] heroRanks = { "[PAWN]", "[NOVICE]", "[WARRIOR]", "[VETERAN]", "[HERO]", "[CHAMPION]",
			"[CONQUEROR]", "[DEMIGOD]", "[GOD]", "[WARLORD]" };

	public static String[] places = { "The Whispering Mountains", "The Sunken City of Atheria", "The Emerald Forest",
			"The Pits of Hell" };

	public static int readInput(String prompt, int choices) {
		int input;

		do {
			System.out.println("  " + prompt);
			try {
				System.out.print("> ");
				input = Integer.parseInt(scan.next());
			} catch (Exception e) {
				input = -1;
				System.out.println("  Enter a valid option!");
			}
		} while (input < 1 || input > choices);

		return input;
	}

	public static void clearConsole() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	public static void lineSeparator(int ln) {
		for (int i = 0; i < ln; i++)
			System.out.print("-");
		System.out.println();
	}

	public static void printHeading(String prompt) {
		lineSeparator(35);
		System.out.println("  " + prompt);
		lineSeparator(35);
	}

	public static void statement(String prompt) {
		System.out.println("  " + prompt);
	}

	public static void pressAnything() {
		System.out.println("\n  Enter anything to continue..");
		System.out.print("> ");
		scan.next();
	}

	public static void loadState() {
		boolean loadMenu = true;
		clearConsole();
		do {
			String[] files = gameFolder.list();
			File[] listFiles = gameFolder.listFiles();
			clearConsole();
			printHeading("Load from your save files.");
			statement("[1] Load Save Files\n" + "  [2] Go back \n");
			int input = readInput("Pick a number.", 2);
			if (input == 1 && listFiles != null) {
				clearConsole();
				int fileCount = 1;
				printHeading("Save Files (Enter 1 if none): ");
				for (File file : listFiles) {

					statement("[" + fileCount + "] " + file.getName());
					fileCount++;
				}

				int in = readInput("\n  Choose a save file.", fileCount);
				if (in <= fileCount) {
					try {
						clearConsole();
						printHeading("Do you really want to load " + files[in - 1]
								+ "?\n  All progress will be lost if not saved beforehand!");
						int i = readInput("[1] Yes\n  [2] No \n", 2);
						if (i == 1) {
							String format = gameFolder + File.separator + files[in - 1];
							try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(format))) {
								Player loadedState = (Player) stream.readObject();

								// Loaded State
								player = new Player("based");

								player.name = loadedState.name;
								player.rank = loadedState.rank;
								player.maxHp = loadedState.maxHp;
								player.hp = loadedState.hp;
								player.lvl = loadedState.lvl;
								player.exp = loadedState.exp;
								player.maxExp = loadedState.maxExp;
								player.atk = loadedState.atk;
								player.def = loadedState.def;

								// Traits
								player.numAtkUps = loadedState.numAtkUps;
								player.numDefUps = loadedState.numDefUps;
								player.atkUps[player.numAtkUps] = loadedState.atkUps[loadedState.numAtkUps];
								player.defUps[player.numDefUps] = loadedState.defUps[loadedState.numDefUps];

								// Gold and Equipments
								player.gold = loadedState.gold;
								player.act = loadedState.act;
								player.location = loadedState.location;
								player.place = loadedState.place;
								player.armor = loadedState.armor;
								player.weapon = loadedState.weapon;
								player.shield = loadedState.shield;

								// Inventory and stuffs
								player.playerInv = loadedState.playerInv;
								player.playerTraits = loadedState.playerTraits;
								player.playerSkills = loadedState.playerSkills;
								player.saveCount = loadedState.saveCount;
								player.saves = loadedState.saves;
								player.armorAdded = loadedState.armorAdded;
								player.weaponAdded = loadedState.weaponAdded;
								player.shieldAdded = loadedState.shieldAdded;

								clearConsole();
								characterInfo();
								clearConsole();
								printHeading("Successfully loaded your progress in Save Slot " + files[in - 1] + "!");
								pressAnything();
								loadMenu = false;
							} catch (IOException | ClassNotFoundException e) {
								e.printStackTrace();
								clearConsole();
								printHeading("A problem occured while loading the save file.");
								pressAnything();
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						clearConsole();
						printHeading("Invalid Save File.");
						pressAnything();
					}
				}
			} else {
				loadMenu = false;
				if (go) {
					startGame();
				}
			}
		} while (loadMenu);
	}

	public static void equipmentSetup() {
		if (!player.armorAdded) {
			player.maxHp += Equipments.armorAttributes[0];
			player.armorAdded = true;
		}

		if (!player.weaponAdded) {
			player.atk += Equipments.weaponAttributes[0];
			player.weaponAdded = true;
		}

		if (!player.shieldAdded) {
			player.def += Equipments.shieldAttributes[0];
			player.shieldAdded = true;
		}
	}

	public static void startGame() {
		boolean nameSet = false;
		String name;

		clearConsole();

		lineSeparator(35);
		System.err.println("  Sudden Death: Hero Legacy");
		System.out.println("  A Text-RPG made by Iussyan");
		lineSeparator(35);
		pressAnything();

		if (gameFolder.exists() && gameFolder.isDirectory()) {
			do {
				String[] files = gameFolder.list();
				clearConsole();
				printHeading("Main Menu");
				statement("[1] New Game \n" + "  [2] Load Game \n" + "  [3] Quit Game \n");

				int input = readInput("Pick a number.", 3);

				if (input == 1) {
					play = true;
					brandNew = true;
					go = false;
				} else if (input == 2) {
					if (files == null || files.length == 0) {
						clearConsole();
						printHeading("There is no save files found!");
						pressAnything();
					} else {
						loadState();
						if (go) {
							isRunning = true;
						}
						go = false;
						brandNew = false;
					}
				} else {
					clearConsole();
					printHeading("Thanks for playing!");
					play = false;
					go = false;
				}
			} while (go);

		} else {
			System.out.println("The specified package does not exist or is not a directory.");
		}

		if (play) {
			if (brandNew) {
				do {
					clearConsole();
					printHeading("Greetings, Hero! State your name.");
					System.out.print("> ");
					scan.nextLine();
					name = scan.nextLine();

					clearConsole();
					printHeading("You will be known as the Hero, \"" + name + "\". \n" + "  Is that correct?");
					System.out.println("  [1] Yes! \n" + "  [2] No, I want to change my name.");
					lineSeparator(35);
					int input = readInput("Pick a number.", 2);

					if (input == 1)
						nameSet = true;

				} while (!nameSet);
				Story.storyIntro();
				player = new Player(name);
				player.chooseTrait();
				player.baseInventory();
				Story.storyActI();
				equipmentSetup();
				isRunning = true;
			}
			gameLoop();
		}
	}

	public static void checkLvl() {
		if (player.exp >= player.maxExp) {

			player.exp -= player.maxExp;
			player.maxExp += 20;
			player.maxHp += 5;
			player.atk++;
			player.lvl++;
			player.def++;

			if (player.lvl >= 10 && player.lvl < 20) {
				player.rank = heroRanks[1];
			} else if (player.lvl >= 20 && player.lvl < 30) {
				player.rank = heroRanks[2];
			} else if (player.lvl >= 30 && player.lvl < 40) {
				player.rank = heroRanks[3];
			} else if (player.lvl >= 40 && player.lvl < 50) {
				player.rank = heroRanks[4];
			} else if (player.lvl >= 50 && player.lvl < 60) {
				player.rank = heroRanks[5];
			} else if (player.lvl >= 60 && player.lvl < 70) {
				player.rank = heroRanks[6];
			} else if (player.lvl >= 70 && player.lvl < 80) {
				player.rank = heroRanks[7];
			} else if (player.lvl >= 80 && player.lvl < 90) {
				player.rank = heroRanks[8];
			} else if (player.lvl >= 90) {
				player.rank = heroRanks[9];
			} else {
				player.rank = heroRanks[0];
			}

			lvlUpScreen();
		}

	}

	public static void lvlUpScreen() {
		clearConsole();
		printHeading("You Leveled Up!");
		System.out.println("  Name    : " + player.name + "\n  Rank    : " + player.rank + "\n  HP      : " + player.hp
				+ "/" + player.maxHp + " +5" + "\n  Attack  : " + player.atk + " +1" + "\n  Defense : " + player.def
				+ " +1" + "\n  Level   : " + player.lvl + " +1" + "\n  Exp     : " + player.exp + "/" + player.maxExp);
		lineSeparator(35);

		if (player.playerSkills.isEmpty() && !player.playerTraits.isEmpty())
			System.out.println("  Traits : " + player.playerTraits.toString() + "\n  SKills : [No skills yet]");
		else
			System.out.println(
					"  Traits : " + player.playerTraits.toString() + "\n  Skills : " + player.playerSkills.toString());
		lineSeparator(35);

		if (player.lvl == 21) {
			player.place++;
			System.out.println("  You can now go to " + places[player.place] + "!");
		} else if (player.lvl == 31) {
			player.place++;
			System.out.println("  You can now go to " + places[player.place] + "!");
		} else if (player.lvl == 51) {
			player.place++;
			System.out.println("  You can now go to " + places[player.place] + "!");
		}

		pressAnything();
	}

	public static void openMap() {
		clearConsole();

		if (player.location == 0 && player.act == 0)
			Story.mapActi();
		else if (player.location == 1 && player.act == 1)
			Story.mapActII();
		else if (player.location == 2 && player.act == 2)
			Story.mapActIII();
		else if (player.location == 3 && player.act == 3)
			Story.mapActIV();
		pressAnything();
	}

	public static void checkAct() {
		if (player.lvl >= 21 && player.act == 0) {
			player.location++;
			player.act++;

			Story.storyOutI();

			player.chooseTrait();

			Story.storyActII();

			enemies[0] = "Shadowling";
			enemies[1] = "Wraith";
			enemies[2] = "Gnoll";
			enemies[3] = "NIghtmare Weaver";
			enemies[4] = "Phantom Reaver";

			encounters[0] = "Rest";
			encounters[1] = "Battle";
			encounters[2] = "Battle";
			encounters[3] = "Battle";
			encounters[4] = "Rest";

		} else if (player.lvl >= 31 && player.act == 1) {
			player.location++;
			player.act++;

			Story.storyOutII();

			player.chooseTrait();

			Story.storyActIII();

			enemies[0] = "Dreadwood Sentinel";
			enemies[1] = "Forest Troll";
			enemies[2] = "Wrym";
			enemies[3] = "Vineheart Shaman";
			enemies[4] = "Frost Serpent";

			encounters[0] = "Battle";
			encounters[1] = "Battle";
			encounters[2] = "Battle";
			encounters[3] = "Shops";
			encounters[4] = "Rest";

		} else if (player.lvl >= 51 && player.act == 2) {
			player.location++;
			player.act++;

			Story.storyOutIII();

			player.chooseTrait();

			Story.storyActIV();

			enemies[0] = "Flamebound Cerberus";
			enemies[1] = "Abyssal Overlord";
			enemies[2] = "Magma Behemoth";
			enemies[3] = "Shadowflame Incubus";
			enemies[4] = "Voidlash Succubus";

			encounters[0] = "Shops";
			encounters[1] = "Battle";
			encounters[2] = "Battle";
			encounters[3] = "Battle";
			encounters[4] = "Rest";
		}
	}

	public static void randomBattle() {
		clearConsole();
		int encounter = (int) (Math.random() * enemies.length);
		int rank;
		inBattle = true;

		if (player.act == 0) {
			rank = (int) (Math.random() * (enemyRanks.length - 4));
			printHeading("You stumbled upon a creature!\n  " + enemyRanks[rank] + " " + enemies[encounter] + "!");
			pressAnything();
			battle(new Enemy(enemies[encounter], enemyRanks[rank], player.lvl));
		} else if (player.act == 1) {
			rank = (int) (Math.random() * (enemyRanks.length - 3));
			printHeading(
					"You stumbled upon a vengeful creature!\n  " + enemyRanks[rank] + " " + enemies[encounter] + "!");
			pressAnything();
			battle(new Enemy(enemies[encounter], enemyRanks[rank], player.lvl));
		} else if (player.act == 2) {
			rank = (int) (Math.random() * (enemyRanks.length - 2));
			printHeading(
					"You stumbled upon a corrupted creature!\n  " + enemyRanks[rank] + " " + enemies[encounter] + "!");
			pressAnything();
			battle(new Enemy(enemies[encounter], enemyRanks[rank], player.lvl));
		} else {
			rank = (int) (Math.random() * (enemyRanks.length - 1));
			printHeading("You stumbled upon an unimaginable horror!\n  " + enemyRanks[rank] + " " + enemies[encounter]
					+ "!");
			pressAnything();
			battle(new Enemy(enemies[encounter], enemyRanks[rank], player.lvl));
		}

	}

	public static void inventory() {
		int invSize = player.playerInv.size(), itemC = 0;
		String item = "";
		invOpen = true;

		clearConsole();

		if (player.playerInv.isEmpty()) {
			defend = prevDef;
			escape = prevEsc;
			inventory = true;
			printHeading(player.name + "'s Inventory: ");
			System.out.println("\n  [ NO ITEMS FOUND ]\n");
			lineSeparator(35);
			pressAnything();
		} else {
			defend = prevDef;
			escape = prevEsc;
			inventory = true;
			while (invOpen) {
				String[] items = player.playerInv.keySet().toArray(new String[invSize]);
				Integer[] itemCount = player.playerInv.values().toArray(new Integer[invSize]);
				Items.showInv(player);

				lineSeparator(35);
				int input = readInput("Select an item.", invSize + 1);

				if (input <= invSize) {
					try {
						item = items[input - 1];
						itemC = itemCount[input - 1];

						Items.useItem(item, itemC, player);
					} catch (NullPointerException e) {
						invOpen = false;
					}
					
					clearConsole();
				} else if (input == invSize + 1)
					invOpen = false;
			}
		}
	}

	public static void battle(Enemy enemy) {
		int maxHp, playerAtk, enemyAtk, esc = 0;
		int atk = enemy.attack();
		int enemyLvl = ((enemy.lvl + 1) * 5);
		int limiter = (player.atk * 2) - (enemy.lvl * 2);
		int pAtk = player.atk * 2;
		boolean userTurn = true, defending = false;

		if (enemy.rank == enemyRanks[0]) {

			enemy.hp = random.nextInt(pAtk, enemyLvl);
			esc = 50;
			atk = (int) (Math.random() * ((enemy.hp / 2 - limiter) + 1) + limiter);
			enemy.def = (int) (Math.random() * ((2 - 0) + 1) + 0);

			if (player.act == 1) {
				enemy.hp += 10;
				atk += 2;
				enemy.def += 2;
			} else if (player.act == 2) {
				enemy.hp += 12;
				atk += 3;
				enemy.def += 3;
			} else if (player.act == 3) {
				enemy.hp += 14;
				atk += 4;
				enemy.def += 4;
			}

		}

		else if (enemy.rank == enemyRanks[1]) {

			enemy.hp = random.nextInt(pAtk, enemyLvl);
			esc = 55;
			atk = (int) (Math.random() * ((enemy.hp / 2 - limiter) + 1) + limiter);
			enemy.def = (int) (Math.random() * ((2 - 1) + 1) + 1);

			if (player.act == 1) {
				enemy.hp += 12;
				atk += 4;
				enemy.def += 2;
			} else if (player.act == 2) {
				enemy.hp += 14;
				atk += 5;
				enemy.def += 3;
			} else if (player.act == 3) {
				enemy.hp += 16;
				atk += 6;
				enemy.def += 4;
			} else {
				enemy.hp += 5;
				atk += 2;
				enemy.def += 1;
			}

		}

		else if (enemy.rank == enemyRanks[2]) {

			enemy.hp = random.nextInt(pAtk + 2, enemyLvl);
			esc = 65;
			atk = (int) (Math.random() * ((enemy.hp / 2 - limiter) + 1) + limiter);
			enemy.def = (int) (Math.random() * ((3 - 2) + 1) + 2);

			if (player.act == 1) {
				enemy.hp += 14;
				atk += 5;
				enemy.def += 3;
			} else if (player.act == 2) {
				enemy.hp += 16;
				atk += 6;
				enemy.def += 4;
			} else if (player.act == 3) {
				enemy.hp += 18;
				atk += 7;
				enemy.def += 5;
			} else {
				enemy.hp += 10;
				atk += 3;
				enemy.def += 2;
			}

		}

		else if (enemy.rank == enemyRanks[3]) {

			enemy.hp = random.nextInt(pAtk + 3, enemyLvl);
			esc = 75;
			atk = (int) (Math.random() * ((enemy.hp / 2 - limiter) + 1) + limiter);
			enemy.def = (int) (Math.random() * ((4 - 3) + 1) + 3);

			if (player.act == 1) {
				enemy.hp += 16;
				atk += 6;
				enemy.def += 4;
			} else if (player.act == 2) {
				enemy.hp += 18;
				atk += 7;
				enemy.def += 5;
			} else if (player.act == 3) {
				enemy.hp += 20;
				atk += 8;
				enemy.def += 6;
			} else {
				enemy.hp += 15;
				atk += 4;
				enemy.def += 2;
			}

		}

		else if (enemy.rank == enemyRanks[4]) {

			enemy.hp = random.nextInt(pAtk + 5, enemyLvl);
			esc = 85;
			atk = (int) (Math.random() * ((enemy.hp / 2 - limiter) + 1) + limiter);
			enemy.def = (int) (Math.random() * ((5 - 4) + 1) + 4);

			if (player.act == 1) {
				enemy.hp += 18;
				atk += 7;
				enemy.def += 5;
			} else if (player.act == 2) {
				enemy.hp += 20;
				atk += 8;
				enemy.def += 6;
			} else if (player.act == 3) {
				enemy.hp += 22;
				atk += 9;
				enemy.def += 7;
			} else {
				enemy.hp += 17;
				atk += 5;
				enemy.def += 3;
			}

		}

		maxHp = enemy.hp;

		if (enemy.def < 0)
			enemy.def = 0;

		while (true) {
			clearConsole();

			playerAtk = (player.atk - enemy.def);
			enemyAtk = (atk - player.def);
			prevDef = player.defend();
			prevEsc = player.escape();

			if (inventory) {
				prevDef = defend;
				prevEsc = escape;
				inventory = false;
				defend = 0;
				escape = 0;
			}

			printHeading(enemy.rank + " " + enemy.name + "\n  Level   : " + enemyLvl + "\n\n  HP      : " + enemy.hp
					+ "/" + maxHp + "\n  Attack  : " + atk + "\n  Defense : " + enemy.def);
			printHeading(player.rank + " " + player.name + "\n  Level   : " + player.lvl + "\n\n  HP      : "
					+ player.hp + "/" + player.maxHp + "\n  Attack  : " + player.atk + "\n  Defense : " + player.def);

			if (userTurn) {
				if (enemy.def > playerAtk && playerAtk <= 0)
					playerAtk = 0;
				if (player.def > enemyAtk && enemyAtk <= 0)
					enemyAtk = 0;
				System.out.println("  " + player.name + "'(s) Turn! \n\n" + "  [1] Attack    " + "(-" + playerAtk
						+ " enemy hp) || (-" + enemyAtk + " hero hp)\n" + "  [2] Defend    " + "(" + prevDef
						+ "% / 50% chance)\n" + "  [3] Inventory\n" + "  [4] Run away  " + "(" + prevEsc + "% / " + esc
						+ "% chance)\n");
				int input = readInput("What do you want to do?", 4);

				if (input == 1) {
					clearConsole();
					if (enemy.def > playerAtk && playerAtk <= 0)
						playerAtk = 0;
					else
						enemy.hp -= playerAtk;
					printHeading(player.name + " dealt " + playerAtk + " dmg to " + enemy.rank + " " + enemy.name);
					userTurn = false;
					pressAnything();
				} else if (input == 2) {
					if (prevDef >= 50) {
						clearConsole();
						printHeading(player.name + " braced for impact!");
						defending = true;
						userTurn = false;
						pressAnything();
					} else {
						clearConsole();
						printHeading(player.name + " failed to defend!");
						userTurn = false;
						pressAnything();
					}
				} else if (input == 3) {
					inventory();

				} else {
					if (enemy.rank == enemyRanks[0] && prevEsc >= 50) {
						clearConsole();
						printHeading("Successfully ran away!");
						inBattle = false;
						pressAnything();
						break;
					} else if (enemy.rank == enemyRanks[1] && prevEsc >= 55) {
						clearConsole();
						printHeading("Successfully ran away!");
						inBattle = false;
						pressAnything();
						break;
					} else if (enemy.rank == enemyRanks[2] && prevEsc >= 65) {
						clearConsole();
						printHeading("Successfully ran away!");
						inBattle = false;
						pressAnything();
						break;
					} else if (enemy.rank == enemyRanks[3] && prevEsc >= 75) {
						clearConsole();
						printHeading("Successfully ran away!");
						inBattle = false;
						pressAnything();
						break;
					} else if (enemy.rank == enemyRanks[4] && prevEsc >= 85) {
						clearConsole();
						printHeading("Successfully ran away!");
						inBattle = false;
						pressAnything();
						break;
					} else {
						userTurn = false;
						clearConsole();
						printHeading("You tripped as you try to run away!");
						pressAnything();
					}

				}
			} else {
				System.out.println("  " + enemy.rank + " " + enemy.name + "'(s) Turn!");
				pressAnything();

				clearConsole();
				if (defending) {
					player.hp -= 0;
					printHeading(player.name + " defended the attack!");
					defending = false;
				} else {
					if (player.def > enemyAtk && enemyAtk <= 0)
						enemyAtk = 0;
					else
						player.hp -= enemyAtk;
					printHeading(enemy.name + " dealt " + enemyAtk + " dmg to " + player.rank + " " + player.name);
				}
				pressAnything();
				userTurn = true;
			}

			if (player.hp <= 0) {
				clearConsole();
				if (player.lvl >= 6) {
					printHeading("You passed out. A Travelling Nurse healed you for 50 exp.\n  - 50 exp");
					player.exp -= 50;
					if (player.exp <= 0 && player.exp < player.maxExp) {
						statement("You went down a level!");
						player.lvl--;
						player.maxExp -= 20;
						player.exp = player.maxExp - 50;
						statement("Level : " + player.lvl + "(-1)");
					}
					player.hp = player.maxHp;
				} else if (player.lvl >= 0 && player.lvl < 6 && player.exp >= 5) {
					printHeading("You passed out. A strange being healed you and took some of your exp.\n  - 5 exp");
					player.exp -= 5;
					if (player.exp <= 0 && player.exp < player.maxExp) {
						statement("You went down a level!");
						player.lvl--;
						player.maxExp -= 20;
						player.exp = player.maxExp - 30;
						statement("\n  Level : " + player.lvl + "(-1)");
					}
					player.hp = player.maxHp;
				} else {
					printHeading("You passed out. A strange being healed you out of pity.");
					player.hp = player.maxHp;
				}
				inBattle = false;

				pressAnything();
				clearConsole();
				break;
			} else if (enemy.hp <= 0) {
				clearConsole();
				printHeading("You defeated " + enemy.rank + " " + enemy.name + "!");
				pressAnything();
				clearConsole();

				if (enemy.rank == enemyRanks[0]) {
					printHeading("You gained " + (maxHp + 4) + " exp!");
					player.exp += (maxHp + 4);
				} else if (enemy.rank == enemyRanks[1]) {
					printHeading("You gained " + (maxHp + 6) + " exp!");
					player.exp += (maxHp + 6);
				} else if (enemy.rank == enemyRanks[2]) {
					printHeading("You gained " + (maxHp + 8) + " exp!");
					player.exp += (maxHp + 8);
				} else if (enemy.rank == enemyRanks[3]) {
					printHeading("You gained " + (maxHp + 10) + " exp!");
					player.exp += (maxHp + 10);
				} else if (enemy.rank == enemyRanks[4]) {
					printHeading("You gained " + (maxHp + 12) + " exp!");
					player.exp += (maxHp + 12);
				}

				inBattle = false;

				if (!inBattle) {
					if (tempAtk != 0 || tempDef != 0) {
						System.out.println("\n\n");
						printHeading("You lost all of the buffs from your last battle.");
						if (atkBuff) {
							player.atk -= tempAtk;
							System.out.println(
									"  You lost the +" + tempAtk + " Attack buff.\n\n  Attack   : " + player.atk);
							tempAtk = 0;
							lineSeparator(35);
						} else if (defBuff) {
							player.def -= tempDef;
							System.out.println(
									"  You lost the +" + tempDef + " Defense buff.\n\n  Defense  : " + player.def);
							tempDef = 0;
							lineSeparator(35);
						} else {
							player.atk -= tempAtk;
							player.def -= tempDef;
							System.out.println(
									"  You lost the +" + tempAtk + " Attack buff and +" + tempDef + " Defense buff.\n"
											+ "\n  Attack   : " + player.atk + "\n  Defense  : " + player.def);
							tempAtk = 0;
							tempDef = 0;
							lineSeparator(35);
						}
					}
					atkBuff = false;
					defBuff = false;
				}

				pressAnything();
				clearConsole();

				break;
			}
		}
	}

	public static void rest() {
		clearConsole();
		int dialogue = random.nextInt(0, 3);
		int event = random.nextInt(0, events.length);

		if (dialogue == 1) {
			int amount = random.nextInt(1, 3);

			if (events[event] != "Nothing") {
				printHeading("A mysterious lady suddenly appeared in front of you.\n  They handed you +" + amount + " "
						+ events[event] + "\n\n  You stashed it in your inventory.");

				if (player.playerInv.containsKey(events[event])) {
					int origAmount = 0;

					for (Map.Entry<String, Integer> entry : player.playerInv.entrySet()) {
						if (entry.getKey().equals(events[event])) {
							origAmount = entry.getValue();
						}
					}

					origAmount += amount;

					player.playerInv.put(events[event], origAmount);
				} else {
					player.playerInv.put(events[event], amount);
				}

				pressAnything();

			} else {
				printHeading("You stumbled upon a glowing thing lying on the ground. As you look closely,\n\n"
						+ "  It was just a puddle. You gained " + events[event] + ".");
				pressAnything();
			}

		} else if (dialogue == 2) {
			int amount = random.nextInt((player.hp / 4) - 1, player.hp / 2);

			printHeading("You found a well-maintained garden. It has a glowing fountain found in the center.");

			if (player.hp < player.maxHp) {
				player.hp += amount;
				if (player.hp >= player.maxHp) {
					int excess = 0;
					excess = player.hp - player.maxHp;
					amount -= excess;
					player.hp = player.maxHp;
				}
				System.out.println("  You moved closer and drank the glowing liquid." + "\n  You gained +" + amount
						+ " HP!" + "\n\n  HP      : " + player.hp + "/" + player.maxHp);
				lineSeparator(35);
				pressAnything();
			} else {
				System.out.println("  You moved closer but found it useless to drink the glowing liquid.");
				lineSeparator(35);
				pressAnything();
			}

		} else if (dialogue == 3) {
			int amount = random.nextInt(0, 5);

			printHeading(
					"You found yourself trapped in a thorny field! You tried to remove the thorns from your body.");

			if (amount != 0) {
				System.out.println("  You successfully removed the thorns!\n\n  However, you took -" + amount + " HP!");
				player.hp -= amount;
				lineSeparator(35);
				pressAnything();
			} else {
				System.out.println("  You successfully removed the thorns! You took no damage.");
				lineSeparator(35);
				pressAnything();
			}
		} else {
			int amount = random.nextInt(1, 10);

			printHeading("You found glowing things on the ground.\n\n  You picked up +" + amount + " Gold!");
			player.gold += amount;
			pressAnything();
		}
	}

	public static void randomEncounter() {
		int encounter = random.nextInt(encounters.length);

		if (encounters[encounter].equals("Battle")) {
			randomBattle();
		} else if (encounters[encounter].equals("Rest")) {
			rest();
		} else {
			// shop();
		}
	}

	public static void continueJourney() {
		checkAct();
		if (player.act != 4) {
			randomEncounter();
		}
	}

	public static void characterInfo() {
		clearConsole();
		printHeading("Character Information:");
		System.out.println("  Name    : " + player.name + "\n  Rank    : " + player.rank + "\n  Gold    : "
				+ player.gold + "\n  HP      : " + player.hp + "/" + player.maxHp + "\n  Attack  : " + player.atk
				+ "\n  Defense : " + player.def + "\n  Level   : " + player.lvl + "\n  Exp     : " + player.exp + "/"
				+ player.maxExp);
		lineSeparator(35);

		if (player.playerSkills.isEmpty() && !player.playerTraits.isEmpty())
			System.out.println("  Traits : " + player.playerTraits.toString() + "\n  SKills : [No skills yet]");
		else
			System.out.println(
					"  Traits : " + player.playerTraits.toString() + "\n  Skills : " + player.playerSkills.toString());
		lineSeparator(35);
		pressAnything();
	}

	public static void explore() {

	}

	public static void saveState() {
		boolean saveMenu = true;
		if (gameFolder.exists() && gameFolder.isDirectory()) {
			do {
				String[] list = gameFolder.list();
				File[] listFiles = gameFolder.listFiles();
				clearConsole();
				printHeading("Save your current state.");
				statement("[1] Create New Save \n" + "  [2] Overwrite Save \n" + "  [3] Load Save \n"
						+ "  [4] Go back \n");

				int input = readInput("Pick a number.", 4);

				if (input == 1) {
					clearConsole();
					printHeading("New Save File");
					if (player.saveCount <= 9) {
						statement("Save Slot " + player.saveCount);
						int inp = readInput("\n  Save?\n  [1] Yes\n  [2] Maybe later\n", 2);
						if (inp == 1) {
							player.saves.add(player.name + "-Save-Slot-" + player.saveCount + ".ser");
							player.saveCount++;
							String format = gameFolder + File.separator + player.name + "-Save-Slot-"
									+ (player.saveCount - 1) + ".ser";
							try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(format))) {
								out.writeObject(player);
								clearConsole();
								printHeading("Game saved in Save Slot " + (player.saveCount - 1) + "!");
								pressAnything();
								break;
							} catch (IOException e) {
								e.printStackTrace();
								clearConsole();
								printHeading("A problem occured while saving the game.");
								pressAnything();
							}
						}
					} else {
						statement("You have used all available save files! \n  Save files available: "
								+ player.saveCount + "/10");
						pressAnything();
					}

				} else if (input == 2) {
					clearConsole();
					if (listFiles != null) {
						int fileCount = 1;
						printHeading("Save Files (Enter 1 if none): ");
						for (File file : listFiles) {

							statement("[" + fileCount + "] " + file.getName());
							fileCount++;
						}

						int in = readInput("\n  Choose a save file.", fileCount);
						if (in <= fileCount) {
							try {
								clearConsole();
								printHeading("Do you wish to overwrite " + list[in - 1] + "?");
								int i = readInput("[1] Yes\n  [2] No \n", 2);
								if (i == 1) {
									String format = gameFolder + File.separator + list[in - 1];
									try (ObjectOutputStream out = new ObjectOutputStream(
											new FileOutputStream(format))) {
										out.writeObject(player);
										clearConsole();
										printHeading("Overwrited game save in Save Slot " + list[in - 1] + "!");
										pressAnything();
										break;
									} catch (IOException e) {
										e.printStackTrace();
										clearConsole();
										printHeading("A problem occured while saving the game.");
										pressAnything();
									}
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								clearConsole();
								printHeading("Invalid Save File.");
								pressAnything();
							}
						}
					} else {
						printHeading("You have no save files yet.");
						pressAnything();
					}
				} else if (input == 3) {
					loadState();
				} else {
					saveMenu = false;
					options();
				}
			} while (saveMenu);

		} else {
			System.out.println("The specified package does not exist or is not a directory.");
		}

	}

	public static void options() {
		clearConsole();
		printHeading("Options:");
		statement("[1] Inventory \n" + "  [2] Equipments \n" + "  [3] Save Game \n" + "  [4] Go back \n");
		int input = readInput("Pick a number.", 4);
		if (input == 1) {
			inventory();
		} else if (input == 2) {
			Equipments.equipments(player);
		} else if (input == 3) {
			saveState();
		}
	}

	public static void printMenu() {
		clearConsole();
		printHeading(places[player.location]);
		System.out.println("  Choose an action: ");
		lineSeparator(30);
		System.out.println("  [1] Continue Journey \n" + "  [2] Explore \n" + "  [3] Character Info \n" + "  [4] Map \n"
				+ "  [5] +1k EXP (FOR TESTING PURPOSES) \n" + "  [6] Options \n" + "  [7] Exit game");
		lineSeparator(30);
	}

	public static void gameLoop() {
		while (isRunning) {
			printMenu();

			int input = readInput("Pick a number.", 7);

			if (input == 1) {
				continueJourney();
				do {
					checkLvl();
				} while (player.exp >= player.maxExp);
			} else if (input == 2)
				explore();
			else if (input == 3)
				characterInfo();
			else if (input == 4)
				openMap();
			else if (input == 5) {
				player.exp += 1000;
				do {
					checkLvl();
				} while (player.exp >= player.maxExp);
			} else if (input == 6) {
				options();
			} else {
				do {
					int conf = readInput("\n  Are you sure? \n\n  [1] Yes \n  [2] No\n", 2);

					if (conf == 1) {
						clearConsole();
						printHeading("Thanks for playing!");
						isRunning = false;
						break;
					} else
						break;

				} while (true);
			}
		}

	}

}
