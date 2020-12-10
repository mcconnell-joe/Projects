package status;

import turnBasedCharacters.GameCharacter;

public class Stun extends Status
{
	public Stun(int tics, GameCharacter target) {
		super(tics, target);
		super.getTarget().setCanAttack(false);
	}
	
	public void doAction() {
			System.out.println(super.getTarget().getName() + " is stunned and can not act.");
			super.decTics();
			if(super.getTics() == 0) {		
				super.getTarget().setCanAttack(true);
			}
	}
}
