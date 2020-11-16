package Enemies;

import java.util.ArrayList;

import teams.Team;
import turnBasedCharacters.GameCharacter;

public class Skeleton extends GameCharacter
{

	public Skeleton(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		super.setType("Skeleton");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void abilityOne(ArrayList<GameCharacter> team)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void abilityTwo(ArrayList<GameCharacter> team)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayAbilites()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void attack(Team team)
	{
		GameCharacter target = team.getRandom();
		System.out.println(getName() + " the " + getType() + " wildly swings his sword slicing at " + target.getName() + "!");
		super.targetAttack(target, team.getTeamArray());
	}
	
}
