package turnBasedCharacters;

public class Knight extends PlayerHero
{
	private double armour;
	public Knight(String name, int level)
	{
		super(name, level);
		this.setType("Knight");
	}

	@Override
	public void displayAbilites()
	{
		System.out.println("Avaliable Moves: ");
		System.out.println("1) Attack");
		System.out.println("2) Block Teammate");
	}




}
