package turnBasedRPG;
import java.util.ArrayList;
import java.util.Scanner;

import teams.EnemyTeamGenerator;
import teams.Team;
import turnBasedCharacters.GameCharacter;
public class GameManager
{
	Scanner kb = SingletonScanner.getScanner();

	public void Battle(Team heroTeam)
	{
		EnemyTeamGenerator enemyTeamGenerator = new EnemyTeamGenerator();
		Team enemyTeam = enemyTeamGenerator.createEnemyTeam(heroTeam);
		
		//Get the arrayList from each team
		ArrayList<GameCharacter> heroes = heroTeam.getTeamArray();
		ArrayList<GameCharacter> baddies = enemyTeam.getTeamArray();

		System.out.println("You've entered a BATTLE!\n");
		
		//Run while each team has at least one team member alive
		while(!enemyTeam.teamDead() && !heroTeam.teamDead())
		{
			for(int x = 0; x < heroes.size(); ++x)
			{
				if(enemyTeam.teamDead())
				{
					break;
				}
				GameCharacter hero = heroes.get(x);
				hero.getStatusManager().checkStatuses();
				if(hero.getCanAttack()) {
					System.out.println("It's " + hero.getName() + " the " + hero.getType() + "'s turn!");
					System.out.println(hero.getName() + "'s abilites are: ");
					hero.displayAbilites();
					int ability;
					do 
					{
						System.out.println("Enter ability choice: ");
						ability = Integer.parseInt(kb.nextLine());
						System.out.println("");
					}while(ability < 1 || ability > 3);
					
					callAbility(ability, hero, enemyTeam, heroTeam);
				}
				else {
					hero.getStatusManager().checkStatuses();
				}
	
			}
			
			System.out.println("\n");
			//Have the enemy team attack the lowest health hero
			for(int y = 0; y < baddies.size(); y++)
			{
				GameCharacter attacker = baddies.get(y);
				if(attacker.getCanAttack()) {
					attacker.getStatusManager().checkStatuses();
					attacker.attack(heroTeam);
				}
				else {
					attacker.getStatusManager().checkStatuses();
				}
				
			}
			
			
			System.out.print("\n");
		}
		System.out.println("You won the battle!");
	}
	
	//Ensures that the correct abilty is called for each hero
	public static void callAbility(int ability, GameCharacter hero, Team enemies, Team heroes)
	{
		if(ability == 1)
		{
			hero.attack(enemies);;
		}
		else if(ability == 2)
		{
			if(hero.getType() == "Mage")
			{
				hero.abilityOne(heroes.getTeamArray());
			}
			else
			{
				hero.abilityOne(enemies.getTeamArray());
			}
			
		}
		else if(ability == 3)
		{
			hero.abilityTwo(enemies.getTeamArray());
		}
		else
		{
			System.out.print("Invalid Choice!");
		}
			
	}
}
	
