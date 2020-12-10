package turnBasedCharacters;

import java.util.ArrayList;
import java.util.Scanner;

import status.StatusManager;
import teams.Team;
import turnBasedRPG.SingletonScanner;

public abstract class GameCharacter
{
	private String name;
	private int health;
	private String type;
	private double hitChance;
	private int minDmg, maxDmg;
	protected StatusManager statusMg;
	protected boolean canAttack = true;
	
	public GameCharacter(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		this.name = name;
		this.health = health;
		this.hitChance = hitChance;
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
		this.statusMg = new StatusManager();
	}

	//Healing method for Mage and various monsters
	public void heal(int heal)
	{
		this.health += heal;
	}
	
	public void doDamage(int damage)
	{
		if(damage < 0)
		{
			System.out.println("Damage must be positive");
		}
		else if(damage > 0)
		{
			this.health -= damage;
			if(this.health < 0)
			{
				this.health = 0;
			}
		}
		
	}
	//Notification and actions//
	public boolean isDead()
	{
		return(this.health <= 0);
	}
	//Target attack used for the enemy when they attack
	public void targetAttack(GameCharacter target, ArrayList<GameCharacter> team)
	{
		boolean successfulAttack = Math.random() <= hitChance;
		int damage = calDamage();
		
		if(successfulAttack)
		{
			target.doDamage(damage);
			if(target.isDead())
			{
				System.out.println(this.getName() + "'s attack was a fatal blow! " 
						+ target.getName() + " has been killed!\n");
				team.remove(target);
			}
			else
			{
				System.out.println("The attack was successful doing " + damage + "!");
				System.out.println(target.getName() + " has " + target.health + " health remaining.\n");
			}
		}
		else
		{
			System.out.println(this.getName() + " misses the attack!\n");
		}
		
	}
	//Typical attack for heroes, it then calls target attack after getting the target from user input
	public void attack(Team team)
	{
		ArrayList<GameCharacter> teamA = team.getTeamArray();
		int choice = displayTargets(teamA);
		GameCharacter target = teamA.get(choice);
		
		targetAttack(target, team.getTeamArray());
	}
	
	public abstract void abilityOne(ArrayList<GameCharacter> team);
	public abstract void abilityTwo(ArrayList<GameCharacter> team);
	public abstract void displayAbilites();
	//This method displays all choices to attack in a team ArrayList
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
	public boolean getCanAttack()
	{
		return canAttack;
	}
	public StatusManager getStatusManager()
	{
		return this.statusMg;
	}


	//SETTERS//
	public void setType(String type)
	{
		this.type = type;
	}
	public void setCanAttack(boolean bool) {
		this.canAttack = bool;
	}

	
	


}
