package status;

import java.util.ArrayList;

public class StatusManager
{
	private ArrayList<Status> statuses;
	
	public StatusManager()
	{
		statuses = new ArrayList<Status>();
	}
	
	public void checkStunned()
	{
		
	}

	//Walk through array list and if there are ticks call action()
	public void checkStatuses(){
		
		for(int x = 0; x < statuses.size(); x++) {
			if(statuses.get(x).getTics() > 0) {
				statuses.get(x).doAction();
			}
			else {
				statuses.remove(x);
			}
		}
	}
	
	public void addStatus(Status status)
	{
		statuses.add(status);
	}
	
}
