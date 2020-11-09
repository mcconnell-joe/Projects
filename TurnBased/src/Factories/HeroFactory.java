package Factories;

import turnBasedCharacters.Knight;
import turnBasedCharacters.Mage;
import turnBasedCharacters.PlayerHero;
import turnBasedCharacters.Rouge;

public class HeroFactory
{

	public PlayerHero getHero(int choice)
	{
		if(choice == 1)
		{
			Knight knight = new Knight("Sir Reginald", 0);
			System.out.println(knight.getName() + " the " + knight.getType() + " was just added to your party!");
			return knight;
		}
		else if(choice == 2)
		{
			Rouge rouge = new Rouge("Sneaky Boy", 0);
			System.out.println(rouge.getName() + " the " + rouge.getType() + " was just added to your party!");
			return rouge;
		}
		else if(choice == 3)
		{
			Mage mage = new Mage("Magic Man", 0);
			System.out.println(mage.getName() + " the " + mage.getType() + " was just added to your party!");
			return mage;
		}
		else
		{
			System.out.println("Please enter a correct choice: ");
			return null;
		}
	}
}
