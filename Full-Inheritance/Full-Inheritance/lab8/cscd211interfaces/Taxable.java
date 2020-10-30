package lab8.cscd211interfaces;
/**
 * The interface Taxable that contains a constant for the current tax rate and an abstract method to calculateTaxes
 */
public interface Taxable
{
	/**
	 * Tax rate is set to 0.09
	 */
	public final double BASE_TAX_RATE = 0.09;

	/**
	 * The calculateTaxes is defined in the implementing class
	 * @return double Representing the calculated taxes
	 */
	public abstract double calculateTaxes();
}