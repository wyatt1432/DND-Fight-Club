import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		String mSNumPlayers;
		int mNumPlayers;
		String mSNumMonsters;
		int mNumMonsters;
		int mNumEntities;
		mSNumPlayers = JOptionPane.showInputDialog("Number of Players");
		mNumPlayers = Integer.parseInt(mSNumPlayers);
		mSNumMonsters = JOptionPane.showInputDialog("Number of Monsters");
		mNumMonsters = Integer.parseInt(mSNumMonsters);
		mNumEntities = mNumPlayers + mNumMonsters;
		Entity[] entityArray = new Entity[mNumEntities];
		for (int i = 0; i < mNumEntities; i++) {
			entityArray[i] = new Entity();
			if (i < mNumPlayers) {
				String newName = JOptionPane.showInputDialog("What is the name of Player " + (i + 1) + "?");
				entityArray[i].setName(newName);
				entityArray[i].isPlayer = true;
				entityArray[i].isMonster = false;
			}
			if (i >= mNumPlayers) {
				entityArray[i].name = JOptionPane.showInputDialog("What is the name of Monster " + (i + 1 - mNumPlayers) + "?");
				entityArray[i].isPlayer = false;
				entityArray[i].isMonster = true;
			}
			String acString = JOptionPane.showInputDialog("What is the AC of " + entityArray[i].name + "?");
			int acInt = Integer.parseInt(acString);
			entityArray[i].ac = acInt;
			//AC
			String hpString = JOptionPane.showInputDialog("What is the HP of " + entityArray[i].name + "?");
			double hpDouble = Double.valueOf(hpString);
			entityArray[i].hp = hpDouble;
			//HP
			String numOfAttackDiceString = JOptionPane.showInputDialog("What is the number of attack dice of " + entityArray[i].name + "?");
			int numOfAttackDiceInt = Integer.parseInt(numOfAttackDiceString);
			entityArray[i].attackDiceNum = numOfAttackDiceInt;
			//Num of attack dice
			String sidesOfAttackDiceString = JOptionPane.showInputDialog("What is the number of sides on the attack dice of " + entityArray[i].name + "?");
			int sidesOfAttackDiceInt = Integer.parseInt(sidesOfAttackDiceString);
			entityArray[i].attackDiceSides = sidesOfAttackDiceInt;
			//Sides of attack dice
			String attackBonusString = JOptionPane.showInputDialog("What is the attack bonus of " + entityArray[i].name + "?");
			int attackBonusInt = Integer.parseInt(attackBonusString);
			entityArray[i].attackDiceSides = attackBonusInt;
			//Attack Bonus
			String numOfAttacksString = JOptionPane.showInputDialog("What is the number of sides on the attack dice of " + entityArray[i].name + "?");
			int numOfAttacksInt = Integer.parseInt(numOfAttacksString);
			entityArray[i].attacks = numOfAttacksInt;
			//Number of Attacks
			String toHitString = JOptionPane.showInputDialog("What is ths to hit bonus of " + entityArray[i].name + "?");
			int toHitInt = Integer.parseInt(toHitString);
			entityArray[i].attacks = toHitInt;
			//to hit
			String rangeString = JOptionPane.showInputDialog("Is " + entityArray[i].name + " melee (1) or ranged (2)?");
			int rangeInt = Integer.parseInt(rangeString);
			entityArray[i].range = rangeInt;
			// 1 for melee 2 for range
		}
		
		//NEED TO SET NUMBER OF PLAYERS AND MONSTERS AND ENTITIES INTO ENCOUNTER BEFORE YOU RUN
		
		double totalInterations = 1000;
		double totalRounds;
		double totalPlayerKilled;
		double totalTPK;
		
		Encounter myEncounter = new Encounter(entityArray, mNumPlayers, mNumMonsters);
		System.out.println("All of the players died.");
		myEncounter.runEncounter();
		
		
	}

}
