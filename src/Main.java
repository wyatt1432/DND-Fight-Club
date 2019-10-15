import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
			
		double totalInterations = 1000;
		double totalRounds;
		double totalPlayerKilled = 0;
		double totalTPK;
		
		entityArrayMaker myEntityArrayMaker = new entityArrayMaker();
		Encounter myEncounter = new Encounter(myEntityArrayMaker.constructArray(), myEntityArrayMaker.entityArrayHealth, myEntityArrayMaker.mNumPlayers, myEntityArrayMaker.mNumMonsters);
		for (int i = 0; i < totalInterations; i++) {
			myEncounter.runEncounter();
			for (int j = 0; j < myEncounter.entityArray.length; j++) {
				//System.out.println(myEncounter.entityArray[j].getName() + " end with " + myEncounter.entityArray[j].hp + " health.");
			}
		}
		
		Entity lowestMonster = myEncounter.findLowestMonster();
		Entity lowestPlayer = myEncounter.findLowestPlayer();
		System.out.println(myEncounter.findLowestMonster().getName());
		System.out.println(myEncounter.findLowestPlayer().getName());
		
		System.out.println("Using " + (int)totalInterations + " total interations:");
		System.out.println(String.format("%1$,.2f", ((myEncounter.tpk / totalInterations) * 100)) + "% TPK");
		System.out.println(String.format("%1$,.2f", ((myEncounter.totalIsAnyPlayerDead / totalInterations) * 100)) + "% > 1 player death");
		System.out.println(String.format("%1$,.2f", ((((double)myEncounter.totalPlayerInitiative)/myEncounter.numPlayers) / totalInterations)) + " average player initiative");
		System.out.println(String.format("%1$,.2f", ((((double)myEncounter.totalMonsterInitiative)/myEncounter.numMonsters) / totalInterations)) + " average monster initiative.");
		System.out.println(String.format("%1$,.2f", ((((double)myEncounter.playerWentFirst) / totalInterations) * 100)) + "% player went first. (Perfectly random: " + String.format("%1$,.2f", (((double)myEncounter.numPlayers / ((double)myEncounter.numPlayers + (double)myEncounter.numMonsters))) * 100) + "%)");
		System.out.println(String.format("%1$,.2f", (myEncounter.totalRounds / totalInterations)) + " average length");
		
		
		
	}

}
