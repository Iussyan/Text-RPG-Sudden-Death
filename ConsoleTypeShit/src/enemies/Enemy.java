package enemies;

public class Enemy extends EnemyChar {
	
	public int lvl;

	public Enemy(String name, String rank, int lvl) {
		super(name, rank, 1, 1);
		this.lvl = lvl;
	}

	@Override
	public int attack() {
		
		return 1;
	}

	@Override
	public int magicAttack() {
		
		return 0;
	}

	@Override
	public int defend() {

		return (int) (Math.random() * 100);
	}

}
