package lab8.cscd211classes.players;

public class HockeyPlayer extends Player implements Cloneable
{ 
	protected int jerseyNumber;

	public HockeyPlayer(final String name, final String ssn, final int salary, final String position, final int jerseyNumber)
	{
		super(name, ssn, salary, position);

		if(jerseyNumber < 0)
		{
			throw new IllegalArgumentException("Error: jerseyNumber is less than zero.");
		}

		this.jerseyNumber = jerseyNumber;
	}

	@Override
	public String toString()
	{
		return super.toString() + " jerseyNumber: " + this.jerseyNumber + "\n";
	}

	@Override
	public HockeyPlayer clone() throws CloneNotSupportedException
	{
		return (HockeyPlayer)(super.clone());
	}

}
