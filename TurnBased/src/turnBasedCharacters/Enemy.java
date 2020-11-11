package turnBasedCharacters;

public class Enemy extends GameCharacter
{
	public Enemy(String name, int health, double hitChance, int minDmg, int maxDmg)
	{
		super(name, health, hitChance, minDmg, maxDmg);
		this.setType("Enemy");
	}


}
