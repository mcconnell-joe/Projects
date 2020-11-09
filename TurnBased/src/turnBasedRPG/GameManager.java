package turnBasedRPG;
import java.util.ArrayList;
import java.util.Scanner;

import teams.Team;
import turnBasedCharacters.GameCharacter;
public class GameManager
{
	Scanner kb = new Scanner(System.in);

	public void Battle(Team enemyTeam, Team heroTeam)
	{
		ArrayList<GameCharacter> heroes = heroTeam.getTeamArray();
		ArrayList<GameCharacter> baddies = enemyTeam.getTeamArray();

		System.out.println("You've entered a BATTLE!");

		while(!enemyTeam.teamDead() && !heroTeam.teamDead())
		{
			int choice = 0;
			for(int x = 0; x < heroes.size(); ++x)
			{
				GameCharacter hero = heroes.get(x);
				System.out.println("It's " + hero.getName() + "'s turn!");
				System.out.println("Which enemy do you want to attack?:\n");
				for(int z = 0; z < baddies.size(); ++z)
				{
					System.out.println((z + 1) + ": " + baddies.get(z).getName() + " Health: " + baddies.get(z).getHealth());
				}
				choice = Integer.parseInt(kb.nextLine());
				if(choice > baddies.size() || choice < 1)
				{
					do
					{
						System.out.println("You must enter a valid enemy!");
						choice = Integer.parseInt(kb.nextLine());
	
					}while(choice > baddies.size() || choice <  1);
				}
				
				choice = choice - 1;
				GameCharacter enemy = baddies.get(choice);
				heroes.get(x).attack(baddies.get(choice));
				System.out.println(hero.getName() + " attacks " + enemy.getName() + " for " + hero.getDamage() + " damage!\n");
				
				if(enemy.isDead())
				{
					System.out.println(enemy.getName() + " has died!");
					baddies.remove(choice);
				}
			}

			for(int y = 0; y < baddies.size(); y++)
			{
				GameCharacter target = heroTeam.getLowestHealth();
				GameCharacter attacker = baddies.get(y);
				System.out.println(attacker.getName() + ": Attacks " + target.getName()
				+ " and does " + attacker.getDamage() + " damage");

				attacker.attack(target);
			}
			System.out.print("\n");
		}
		System.out.println("You won the battle!");
	}
}
