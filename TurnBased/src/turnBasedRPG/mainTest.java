package turnBasedRPG;

import java.util.ArrayList;
import java.util.Scanner;

import Factories.HeroFactory;
import teams.EnemyTeamGenerator;
import teams.Team;
import turnBasedCharacters.GameCharacter;

public class mainTest
{
	public static void main(String[] args)
	{
		
		//CREATE VARIABLES//
		
		
		ArrayList<GameCharacter> heroes = new ArrayList<GameCharacter>();
		EnemyTeamGenerator enemyTeamGenerator = new EnemyTeamGenerator();
		GameManager gameManager = new GameManager();


		do
		{
		
		//Fill hero arrayList
		createTeam(heroes);
		
		System.out.print("\n");
		System.out.println("You've successfully created a team!\n");

		heroes.trimToSize();
		Team heroTeam = new Team(heroes);
		Team enemyTeam = enemyTeamGenerator.createEnemyTeam(heroTeam);
		
		//Initiate a battle between the Hero and Enemy Teams
		gameManager.Battle(enemyTeam, heroTeam);
		
		}while(playAgain());
		
		System.out.println("Thank you for playing!");
		

	}
	
	public static void createTeam(ArrayList<GameCharacter> heroes)
	{
		HeroFactory heroFactory = new HeroFactory();
		int count = 1, choice;
		Scanner kb = SingletonScanner.getScanner();
		
		do 
		{
			System.out.println("Pick the " + count +  " memeber of your party: ");
			System.out.println("Enter a 1 for a Knight:");
			System.out.println("Enter a 2 for a Rouge: ");
			System.out.println("Enter a 3 for a Mage: ");
			choice = Integer.parseInt(kb.nextLine());
			if(choice > 0 && choice < 4)
			{
				heroes.add(heroFactory.getHero(choice));
				count++;
			}
		}while(count < 4);
	}
		

	
	public static boolean playAgain()
	{
		Scanner kb = SingletonScanner.getScanner();
		
		
		char again;
		do
		{
			System.out.println("Play again y/n?");
			again = kb.next().charAt(0);
			kb.nextLine();

		}while(again != 'y' && again != 'Y' && again != 'n' && again != 'N');
		
		
		return (again == 'Y' || again == 'y');
	}
}

