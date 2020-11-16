package turnBasedCharacters;

import teams.Team;

public class StatusManager
{
	Team heroes;
	Team enemies;
	int ticks;
	
	public StatusManager(Team heroes, Team enemies)
	{
		this.heroes = heroes;
		this.enemies = enemies;
	}
	
	public void setStunned(GameCharacter target, Team team)
	{
		
	}
	
	public void setPoison(GameCharacter target, Team team)
	{
		
	}
	
}
