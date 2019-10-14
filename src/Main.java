import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
			
		double totalInterations = 1000;
		double totalRounds;
		double totalPlayerKilled;
		double totalTPK;
		
		entityArrayMaker myEntityArrayMaker = new entityArrayMaker();
		Encounter myEncounter = new Encounter(myEntityArrayMaker.constructArray(), myEntityArrayMaker.entityArrayHealth, myEntityArrayMaker.mNumPlayers, myEntityArrayMaker.mNumMonsters);
		for (int i = 0; i < 4; i++) {
			System.out.println("This is fight " + (i + 1));
			myEncounter.runEncounter();
		}
		
		
	}

}
