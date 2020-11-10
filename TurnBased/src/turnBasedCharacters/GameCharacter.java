package turnBasedCharacters;

import java.util.ArrayList;
import java.util.Scanner;

import turnBasedRPG.SingletonScanner;

public abstract class GameCharacter
{
	private String name;
	private int health;
	private String type;
	private double hitChance;
	private int minDmg, maxDmg;

	public GameCharacter(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		this.name = name;
		this.health = health;
		this.hitChance = hitChance;
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
	}

	//Healing method for Mage and various monsters
	public void heal(int heal)
	{
		this.health += heal;
	}
	
	public void doDamage(int damage)
	{
		this.health -= damage;
	}
	//Notification and actions//
	public boolean isDead()
	{
		return(this.health <= 0);
	}
	public void enemyAttack(GameCharacter target, ArrayList<GameCharacter> team)
	{
		boolean successfulAttack = Math.random() <= hitChance;
		int damage = (int)(Math.random() * (maxDmg - minDmg + 1) + minDmg);
		
		if(successfulAttack)
		{
			target.doDamage(damage);
			if(target.isDead())
			{
				System.out.println(this.getName() + " attacks " + target.getName() + " and does " + damage + " damage, killing them!");
				team.remove(target);
			}
			else
			{
				System.out.println(this.getName() + " Attacks " + target.getName() + " and does " + damage + " damage, " 
						+ target.getName() + " now has " + target.getHealth() + " health remaining!");
			}
		}
		else
		{
			System.out.println(this.getName() + " attempted to attack " + target.getName() + " and Missed!");
		}	
		
	}
	
	public void attack(ArrayList<GameCharacter> team)
	{
		boolean successfulAttack;
		int damage, choice = displayTargets(team);
		GameCharacter target = team.get(choice);
		
		successfulAttack = Math.random() <= hitChance;
		
		if(successfulAttack)
		{
			damage = (int)(Math.random() * (maxDmg - minDmg + 1) + minDmg);
			target.doDamage(damage);
			
			if(target.isDead())
			{
				System.out.println(this.getName() + " attacks " + target.getName() + " and does " + damage + " damage, killing them!");
				team.remove(target);
			}
			else
			{
				System.out.println(this.getName() + " Attacks " + target.getName() + " and does " + damage + " damage, " 
						+ target.getName() + " now has " + target.getHealth() + " health remaining!");
			}
			
		}
		else
		{
			System.out.println(this.getName() + " attempted to attack " + target.getName() + " and Missed!");
		}	
	}
	public void displayAbilites()
	{
		
	}
	public void abilityOne(ArrayList<GameCharacter> team)
	{
		
	}
	
	public void abilityTwo(ArrayList<GameCharacter> team)
	{
		
	}
	
	public static int displayTargets(ArrayList<GameCharacter> team)
	{	
		Scanner kb = SingletonScanner.getScanner();
		int choice;
		GameCharacter target;
		System.out.println("Who is your target?");
		for(int x = 0; x < team.size(); ++x)
		{
			target = team.get(x);
			System.out.println((x+1) + ") " + target.getName() + " the " + target.getType() + " has " + target.getHealth() + " health.");
		}
		
		choice = Integer.parseInt(kb.nextLine());
		if(choice > team.size() || choice < 1)
		{
			do
			{
				System.out.println("You must enter a valid choice!");
				choice = Integer.parseInt(kb.nextLine());

			}while(choice > team.size() || choice <  1);
		}
		choice = choice - 1;
		return choice;
	}

	
	//GETTERS//
	public String getType()
	{
		return this.type;
	}

	public int getHealth()
	{
		return this.health;
	}

	public String getName()
	{
		return this.name;
	}
	
	public double getChanceToHit()
	{
		return this.hitChance;
	}
	public int calDamage()
	{
		return (int)((Math.random() * (maxDmg - minDmg + 1) + minDmg));
	}
	

	//SETTERS//
	public void setType(String type)
	{
		this.type = type;
	}

	
	


}
