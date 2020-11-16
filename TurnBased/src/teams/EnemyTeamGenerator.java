package teams;

import java.util.ArrayList;
import java.util.Random;

import Enemies.Ogre;
import Enemies.Orc;
import Enemies.Skeleton;
import turnBasedCharacters.GameCharacter;

public class EnemyTeamGenerator
{
	private String[] names = {"Evil Boy", "Stinky Man", "Tickle Monster", "Angry Dude", "Pissy Pete", "Mr.Omally", "Monkey Mike", "Potato", "Scrawny Bananna", "Momma MeatBall", "Angry Jello", "Scary Terry", "Nasty Nelly", "Slammy Tammy" };
	private String[] types = {"Ogre", "Skeleton", "Orc"};
	Random rand = new Random();
	

	public Team createEnemyTeam(Team heroTeam)
	{
		ArrayList<GameCharacter> enemies = new ArrayList<GameCharacter>();
		for(int x = 0; x < heroTeam.getTeamSize(); ++x)
		{
			int random = rand.nextInt(names.length);
			int randomT = rand.nextInt(types.length);
			String type = types[randomT];
			
			if(type == "Ogre")
			{
				enemies.add(new Ogre(names[random], 150, .65, 10, 25));
			}
			else if(type == "Orc")
			{
				enemies.add(new Orc(names[random], 75, .70, 8, 15));
			}
			else
			{
				enemies.add(new Skeleton(names[random], 100, .60, 10, 17));
			}
		}
		enemies.trimToSize();
		Team enemyTeam = new Team(enemies);

		return enemyTeam;
	}
}
