package Enemies;

import java.util.ArrayList;

import teams.Team;
import turnBasedCharacters.GameCharacter;

public class Orc extends GameCharacter
{

	public Orc(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		super.setType("Orc");
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
		GameCharacter target = team.getLowestHealth();
		System.out.println(getName() + " the " + getType() + " jumps behind and quickly slashes " + target.getName() + "!");
		super.targetAttack(target, team.getTeamArray());
	}
}
