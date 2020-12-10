package status;

import turnBasedCharacters.GameCharacter;

public abstract class Status
{
	private int tics;
	private GameCharacter target;
	
	public Status(int tics, GameCharacter target) {
		this.tics = tics;
		this.target = target;
	}
	
	
	public void doAction() {
		
	}
	
	
	
	//Getters and Setters
	public int getTics() {
		return tics;
	}
	public void decTics() {
		tics--;
	}
	public GameCharacter getTarget() {
		return this.target;
	}
	public void zeroTics() {
		this.tics = 0;
	}
}
