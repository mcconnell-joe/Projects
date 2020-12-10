package status;

import java.util.ArrayList;

import turnBasedCharacters.GameCharacter;

public class Poison extends Status
{
	private int damage;
	private ArrayList<GameCharacter> team;
	
	public Poison(int tics, GameCharacter target, int damage, ArrayList team)
	{
		super(tics + 1, target);
		this.damage = damage;
		
	}
	
	public void doAction() {
		super.getTarget().doDamage(damage);
		if(super.getTarget().isDead())
		{
			System.out.println(super.getTarget().getName() + " took lethal poison damage of poison, they died.");
			super.zeroTics();
			team.remove(super.getTarget());
		}
		else
		{
			String str = super.getTarget().getName() + " has taken " + this.damage + " poison damage.";
			super.decTics();
			if(super.getTics() < 0)
			{
				str += " They are no longer poisoned.";
			}
			System.out.println(str);
		}
	}
}
