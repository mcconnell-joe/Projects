package lab8.cscd211classes.players;

public class BaseballPlayer extends Player implements Cloneable
{
	protected double batAvg;

	public BaseballPlayer(final String name, final String ssn, final int salary, final String position, final double batAvg)
	{
		super(name, ssn, salary, position);

		if(batAvg < 0)
		{
			throw new IllegalArgumentException("Error: batAvg is less than 0.");
		}

		this.batAvg = batAvg;
	}

	@Override
	public String toString()
	{
		return super.toString() + " " + this.batAvg + "\n";
	}

	@Override
	public BaseballPlayer clone() throws CloneNotSupportedException
	{
		return (BaseballPlayer)(super.clone());
	}
}
