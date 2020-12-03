package fraction;

public class Fraction implements Comparable<Fraction>
{
	private int num;
	private int den;
	
	public Fraction(int num, int den)
	{
		if(den == 0)
		{
			throw new IllegalArgumentException("The denominator with the value of 0 is not permitted");
		}
		if(den < 0)
		{
			num *= -1;
			den *= -1;
		}

		this.num = num;
		this.den = den;
		
		simplify();
	}
	
	public Fraction add(Fraction frac)
	{
		if(frac == null)
		{
			throw new IllegalArgumentException("Cannot perform math operations on a null fraction object!");
		}
		int den1 = this.den;
		int den2 = frac.den;
		
		this.num *= den2;
		frac.num *= den1;
		
		this.den *= den2;
		frac.den *= den1;
		
		frac.num += this.num;
		
		frac.simplify();
		
		return frac;
	}
	
	public Fraction multiply(Fraction frac)
	{
		if(frac == null)
		{
			throw new IllegalArgumentException("Cannot perform math operations on a null fraction object!");
		}
		frac.num *= this.num;
		frac.den *= this.den;
		
		frac.simplify();
		
		return frac;
	}
	
	public Double realValue()
	{
		return (double)this.num / this.den;
	}
	
	@Override
	public int compareTo(Fraction frac)
	{
		if(this.realValue() == frac.realValue())
		{
			return 0;
		}
		
		int den1 = this.den;
		int den2 = frac.den;
	
		int res = (this.num * den2) - (frac.num * den1);
		
		return res;
		
	}
	
	
	
	public boolean equals(Object o)
	{
		Fraction frac = (Fraction)o;
		
		if(this.num == frac.num && this.den == frac.den)
		{
			return true;
		}
		
		return false;
	}
	
	public int getNum()
	{
		return this.num;
	}
	public int getDen()
	{
		return this.den;
	}
	
	public void simplify()
	{

		
		int gcd = this.gcd(this.num, this.den);
		
		if(gcd < 0)
		{
			gcd *= -1;
		}
		
		this.num /= gcd;
		this.den /= gcd;
	}
	
	public int gcd(int a, int b)
	{
		if(b == 0)
		{
			return a;
		}
		return gcd(b, a % b);
	}
	
	@Override
	public String toString()
	{
		simplify();
		
		return this.num + "/" + this.den;
	}
}
