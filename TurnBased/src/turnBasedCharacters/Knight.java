package turnBasedCharacters;

import java.util.ArrayList;

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
		System.out.println("No ability 2 yet! Coming Soon!");
	}



}
