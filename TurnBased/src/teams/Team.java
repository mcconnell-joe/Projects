package teams;
import java.util.ArrayList;

import turnBasedCharacters.GameCharacter;

public class Team
{
	private ArrayList<GameCharacter> team;
	private double teamHealth;

	public Team(ArrayList<GameCharacter> team)
	{
		this.team = team;
	}

	public int getTeamSize()
	{
		return team.size();
	}

	public double getTeamHealth()
	{
		for(int x = 0; x < team.size(); ++x)
		{
			teamHealth += team.get(x).getHealth();
		}

		return this.teamHealth;
	}

	public ArrayList<GameCharacter> getTeamArray()
	{
		return team;
	}

	public boolean teamDead()
	{
		for(int x = 0; x < team.size(); ++x)
		{
			if(!(team.get(x).isDead()))
			{
				return false;
			}
		}
		return true;
	}

	public GameCharacter getLowestHealth()
	{
		int min = 0;

		for(int x = 1; x < team.size(); ++x)
		{
			if(team.get(x).getHealth() < min)
			{
				min = x;
			}
		}

		return team.get(min);
	}

	public int getAvgTeamLevel()
	{
		int teamLvl = 0;
		for(int x = 0; x < team.size(); ++x)
		{
			teamLvl += team.get(x).getLevel();
		}
		teamLvl /= team.size();

		return teamLvl;

	}

}
