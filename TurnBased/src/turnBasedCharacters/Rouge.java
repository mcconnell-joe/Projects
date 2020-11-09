package turnBasedCharacters;

public class Rouge extends PlayerHero
{
	private double crit;

	public Rouge(String name, int level)
	{
		super(name, level);
		this.crit = level * .1;
		this.setType("Rouge");
	}

}
