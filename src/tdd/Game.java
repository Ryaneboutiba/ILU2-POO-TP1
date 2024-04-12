package tdd;

public class Game {
	
	int[] lancerScore=new int[21];
	int numeroLancer=0;
	
	
	public void roll(int quillesAbattus) {
		lancerScore[numeroLancer]=quillesAbattus;
		numeroLancer++;
	}
	
	public int score() {
		int score=0;
		for(int i=0;i<21;i++) {
			score+=lancerScore[i];
		}
		return score;
	}
}
