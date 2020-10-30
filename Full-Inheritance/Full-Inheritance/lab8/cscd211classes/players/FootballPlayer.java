package lab8.cscd211classes.players;

public class FootballPlayer extends Player implements Cloneable
{
	protected int jerseyNumber;
	protected int td;

	public FootballPlayer(final String name, final String ssn, final int salary, final String position, final int td, final int jerseyNumber)
	{
		super(name, ssn, salary, position);

		if(td < 0 || jerseyNumber < 0)
		{
			throw new IllegalArgumentException("Error: " + (td < 0 ? "td" : "jerseyNumber") + " is less than zero.");
		}

		this.td = td;
		this.jerseyNumber = jerseyNumber;
	}

	@Override
	public String toString()
	{
		return super.toString() + "\tTouchdowns: " + this.td + "\tjersey: " + this.jerseyNumber + "\n";
	}

	@Override
	public FootballPlayer clone() throws CloneNotSupportedException
	{
		return (FootballPlayer)(super.clone());
	}
}
