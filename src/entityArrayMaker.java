import javax.swing.JOptionPane;

public class entityArrayMaker {
	
	String mSNumPlayers;
	int mNumPlayers;
	String mSNumMonsters;
	int mNumMonsters;
	int mNumEntities;
	boolean test = false;
	Entity entityArray[];
	Entity entityArrayHealth[];
	double hpDoubleCopy = 0;
	
	public double getHPDoubleCopy() {
		return hpDoubleCopy;
	}
	
	public Entity[] constructArray() {
		String testString = JOptionPane.showInputDialog("Is this a test? (1 is true 0 is false)");
		int testInt = Integer.parseInt(testString);
		if (testInt == 0) {
			test = false;
		} else {
			test = true;
		}
		
		if (test == false) {
			mSNumPlayers = JOptionPane.showInputDialog("Number of Players");
			mNumPlayers = Integer.parseInt(mSNumPlayers);
			mSNumMonsters = JOptionPane.showInputDialog("Number of Monsters");
			mNumMonsters = Integer.parseInt(mSNumMonsters);
			mNumEntities = mNumPlayers + mNumMonsters;
			entityArray = new Entity[mNumEntities];
			entityArrayHealth = new Entity[mNumEntities];
			for (int i = 0; i < mNumEntities; i++) {
				entityArray[i] = new Entity();
				entityArrayHealth[i] = new Entity();
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
				hpDoubleCopy = hpDouble;
				entityArray[i].hp = hpDouble;
				entityArrayHealth[i].hp = hpDouble;
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
				String numOfAttacksString = JOptionPane.showInputDialog("What is the number of attacks that " + entityArray[i].name + " can do per turn?");
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
			return entityArray;
		} else {
			mNumPlayers = 5;
			mNumMonsters = 5;
			mNumEntities = mNumPlayers + mNumMonsters;
			entityArray = new Entity[mNumEntities];
			entityArrayHealth = new Entity[mNumEntities];
			for (int i = 0; i < mNumEntities; i++) {
				entityArray[i] = new Entity();
				entityArrayHealth[i] = new Entity();
				if (i < mNumPlayers) {
					String newName = "p" + (i + 1);
					entityArray[i].setName(newName);
					entityArray[i].isPlayer = true;
					entityArray[i].isMonster = false;
				}
				if (i >= mNumPlayers) {
					entityArray[i].name = "m" + (i - mNumPlayers + 1);
					entityArray[i].isPlayer = false;
					entityArray[i].isMonster = true;
				}
				int acInt = 15;
				entityArray[i].ac = acInt;
				//AC
				double hpDouble = 30;
				hpDoubleCopy = hpDouble;
				entityArray[i].hp = hpDouble;
				entityArrayHealth[i].hp = hpDoubleCopy;
				//HP
				int numOfAttackDiceInt = 2;
				entityArray[i].attackDiceNum = numOfAttackDiceInt;
				//Num of attack dice
				int sidesOfAttackDiceInt = 8;
				entityArray[i].attackDiceSides = sidesOfAttackDiceInt;
				//Sides of attack dice
				int attackBonusInt = 2;
				entityArray[i].attackDiceSides = attackBonusInt;
				//Attack Bonus
				int numOfAttacksInt = 2;
				entityArray[i].attacks = numOfAttacksInt;
				//Number of Attacks
				int toHitInt = 2;
				entityArray[i].attacks = toHitInt;
				//to hit
				int rangeInt = 1;
				entityArray[i].range = rangeInt;
				// 1 for melee 2 for range
			}	
			return entityArray;
		}
	}
}