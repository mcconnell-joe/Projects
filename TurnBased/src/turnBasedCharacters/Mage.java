package turnBasedCharacters;

import java.util.ArrayList;

public class Mage extends PlayerHero
{

	public Mage(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		setType("Mage");
	}

	@Override
	public void displayAbilites()
	{
		System.out.println("1) Attack");
		System.out.println("2) Heal ally");
		System.out.println("3) Metor Shower");
	}
	@Override
	public void abilityOne(ArrayList<GameCharacter> team)
	{
		
		boolean successfulHit = Math.random() <= this.getChanceToHit();
		int choice = GameCharacter.displayTargets(team);
		GameCharacter target = team.get(choice);
		int damage;
		
		if(successfulHit)
		{
			damage = (int)(this.calDamage() * .70);
			target.heal(damage);
			if(target.getName() == this.getName())
			{
				System.out.println(this.getName() + " healed himself for " + damage + " his health is now " + target.getHealth() + "!");
			}
			else {
				System.out.println(this.getName() + " healed " + target.getName() + " for " + damage + " his health is now " + target.getHealth() + "!");
			}
		}
		else
		{
			System.out.println(this.getName() + " failed to heal " + target.getName() + "!");
		}
		
	}
	@Override
	public void abilityTwo(ArrayList<GameCharacter> team)
	{
		boolean successfulHit;
		int damage;
	    GameCharacter target;
		for(int x = 0; x < team.size(); ++x)
		{
			target = team.get(x);
			successfulHit = Math.random() <= (this.getChanceToHit());
			damage = (int)(this.calDamage() * .75);
			if(successfulHit)
			{
				target.doDamage(damage);
				if(target.isDead())
				{
					System.out.println("Metor Shower hit " + team.get(x).getName() + " for " + damage + " and killed him!");
					team.remove(target);
				}
				else
				{
					System.out.println("Metor Shower hit " + team.get(x).getName() + " for " + damage + "!");
				}
				
			}
			else
			{
				System.out.println("Metor Shower missed " + target.getName() + "!");
			}
			
		}
	}
	

}
