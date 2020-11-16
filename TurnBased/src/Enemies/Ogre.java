package Enemies;

import java.util.ArrayList;

import teams.Team;
import turnBasedCharacters.GameCharacter;

public class Ogre extends GameCharacter
{
	public Ogre(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		super.setType("Ogre");
	}

	@Override
	public void abilityOne(ArrayList<GameCharacter> team)
	{
		System.out.println("Error:");
		
	}

	@Override
	public void abilityTwo(ArrayList<GameCharacter> team)
	{
		// TODO Auto-generated method stub
		System.out.println("Error:");
	}

	@Override
	public void displayAbilites()
	{
		// TODO Auto-generated method stub
		System.out.println("Error:");
	}
	public void attack(Team team)
	{
		GameCharacter target = team.getRandom();
		System.out.println(getName() + " the " + getType() + " slams his club at " + target.getName() + "!");
		super.targetAttack(target, team.getTeamArray());
	}
	

}
