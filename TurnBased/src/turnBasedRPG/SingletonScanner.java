package turnBasedRPG;
import java.util.Scanner;
public class SingletonScanner
{
	private static Scanner kb = new Scanner(System.in);

	public static Scanner getScanner()
	{
		return kb;
	}
}
