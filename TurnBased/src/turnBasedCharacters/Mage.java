package turnBasedCharacters;

public class Mage extends PlayerHero
{
	private double dodgeChance;

	public Mage(String name, int level)
	{
		super(name, level);
		this.dodgeChance = level * .075;
		this.setType("Mage");
	}


	public void healOther(double potion, GameCharacter target)
	{
		target.setHealth();
	}

}
