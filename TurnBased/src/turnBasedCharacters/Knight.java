package turnBasedCharacters;

import java.util.ArrayList;

import status.Stun;

public class Knight extends PlayerHero
{

	public Knight(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		setType("Knight");
	}

	@Override
	public void displayAbilites()
	{
		System.out.println("1) Attack");
		System.out.println("2) Crushing Blow");
		System.out.println("3) Stunning Smack");
	}
	

	@Override
	public void abilityOne(ArrayList<GameCharacter> team)
	{
		int choice = GameCharacter.displayTargets(team);
		GameCharacter target = team.get(choice);
		int damage = (int)(this.calDamage() * 2);
		boolean successfulHit = Math.random() <= this.getChanceToHit() * .6;
		
		if(successfulHit)
		{
			target.doDamage(damage);
			if(target.isDead())
			{
				System.out.println(this.getName() + " landed a crushing blow on " + target.getName() +
						" doing " + damage + " damage killing them.");
				team.remove(target);
			}
			else
			{
				System.out.println(this.getName() + " successfully landed a crushing blow on " + target.getName()
				+ " and did " + damage + " damage!");
			}
			
		}
		else
		{
			System.out.println(this.getName() + " missed his attempt at using a crushing blow!");
		}
	}
	@Override
	public void abilityTwo(ArrayList<GameCharacter> team)
	{
		int tics = 1;
		int choice = GameCharacter.displayTargets(team);
		GameCharacter target = team.get(choice);
		boolean successfulHit = Math.random() <= this.getChanceToHit() * .75;
		
		if(successfulHit) {
			target.statusMg.addStatus(new Stun(tics, target));
			System.out.println(this.getName() + " has successfully stunned " + target.getName() + " for " + tics + " turns.");
		}
		else {
			System.out.println(this.getName() + " failed to stun " + target.getName() + ".");
		}
	}



}
