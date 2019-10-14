public class Encounter {
	
	int round = 0;
	boolean death = false;
	boolean tbk = false;
	Entity[] entityArray;
	Entity[] entityArrayHealth;
	int strategy = 1;
	int numPlayers;
	int numMonsters;
	int numEntities;
	
	public Encounter(Entity[] entityArray, Entity[] entityArrayHealth, int numPlayers, int numMonsters) {
		this.entityArray = entityArray;
		this.entityArrayHealth = entityArrayHealth;
		this.numPlayers = numPlayers;
		this.numMonsters = numMonsters;
	}
	
	public static int randomInt(int min, int max){
	    int x = (int)(Math.random()*((max-min)+1))+min;
	    return x;
	}
	
	public int pickRandomAliveMonster() {
		int index = randomInt(numPlayers, entityArray.length - 1);
		if (!checkMonstersAlive()) {
			System.out.println("pickRandomMonster was called when all monsters are dead.");
		}
		if (entityArray[index].getAlive() == false) {
			System.out.println("interative called Monster");
			index = pickRandomAliveMonster();
		}
		return index;
	}
	
	public int pickRandomAlivePlayer() {
		int index = randomInt(0, numPlayers - 1);
		if (!checkPlayersAlive()) {
			System.out.println("pickRandomPlayer was called when all players are dead.");
		}
		if (entityArray[index].getAlive() == false) {
			System.out.println("interative called Player");
			index = pickRandomAlivePlayer();
		}
		return index;
	}
	
	public boolean checkMonstersAlive() {
		boolean isAnyMonsterAlive = false;
		for (int i = numPlayers; i <= (entityArray.length - 1); i++) {
			if (entityArray[i].getAlive() == true) {
				isAnyMonsterAlive = true;
			}
		}
		return isAnyMonsterAlive;
	}
	
	public boolean checkPlayersAlive() {
		boolean isAnyPlayerAlive = false;
		for (int i = 0; i <= (numPlayers - 1); i++) {
			if (entityArray[i].getAlive() == true) {
				isAnyPlayerAlive = true;
			}
		}
		return isAnyPlayerAlive;
	}
	
	public void takeTurn(Entity actor) {
		if (strategy == 1) {
			if (actor.isPlayer && actor.getAlive()) {
				for (int i = 0; i < actor.attacks; i++) {
					if (checkMonstersAlive() && checkPlayersAlive()) {
						Entity victim = entityArray[pickRandomAliveMonster()];
						double damage = actor.getRandomDamage();
						System.out.println(damage);
						entityArray[pickRandomAliveMonster()].reduceHP(damage);
						System.out.println(actor.getName() + " attacked " + victim.getName() + " while the actor was at " + actor.getHP() + " health and brought the victim to " + victim.getHP() + ". To check, the attacked is " + actor.getAlive() + " while the victim is now " + victim.getAlive() + "." + " checkPlayersAlive is " + checkPlayersAlive());
					}
				}
			}
			if (actor.isMonster && actor.getAlive()) {
				for (int i = 0; i < actor.attacks; i++) {
					if (checkMonstersAlive() && checkPlayersAlive()) {
						entityArray[pickRandomAlivePlayer()].reduceHP(actor.getRandomDamage());
					}
				}
			}
			if (actor.isMonster == false && actor.isPlayer == false) {
				System.out.println(actor.getName() + " is neither a player nor monster");
			}
		}
	}	
	
	public Entity[] createInitiativeArray() {
		for (int i = 0; i < entityArray.length; i++) {
			entityArray[i].initiative = randomInt(1, 20);
			System.out.println(entityArray[i].getName() + " has an intative of " + entityArray[i].getInitiative() + " and a starting health of " + entityArray[i].getHP());
		}
		//decides initiative
		Entity[] initiativeArray = new Entity[entityArray.length];
		int highestInitiative = -5;
		int highestInitiativeIndex = -5;
		for (int i = 0; i < entityArray.length; i++) {
			for (int j = 0; j < entityArray.length; j++) {
				if (entityArray[j].getInitiative() > highestInitiative) {
					highestInitiative = entityArray[j].initiative;
					highestInitiativeIndex = j;
				}
			}
			initiativeArray[i] = entityArray[highestInitiativeIndex];
			entityArray[highestInitiativeIndex].initiative = -500;
			highestInitiative = -5;
		}
		System.out.println("The intiative order is:");
		for (int i = 0; i < entityArray.length; i++) {
			System.out.println(initiativeArray[i].getName());
		}
		return initiativeArray;
		//makes an array ordered by initiative
	}
	
	public void runEncounter() {
		for (double i = 0; i < 1000; i++) {
			for (int j = 0; j < entityArray.length; j++)
			if (i == entityArrayHealth[j].getHP()) {
				entityArray[j].hp = i;
			}
		}
		System.out.println(entityArray[1].getHP());
		Entity[] initiativeArray = createInitiativeArray();
		while (checkMonstersAlive() && checkPlayersAlive()) {
			round += 1;
			System.out.println("HELLLOOOOO");
			System.out.println("This is round " + round);
			for (int i = 0; i < entityArray.length; i++) {
				takeTurn(initiativeArray[i]);
			}
		}
		System.out.println("The fight ended on round " + round + " because:");
		System.out.println("monstersAlive? " + checkMonstersAlive() + " playersAlive? " + checkPlayersAlive());
		if (!checkMonstersAlive() & checkPlayersAlive()) {
			System.out.println("All of the monsters died.");
		}
		if (checkMonstersAlive() & !checkPlayersAlive()) {
			System.out.println("All of the players died.");
		}
		for (int i = 0; i < entityArray.length; i++) {
			System.out.println(entityArray[i].getName() + " end with " + entityArray[i].hp + " health.");
		}
	}
}
