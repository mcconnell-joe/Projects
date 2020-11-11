package Factories;

import java.util.Scanner;

import turnBasedCharacters.Knight;
import turnBasedCharacters.Mage;
import turnBasedCharacters.PlayerHero;
import turnBasedCharacters.Rouge;
import turnBasedRPG.SingletonScanner;

public class HeroFactory
{
	private static int KNIGHT = 1;
	private static int ROUGE = 2;
	private static int MAGE = 3;

	public PlayerHero getHero(int choice)
	{
		String name;
		Scanner kb = SingletonScanner.getScanner();
		if(choice == KNIGHT)
		{
			Knight knight;
			System.out.println("What shall your knight be called? ");
			name = kb.nextLine();
			if(name.trim() == null || name.trim().isEmpty())
			{
				knight = new Knight("Sir Reginald The Brave", 100, .55, 7, 14);
			}
			else
			{
				knight = new Knight(name, 100, .55, 7, 14);
			}
			
			System.out.println(knight.getName() + " the knight was just added to your party!");
			return knight;
		}
		else if(choice == ROUGE)
		{
			Rouge rouge;
			System.out.println("What shall your rouge be called? ");
			name = kb.nextLine();
			
			if(name.trim() == null || name.trim().isEmpty())
			{
				rouge = new Rouge("Mr Sneaky Stabby", 100, .65, 8, 16);
			}
			else
			{
				rouge = new Rouge(name, 100, .65, 8, 16);
			}
			
			
			System.out.println(rouge.getName() + " the rouge was just added to your party!");
			return rouge;
		}
		else if(choice == MAGE)
		{
			System.out.println("What shall your mage be called? ");
			name = kb.nextLine();
			Mage mage;
			
			if(name.trim() == null || name.trim().isEmpty())
			{
				mage = new Mage("Magic Man", 75, .75, 6, 12);
			}
			else
			{
				mage = new Mage(name, 75, .75, 10, 19);
			}
			
			System.out.println(mage.getName() + " the mage was just added to your party!");
			return mage;
		}
		else
		{
			System.out.println("Please enter a correct choice: ");
			return null;
		}
	}
}
