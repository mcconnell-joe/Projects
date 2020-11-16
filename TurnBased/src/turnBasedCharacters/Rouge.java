package turnBasedCharacters;

import java.util.ArrayList;

public class Rouge extends PlayerHero
{
	
	public Rouge(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		setType("Rouge");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void displayAbilites()
	{
		System.out.println("1) Attack");
		System.out.println("2) BackStab");
	}
	@Override
	public void abilityOne(ArrayList<GameCharacter> team)
	{
		int choice = GameCharacter.displayTargets(team);
		int damage = this.calDamage() * 2;
		GameCharacter target = team.get(choice);
		boolean successfulHit = (Math.random() <= this.getChanceToHit() * .65);
		
		if(successfulHit)
		{
			target.doDamage(damage);
			if(target.isDead())
			{
				System.out.println(this.getName() + " successfully landed a back stab on " + target.getName() + " and did " +
						damage + " damage killing them!");
				team.remove(target);
			}
			else
			{
				System.out.println(this.getName() + " successfully landed a back stab on " + target.getName() + " and did " +
						damage + " damage!");
			}
		}
		else
		{
			System.out.println(this.getName() + " failed to back stab " + target.getName() + "!");
		}
	}
	@Override
	public void abilityTwo(ArrayList<GameCharacter> team)
	{
		System.out.println("No ability 2 yet! Coming Soon!");
	}
}
