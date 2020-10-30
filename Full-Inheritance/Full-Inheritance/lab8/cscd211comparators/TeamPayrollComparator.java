package lab8.cscd211comparators;

import java.util.Comparator;

import lab8.cscd211classes.Team;

public class TeamPayrollComparator implements Comparator<Team>
{
	@Override
	public int compare(final Team t1, final Team t2)
	{
		if(t1 == null || t2 == null)
		{
			throw new IllegalArgumentException("Error: " + (t1 == null ? "t1": "t2") + " is null");
		}

		int res = t1.getPayroll() - t2.getPayroll();

		return res;
	}
}
