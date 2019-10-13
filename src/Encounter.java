public class Encounter {
	
	int round = 0;
	boolean death = false;
	boolean tbk = false;
	Entity[] entityArray;
	int strategy = 1;
	int numPlayers;
	int numMonsters;
	int numEntities;
	
	public Encounter(Entity[] entityArray, int numPlayers, int numMonsters) {
		this.entityArray = entityArray;
		this.numPlayers = numPlayers;
		this.numMonsters = numMonsters;
	}
	
	public static int randomInt(int min, int max){
	    int x = (int)(Math.random()*((max-min)+1))+min;
	    return x;
	}
	
	public int pickRandomAliveMonster() {
		int index = randomInt(numPlayers, entityArray.length - 1);
		if (entityArray[index].getAlive() == false) {
			index = pickRandomAliveMonster();
		}
		return index;
	}
	
	public int pickRandomAlivePlayer() {
		int index = randomInt(0, numPlayers - 1);
		if (entityArray[index].getAlive() == false) {
			index = pickRandomAlivePlayer();
		}
		return index;
	}
	
	public boolean checkMonstersAlive() {
		int aliveMonsters = numMonsters;
		for (int i = numPlayers; i <= (entityArray.length - 1); i++) {
			if (entityArray[i].getAlive() == false) {
				aliveMonsters -= 1;
			}
		}
		if (aliveMonsters > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkPlayersAlive() {
		int alivePlayers = numPlayers;
		for (int i = numPlayers; i <= (entityArray.length - 1); i++) {
			if (entityArray[i].getAlive() == false) {
				alivePlayers -= 1;
			}
		}
		if (alivePlayers > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void takeTurn(Entity actor) {
		if (strategy == 1) {
			if (actor.isPlayer == true) {
				for (int i = 0; i < actor.attacks; i++) {
					if (checkMonstersAlive() && checkPlayersAlive()) {
						entityArray[pickRandomAliveMonster()].reduceHP(actor.getRandomDamage());
					}
				}
			}
			if (actor.isMonster == true) {
				for (int i = 0; i < actor.attacks; i++) {
					if (checkMonstersAlive() && checkPlayersAlive()) {
						entityArray[pickRandomAlivePlayer()].reduceHP(actor.getRandomDamage());
					}
				}
			} else {
				System.out.println("Actor is neither a player nor monster");
			}
		}
	}	
	
	public Entity[] createInitiativeArray() {
		for (int i = 0; i < entityArray.length; i++) {
			entityArray[i].initiative = randomInt(1, 20);
			System.out.println(entityArray[i].getName() + " has an intative of " + entityArray[i].getInitiative());
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
		Entity[] initiativeArray = createInitiativeArray();
		while (checkMonstersAlive() && checkPlayersAlive()) {
			round += 1;
			System.out.println("This is round " + round);
			for (int i = 0; i < entityArray.length; i++) {
				takeTurn(initiativeArray[i]);
			}
		}
		System.out.println("The fight ended on round " + round + " because:");
		if (!checkMonstersAlive() & checkPlayersAlive()) {
			System.out.println("All of the monsters died.");
		}
		if (checkMonstersAlive() & !checkPlayersAlive()) {
			System.out.println("All of the players died.");
		}
		for (int i = 0; i < entityArray.length; i++) {
			System.out.println(entityArray[i].getName() + " end with " + entityArray[i].getHP() + " health.");
		}
	}
}
