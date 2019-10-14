import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
			
		double totalInterations = 100000;
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
		//System.out.println("Using " + totalInterations + " total interations:");
		//System.out.println("A TPK occured " + ((myEncounter.tpk / totalInterations) * 100) + "% of the time.");
		//System.out.println("At least 1 player was killed " + ((myEncounter.totalIsAnyPlayerDead / totalInterations) * 100) + "% of the time.");
		//System.out.println("The average player initative was " + ((((double)myEncounter.totalPlayerInitiative)/myEncounter.numPlayers) / totalInterations) + ".");
		//System.out.println("The average monster initative was " + ((((double)myEncounter.totalMonsterInitiative)/myEncounter.numMonsters) / totalInterations) + ".");
		//System.out.println("A player went first " + ((((double)myEncounter.playerWentFirst) / totalInterations) * 100) + "% of the time. (Perfectly random: " + (((double)myEncounter.numPlayers / ((double)myEncounter.numPlayers + (double)myEncounter.numMonsters))) * 100 + "%)");
		//System.out.println("On average, a battle took " + (myEncounter.totalRounds / totalInterations) + " rounds.");
		
		System.out.println("Using " + totalInterations + " total interations:");
		System.out.println(((myEncounter.tpk / totalInterations) * 100) + "% TPK");
		System.out.println(((myEncounter.totalIsAnyPlayerDead / totalInterations) * 100) + "% > 1 player death");
		System.out.println(((((double)myEncounter.totalPlayerInitiative)/myEncounter.numPlayers) / totalInterations) + " average player initiative");
		System.out.println(((((double)myEncounter.totalMonsterInitiative)/myEncounter.numMonsters) / totalInterations) + " average monster initiative.");
		System.out.println(((((double)myEncounter.playerWentFirst) / totalInterations) * 100) + "% player went first. (Perfectly random: " + (((double)myEncounter.numPlayers / ((double)myEncounter.numPlayers + (double)myEncounter.numMonsters))) * 100 + "%)");
		System.out.println((myEncounter.totalRounds / totalInterations) + " average length");
		
		
		
	}

}
