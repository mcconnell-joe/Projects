package teams;

import java.util.ArrayList;
import java.util.Random;

import turnBasedCharacters.Enemy;
import turnBasedCharacters.GameCharacter;

public class EnemyTeamGenerator
{
	private String[] names = {"Evil Boy", "Stinky Man", "Tickle Monster", "Angry Dude", "Pissy Pete", "Mr.Omally", "Monkey Mike", "Potato", "Scrawny Bananna", "Momma MeatBall", "Angry Jello"};
	Random rand = new Random();
	int random = rand.nextInt(11);

	public Team createEnemyTeam(Team heroTeam)
	{
		ArrayList<GameCharacter> enemies = new ArrayList<GameCharacter>();
		for(int x = 0; x < heroTeam.getTeamSize(); ++x)
		{
			enemies.add(new Enemy(names[rand.nextInt(11)], 100, .65, 5, 15));
		}
		enemies.trimToSize();
		Team enemyTeam = new Team(enemies);

		return enemyTeam;
	}
}
