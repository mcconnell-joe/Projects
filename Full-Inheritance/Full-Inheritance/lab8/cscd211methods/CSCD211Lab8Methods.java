package lab8.cscd211methods;

import java.util.ArrayList;
import java.util.Scanner;

import lab8.cscd211classes.Team;
import lab8.cscd211classes.players.BaseballPlayer;
import lab8.cscd211classes.players.FootballPlayer;
import lab8.cscd211classes.players.Player;

/**
 * I have provided the menu method for you.  You must write the fill method. Please look at the file teamlist.txt
 * <br>
 * The file format is City\n Number of teams\n Type, Team Name\n Number of Players\n <br>
 * player name, ssn, salary, position, specific info based on type <br>
 * if football then # of touch downs, jersey number <br>
 * if baseball then batting average <br>
 * if hockey then jersey number <br>
 *
 * @NOTE All parameters passed must be final
 */
public class CSCD211Lab8Methods
{
	/**
	 * reads from the file and creates the appropriate player for the array and
	 * creates the appropriate team
	 *
	 * @param fin Scanner object
	 * @param myTeam ArrayList of type Team
	 *
	 * @throws IllegalArgumentException for fin or myTeam being null
	 * @throws CloneNotSupportedException to propagate the clone method
	 *
	 * @NOTE To add to MyArrayList it is an addLast method call myTeam.addLast(team stuff)
	 */
	public static void fillArrayList(final Scanner fin, final ArrayList<Team>myTeam)throws CloneNotSupportedException
	{
		if(fin == null || myTeam == null)
			throw new IllegalArgumentException("Error Precond: Scanner or Team cannot be null");

		String teamCity, teamName, playerName, ssn, position, type;
		int numPlayers, salary, td = 0, numTeams, jersey;
		double batAvg = 0;
		Player [] playerArray;

		while(fin.hasNext())
		{

			teamCity = (fin.nextLine()). trim();
			numTeams = Integer.parseInt((fin.nextLine()).trim());

			for(int x  = 0; x < numTeams; ++x)
			{
				String[] split = fin.nextLine().split(",");
				type = split[0].trim();
				teamName = split[1].trim();
				numPlayers = Integer.parseInt(fin.nextLine().trim());
				playerArray = new Player[numPlayers];

				for(int z = 0; z < numPlayers; ++z)
				{
					String[] split2 = fin.nextLine().split(",");
					playerName = split2[0].trim();
					ssn = split2[1].trim();
					salary = Integer.parseInt(split2[2].trim());
					position = split2[3].trim();


					if(type.equals("Baseball"))
					{
						batAvg = Double.parseDouble(split2[4].trim());
						playerArray[z] = new BaseballPlayer(playerName, ssn, salary, position, batAvg);

					}
					else if(type.equals("Football"))
					{
						jersey = Integer.parseInt(split2[4].trim());
						td = Integer.parseInt(split2[5].trim());
						playerArray[z] = new FootballPlayer(playerName, ssn, salary, position, jersey, td);

					}
					else
					{
						jersey = Integer.parseInt(split2[4].trim());
						playerArray[z] = new BaseballPlayer(playerName, ssn, salary, position, jersey);
					}
				}
				Team team = new Team(teamCity, teamName, playerArray);
				myTeam.add(team);
			}
		}
		myTeam.trimToSize();

	}// end createAndFill

	/**
	 * The menu method ensures a valid choice is entered and returns that value
	 * <br> 1 Print all Teams
	 * <br> 2 Sort all Teams by city and team name
	 * <br> 3 Sort all Teams by Payroll
	 * <br> 4 Exit program
	 *
	 * @param kb Representing a valid Scanner object
	 * @return int Representing the menu choice
	 *
	 * @throws IllegalArgumentException If Scanner is null
	 */
	public static int menu(final Scanner kb)
	{
		if (kb == null)
			throw new IllegalArgumentException("Error Precond: Scanner cannot be null - menu");

		int choice;

		do
		{
			System.out.println("Please choose from the following");
			System.out.println("1) Print all Teams");
			System.out.println("2) Sort all Teams by city and team name");
			System.out.println("3) Sort all Teams by Payroll");
			System.out.println("4) Exit program");
			System.out.print("Choice --> ");
			choice = kb.nextInt();
			kb.nextLine();
		} while (choice < 1 || choice > 4);

		return choice;
	}// end menu

}// end class