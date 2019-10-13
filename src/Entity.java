public class Entity {
	
	String name;
	boolean isPlayer;
	boolean isMonster;
	int ac;
	double hp;
	int attackDiceNum;
	int attackDiceSides;
	int attackBonus;
	int attacks;
	int toHit;
	int range;
	// 1 for melee 2 for range
	int initiative;
	boolean alive = true;
	
	public Entity (String name, boolean isPlayer, boolean isMonster, int ac, double hp, int attackDiceNum, int attackDiceSides, int attackBonus, int attacks, int toHit) {
		this.name = name;
		this.isPlayer = isPlayer;
		this.isMonster = isMonster;
		this.ac = ac;
		this.hp = hp;
		this.attackDiceNum = attackDiceNum;
		this.attackDiceSides = attackDiceSides;
		this.attackBonus = attackBonus;
		this.attacks = attacks;
		this.toHit = toHit;
	}
	
	public Entity () {
		this.name = "";
		this.isPlayer = true;
		this.isMonster = false;
		this.ac = 12;
		this.hp = 20;
		this.attackDiceNum = 9;
		this.attackDiceSides = 9;
		this.attackBonus = 9;
		this.attacks = 9;
		this.toHit = 9;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public boolean getIsPlayer() {
		return isPlayer;
	}
	
	public boolean getIsMonster() {
		return isMonster;
	}
	
	public int getAC() {
		return ac;
	}
	
	public double getHP() {
		return hp;
	}
	
	public void reduceHP(double damage) {
		hp = hp - damage;
		if (hp <= 0) {
			alive = false;
		}
	}
	
	public double getDmg() {
		return toHit;
	}
	
	public int getAttacks() {
		return attacks;
	}
	
	public int getToHit() {
		return toHit;
	}
	
	public int getInitiative() {
		return initiative;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public double getRandomDamage() {
		double total = 0;
		for (int i = 0; i < attackDiceSides; i++) {
			total += randomInt(1, attackDiceSides);
		}
		//adds result of each dice roll for all dice
		total += attackBonus;
		// adds attack bonus
		return total;
	}
	
	public static int randomInt(int min, int max){
	    int x = (int)(Math.random()*((max-min)+1))+min;
	    return x;
	}
}
