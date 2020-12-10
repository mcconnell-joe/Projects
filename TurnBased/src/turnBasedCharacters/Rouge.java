package turnBasedCharacters;

import java.util.ArrayList;

import status.Poison;

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
		System.out.println("3) Poison Enemy");
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
		int tics = 5;
		int choice = GameCharacter.displayTargets(team);
		double damage = this.calDamage() * .25;
		GameCharacter target = team.get(choice);
		boolean successfulHit = (Math.random() <= this.getChanceToHit());
		
		if(successfulHit)
		{
			target.statusMg.addStatus(new Poison(tics, target, (int)damage, team));
			System.out.println(this.getName() + " has successfully poisoned " + target.getName() + " for " + tics + " turns");
		}
		else
		{
			System.out.println(this.getName() + " has failed to poison " + target.getName());
		}
	}
	
	
}	
