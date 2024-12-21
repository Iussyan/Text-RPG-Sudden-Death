package enemies;

public abstract class EnemyChar {
	
	public String name, rank;
	public int hp, def;
	
	// Base Character/Enemies stats
	public EnemyChar(String name, String rank, int hp, int def) {
		this.name = name;
		this.rank = rank;
		this.hp = hp;
		this.def = def;
	}
	
	/*             ** ENEMY RARITIES **
	 *  SEMI-EVIL  - 15-30   hp  || 3-5     exp
	 *  EVIL       - 35-50   hp  || 8-10    exp
	 *  DEVIOUS    - 55-70   hp  || 13-20   exp
	 *  DEMI-DEMON - 75-100  hp  || 23-30   exp
	 *  DEMON      - 105-130 hp  || 33-40   exp
	 *  DEMON LORD - 300-500 hp  || 43-50   exp
	 */
	
	/*
	 *                 ** HERO TITLES ** 
	 *           (Affected through playthrough)
	 *  NOV. HERO  - 01-10   lvl || 10-100      exp [NOVICE]
	 *  EXP. HERO  - 11-20   lvl || 110-200     exp [EXPERIENCED]
	 *  VET. HERO  - 21-30   lvl || 210-300     exp [VETERAN]
	 *  RES. HERO  - 31-50   lvl || 310-600     exp [RESPECTABLE]
	 *  NOB. HERO  - 51-70   lvl || 610-800     exp [NOBLE]
	 *  BLSD. HERO - 71-100+ lvl || 810-1000+10 exp [BLESSED]
	 */

	// Common Actions (Character/Enemies)
	public abstract int attack();
	public abstract int magicAttack();
	public abstract int defend();
	
}
