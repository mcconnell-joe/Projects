package Enemies;

import java.util.ArrayList;

import turnBasedCharacters.GameCharacter;

public class Enemy extends GameCharacter
{
	public Enemy(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		this.setType("Enemy");
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
	



}
