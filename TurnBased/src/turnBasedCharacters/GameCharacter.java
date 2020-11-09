package turnBasedCharacters;

public abstract class GameCharacter
{
	private String name;
	private double health;
	private double damage;
	private int level;
	private String type;

	public GameCharacter(String name, int level)
	{
		this.name = name;
		this.level = level;
		this.damage = 10 + level;
		setHealth();
	}

	public void heal(double potion, double curHealth)
	{
		double postHeal = potion + curHealth;
		this.health = postHeal;
	}

	//GETTERS//
	public String getType()
	{
		return this.type;
	}
	public int getLevel()
	{
		return this.level;
	}

	public double getHealth()
	{
		return this.health;
	}

	public double getDamage()
	{
		return this.damage;
	}

	public String getName()
	{
		return this.name;
	}

	//SETTERS//
	public void setType(String type)
	{
		this.type = type;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public void setHealth()
	{
		this.health = (level * 2) + 100;
	}

	//Notification and actions//
	public boolean isDead()
	{
		return(this.health <= 0);
	}

	public void attack(GameCharacter target)
	{
		target.health = target.health - this.damage; 
	}



}
