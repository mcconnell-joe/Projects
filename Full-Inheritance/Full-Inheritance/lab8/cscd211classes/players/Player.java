package lab8.cscd211classes.players;

public abstract class Player implements Cloneable
{
	protected String name;
	protected String position;
	protected int salary;
	protected String ssn;

	protected Player(final String name, final String ssn, final int salary, final String position)
	{
		this.name = name;
		this.ssn = ssn;
		this.salary = salary;
		this.position = position;
	}

	public int getSalary()
	{
		return this.salary;
	}

	public String getPosition()
	{
		return this.position;
	}

	public String getName()
	{
		return this.name;
	}

	public String getSSN()
	{
		return this.ssn;
	}

	@Override
	public String toString()
	{
		return this.name + " " + this.ssn + " " + this.salary + " " + this.position;
	}

	@Override
	public Player clone() throws CloneNotSupportedException
	{
		return (Player)super.clone();
	}

}
