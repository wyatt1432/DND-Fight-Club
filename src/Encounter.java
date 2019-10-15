public class Encounter {
	
	int round = 0;
	boolean death = false;
	double tpk;
	Entity[] entityArray;
	Entity[] entityArrayHealth;
	int strategy = 2;
	int numPlayers;
	int numPlayersVer;
	int numMonsters;
	int numEntities = numPlayers + numMonsters;
	double totalRounds;
	double monstersLost;
	double totalIsAnyPlayerDead;
	double bothDied;
	double totalMonsterInitiative;
	double totalPlayerInitiative;
	double playerWentFirst;
	double totalPlayerAC;
	double totalMonsterAC;
	
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
		if (entityArray[index].getIsAlive() == false) {
			index = pickRandomAliveMonster();
		}
		return index;
	}
	
	public int pickRandomAlivePlayer() {
		int index = randomInt(0, numPlayers - 1);
		if (!checkPlayersAlive()) {
			System.out.println("pickRandomPlayer was called when all players are dead.");
		}
		if (entityArray[index].getIsAlive() == false) {
			index = pickRandomAlivePlayer();
		}
		return index;
	}
	
	public boolean checkMonstersAlive() {
		boolean isAnyMonsterAlive = false;
		for (int i = numPlayers; i <= (entityArray.length - 1); i++) {
			if (entityArray[i].getIsAlive() == true) {
				isAnyMonsterAlive = true;
			}
		}
		return isAnyMonsterAlive;
	}
	
	public boolean checkPlayersAlive() {
		boolean isAnyPlayerAlive = false;
		for (int i = 0; i < numPlayers; i++) {
			if (entityArray[i].getIsAlive() == true) {
				isAnyPlayerAlive = true;
			}
		}
		return isAnyPlayerAlive;
	}
	
	public boolean checkAnyPlayerDeath() {
		boolean isAnyPlayerDead = false;
		for (int i = 0; i < numPlayers; i++) {
			if (entityArray[i].getIsAlive() == false) {
				isAnyPlayerDead= true;
			}
		}
		return isAnyPlayerDead;
	}
	
	public Entity findLowestMonster() {
		double lowestHP = 500;
		int lowestHPIndex = 0;
		for (int i = numPlayers; i <= (entityArray.length - 1); i++) {
			if (entityArray[i].getHP() < lowestHP && entityArray[i].getIsAlive()) {
				lowestHPIndex = i;
				lowestHP = entityArray[i].getHP();
			}
		}
		return entityArray[lowestHPIndex];
	}
		
		public Entity findLowestPlayer() {
			double lowestHP = 500;
			int lowestHPIndex = 0;
			for (int i = 0; i < numPlayers; i++) {
				if (entityArray[i].getHP() < lowestHP && entityArray[i].getIsAlive()) {
					lowestHPIndex = i;
					lowestHP = entityArray[i].getHP();
				}
			}
			return entityArray[lowestHPIndex];
		}
	
	public void takeTurn(Entity actor) {
		for (int i = 0; i < actor.attacks; i++) {
			if (checkMonstersAlive() && checkPlayersAlive()) {
				if (actor.isPlayer && actor.getIsAlive()) {
					if (strategy == 1) {
						Entity victim = entityArray[pickRandomAliveMonster()];
						if (victim.isHitBy(actor)) {
							double damage = actor.getRandomDamage();
							//System.out.println(damage);
							//double beforeHealth = victim.hp;
							victim.reduceHP(damage);
						}
					}
					if (strategy == 2) {
						Entity victim = findLowestMonster();
						if (victim.isHitBy(actor)) {
							double damage = actor.getRandomDamage();
							//System.out.println(damage);
							//double beforeHealth = victim.hp;
							victim.reduceHP(damage);
						}
					}
				}
				if (actor.isMonster && actor.getIsAlive()) {
					if (strategy == 1) {
						Entity victim = entityArray[pickRandomAlivePlayer()];
						if (victim.isHitBy(actor)) {
							double damage = actor.getRandomDamage();
							//System.out.println(damage);
							//double beforeHealth = victim.hp;
							victim.reduceHP(damage);
						}
					}
					if (strategy == 2) {
						Entity victim = findLowestPlayer();
						if (victim.isHitBy(actor)) {
							double damage = actor.getRandomDamage();
							//System.out.println(damage);
							//double beforeHealth = victim.hp;
							victim.reduceHP(damage);
						}
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
			//System.out.println(entityArray[i].getName() + " has an initiative of " + entityArray[i].getInitiative() + " and a starting health of " + entityArray[i].getHP());
		}
		//decides initiative
		Entity[] initiativeArray = new Entity[entityArray.length];
		int highestInitiative = -5;
		int highestInitiativeIndex = -5;
		for (int i = 0; i < entityArray.length; i++) {
			if (entityArray[i].getIsMonster()) {
				totalMonsterInitiative += entityArray[i].getInitiative();
			}
			if (entityArray[i].getIsPlayer()) {
				totalPlayerInitiative += entityArray[i].getInitiative();
			}
		}
		for (int i = 0; i < entityArray.length; i++) {
			for (int j = 0; j < entityArray.length; j++) {
				if (entityArray[j].getInitiative() == highestInitiative && entityArray[j].getInitiative() < 49) {
					//System.out.println(entityArray[j].getName() +  " is a duplicate of " + entityArray[highestInitiativeIndex].getName());
					if (randomInt(1,2) == 2) {
						highestInitiative = entityArray[j].initiative;
						highestInitiativeIndex = j;
					}
				} else if (entityArray[j].getInitiative() >= highestInitiative && entityArray[j].getInitiative() < 49) {
					highestInitiative = entityArray[j].initiative;
					highestInitiativeIndex = j;
				}
			}
			initiativeArray[i] = entityArray[highestInitiativeIndex];
			entityArray[highestInitiativeIndex].initiative = 49;
			highestInitiative = -5;
		}
		//System.out.println("The intiative order is:");
		for (int i = 0; i < entityArray.length; i++) {
			//System.out.println(initiativeArray[i].getName());
		}
		return initiativeArray;
		//makes an array ordered by initiative
	}
	
	public void runEncounter() {
		//System.out.println("There were this many players: " + numMonsters);
		numPlayersVer = 0;
		for (int i = 0; i < entityArray.length; i++) {
			if (entityArray[i].isMonster) {
				numPlayersVer += 1;
			}
		}
		//System.out.println("To verify, there was this many players detected: " + numPlayersVer);
		for (double i = 0; i < 1000; i++) {
			for (int j = 0; j < entityArray.length; j++)
			if (i == entityArrayHealth[j].getHP()) {
				entityArray[j].hp = i;
			}
		}
		for (int i = 0; i < entityArray.length; i++) {
				entityArray[i].alive = true;
			}
		Entity[] initiativeArray = createInitiativeArray();
		if (initiativeArray[0].getIsPlayer()) {
			playerWentFirst += 1;
		}
		round = 0;
		while (checkMonstersAlive() && checkPlayersAlive()) {
			round += 1;
			totalRounds += 1;
			//System.out.println("This is round " + round);
			for (int i = 0; i < entityArray.length; i++) {
				takeTurn(initiativeArray[i]);
			}
		}
		//System.out.println("The fight ended on round " + round + " because:");
		//System.out.println("monstersAlive? " + checkMonstersAlive() + " playersAlive? " + checkPlayersAlive());
		if (!checkMonstersAlive() & !checkPlayersAlive()) {
			//System.out.println("All of the monsters died.");
			bothDied += 1;
		}
		if (!checkMonstersAlive() & checkPlayersAlive()) {
			//System.out.println("All of the monsters died.");
			monstersLost += 1;
		}
		if (checkMonstersAlive() & !checkPlayersAlive()) {
			//System.out.println("All of the players died.");
			tpk += 1;
		}
		for (int i = 0; i < entityArray.length; i++) {
			//System.out.println(entityArray[i].getName() + " end with " + entityArray[i].hp + " health.");
		}
		if (checkAnyPlayerDeath() && checkPlayersAlive()) {
			totalIsAnyPlayerDead += 1;
			for (int i = 0; i < entityArray.length; i++) {
				//System.out.println(entityArray[i].getName() + " end with " + entityArray[i].hp + " health.");
			}
		}
	}
}
