package turnBasedCharacters;

import java.util.ArrayList;

public class Mage extends PlayerHero
{
	protected int healPwr;

	public Mage(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		this.healPwr = 10;
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
		
		if(successfulHit)
		{
			target.heal(this.getHealPwr());
			System.out.println(this.getName() + " healed " + target.getName() + " for " + this.getHealPwr() + " his health is now " + target.getHealth() + "!");
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
			successfulHit = Math.random() <= (this.getChanceToHit() *.9);
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
	
	public int getHealPwr()
	{
		return this.healPwr;
	}
}
