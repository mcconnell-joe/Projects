package lab8.cscd211classes;
import java.util.ArrayList;

import lab8.cscd211classes.players.Player;
import lab8.cscd211interfaces.Payroll;
import lab8.cscd211interfaces.Taxable;

public class Team implements Taxable, Payroll, Comparable<Team>
{
	protected String city;
	protected int payroll;
	protected ArrayList<Player> players;
	protected String teamName;

	public Team(final String city, final String teamName, final Player[] players) throws CloneNotSupportedException
	{
		if(city.strip().isEmpty() || teamName.strip().isEmpty() || city == null || teamName == null)
		{
			throw new IllegalArgumentException("The strings are either empty or null.");
		}
		if(players == null || players.length <= 0)
		{
			throw new IllegalArgumentException("The array is null or empty.");
		}

		this.city = city;
		this.teamName = teamName; 
		ArrayList<Player> arrayList = new ArrayList<Player>(players.length);

		for(int x = 0; x < players.length; ++x)
		{
			Player temp = players[x].clone();
			arrayList.add(temp);
		}
		this.players = arrayList;
		this.payroll = calculatePayroll();

	}

	public String getTeamName()
	{
		return this.teamName;
	}

	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}

	public String getCity()
	{
		return this.city;
	}

	public int getPayroll()
	{
		return this.payroll;
	}

	@Override
	public String toString()
	{
		String str = this.city + " - " + this.teamName + "\nPayroll: " + this.payroll + "\nTaxes: " + calculateTaxes() + 
				"\nPLAYER NAME      SSN     SALARY     POSITION     STATS     NUMBER"
				+ "\n-----------------------------------------------------------------\n";
		for(int x = 0; x < this.players.size(); ++x)
		{
			str += players.get(x).toString();
		}

		str += "\n";

		return str;
	}

	@Override
	public double calculateTaxes()
	{
		double taxes = 0.0;
		for(int x = 0; x < this.players.size(); ++x)
		{
			Player temp = this.players.get(x);
			int sal = temp.getSalary();
			double tax = 0.0;
			if(sal > 250000)
			{
				tax = sal * Taxable.BASE_TAX_RATE;
			}
			else
			{
				tax = sal * (Taxable.BASE_TAX_RATE - .1);
			}
			taxes += tax;

		}

		return taxes;
	}

	@Override
	public int calculatePayroll()
	{
		int pay = 0, totalPay = 0;

		for(int x = 0; x < this.players.size(); ++x)
		{
			Player temp = this.players.get(x);
			pay = temp.getSalary();
			totalPay += pay;
		}
		totalPay += Payroll.BASE_PAYROLL;
		return totalPay;
	}

	@Override
	public int compareTo(final Team pi)
	{
		if(pi == null)
		{
			throw new IllegalArgumentException("Error: pi is null.");
		}

		int res = this.city.compareTo(pi.city);
		if(res == 0)
		{
			res = this.teamName.compareTo(pi.teamName);
		}

		return res;
	}
}
