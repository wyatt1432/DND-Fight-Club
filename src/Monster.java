public class Monster extends Entity {
	
	public Monster(String name, int ac, double hp, double dmg, int attacks, int toHit) {
		super.name = name;
		super.ac = ac;
		super.hp = hp;
		super.dmg = dmg;
		super.attacks = attacks;
		super.toHit = toHit;
	}
}
